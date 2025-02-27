package com.puschiasis.Tasks;



import com.puschiasis.Tasks.selenium.FrontEndPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FrontEnd {


    private WebDriver driver;
    private WebDriverWait wait;
    private String username = "probando";
    private String password = "probando";

    @BeforeAll
    public static void createReport() {

        System.out.println("<<< COMIENZAN LOS TEST DE FRONTEND >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);

        frontEndPage.setup();
        frontEndPage.getUrl("http://localhost");


    }


    public void Login() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);

        frontEndPage.LoginUsername(username);
        frontEndPage.LoginPass(password);
        frontEndPage.clickbtnLogin();

    }


    @Test
    @Order(1)
    @Tag("makeTask")
    public void makeTask() throws InterruptedException {

        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        Login();
        frontEndPage.clickCreateTaskButton();
        frontEndPage.enterTaskTitle("Nueva tarea");
        frontEndPage.enterTaskDescription("Descripción de la tarea");
//        frontEndPage.enterTaskDueDate("");
        frontEndPage.clickCreateButton();


    }

    @Test
    @Order(2)
    @Tag("updateTask")
    public void updateTask() throws InterruptedException {

        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        Login();
        frontEndPage.clickEditTaskButton();
        frontEndPage.editNewTaskTitle("Tarea actualizada");
        frontEndPage.editNewTaskDescription("Descripción actualizada");
        frontEndPage.clickUpdateButton();
    }

    @Test
    @Order(3)
    @Tag("deleteTask")
//    @Tag("EXITOSO")
    public void deleteTask() throws InterruptedException {

        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        Login();  // Iniciar sesión
        frontEndPage.clickDeleteButton();
        frontEndPage.clickConfirmDeleteButton();
    }



    @AfterEach
    public void cerrar() {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        frontEndPage.close();

    }
}