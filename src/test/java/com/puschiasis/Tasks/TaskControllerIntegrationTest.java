package com.puschiasis.Tasks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private Long taskId;

    @BeforeAll
    public void setUp() throws Exception {
        String newTaskJson = "{" +
                "\"title\":\"New Task\"," +
                "\"description\":\"Task description\"," +
                "\"status\":\"Pending\"," +
                "\"dueDate\":\"2025-02-26T00:00:00.000+00:00\"," +
                "\"assignedUser\":\"john_doe\"" +
                "}";

        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task description"))
                .andExpect(jsonPath("$.status").value("Pending"))
                .andExpect(jsonPath("$.assignedUser").value("john_doe"))
                .andExpect(jsonPath("$.dueDate").value("2025-02-26T00:00:00.000+00:00"))
                .andReturn().getResponse().getContentAsString();

        taskId = Long.parseLong(response.split("\"id\":")[1].split(",")[0]);
    }

    // Test GET method: getAllTasks
    @Test
    @Order(1)
    public void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    // Test GET method: getTaskById
    @Test
    @Order(2)
    public void testGetTaskById() throws Exception {
        mockMvc.perform(get("/tasks/" + taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.assignedUser").exists());
    }

    // Test GET method: getTasksByAssignedUser
    @Test
    @Order(3)
    public void testGetTasksByAssignedUser() throws Exception {
        mockMvc.perform(get("/tasks/assigned/john_doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].assignedUser").value("john_doe"));
    }

    // Test PUT method: updateTask
    @Test
    @Order(4)
    public void testUpdateTask() throws Exception {
        String updatedTaskJson = "{" +
                "\"title\":\"Updated Task\"," +
                "\"description\":\"Updated description\"," +
                "\"status\":\"In Progress\"," +
                "\"dueDate\":\"2025-03-01T00:00:00.000+00:00\"," +
                "\"assignedUser\":\"jane_doe\"" +
                "}";

        mockMvc.perform(put("/tasks/" + taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.assignedUser").value("jane_doe"))
                .andExpect(jsonPath("$.status").value("In Progress"));
    }

    // Test DELETE method: deleteTask
    @Test
    @Order(5)
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/" + taskId))
                .andExpect(status().isNoContent());
    }
}
