package com.puschiasis.Tasks.dto;

import com.puschiasis.Tasks.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Date dueDate;
    private String assignedUser;

    public TaskDTO(Long id, String title, String description, String status, Date dueDate, String assignedUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.dueDate = task.getDueDate();
        this.assignedUser = task.getAssignedUser();
    }
}
