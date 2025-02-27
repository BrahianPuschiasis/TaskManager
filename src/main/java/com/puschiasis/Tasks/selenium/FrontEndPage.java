package com.puschiasis.Tasks.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontEndPage extends BasePage{


    public FrontEndPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    /** Login */
    private By txtLoginUsername = By.xpath("//input[@type='text' and @class='w-full p-3 mt-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400']");
    private By txtLoginPass = By.xpath("//input[@type='password' and @class='w-full p-3 mt-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400']");
    private By btnLogin = By.xpath("//button[@type='submit' and @class='w-full bg-blue-500 text-white py-3 rounded-lg hover:bg-blue-600 transition']");

    public void LoginUsername(String name) throws InterruptedException {
        this.sendText(name, txtLoginUsername);
    }

    public void LoginPass(String pass) throws InterruptedException {
        this.sendText(pass, txtLoginPass);
    }

    public void clickbtnLogin() throws InterruptedException {
        this.click(btnLogin);
    }

    /** Create Task */
    private By btnCreateTask = By.xpath("//button[@class='px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 transition-colors']");
    private By txtTaskTitle = By.xpath("//input[@class='w-full p-2 border border-gray-300 rounded-md' and @type='text']");
    private By txtTaskDescription = By.xpath("//textarea[@class='w-full p-2 border border-gray-300 rounded-md']");
    private By txtTaskDueDate = By.xpath("//input[@class='w-full p-2 border border-gray-300 rounded-md' and @type='date']");
    private By ddlTaskStatus = By.xpath("//select[@class='w-full p-2 border border-gray-300 rounded-md']");
    private By btnCreate = By.xpath("//button[normalize-space(text())='Crear']");

    public void clickCreateTaskButton() throws InterruptedException {
        this.click(btnCreateTask);
    }

    public void enterTaskTitle(String title) throws InterruptedException {
        this.sendText(title, txtTaskTitle);
    }

    public void enterTaskDescription(String description) throws InterruptedException {
        this.sendText(description, txtTaskDescription);
    }

    public void enterTaskDueDate(String dueDate) throws InterruptedException {
        this.sendText(dueDate, txtTaskDueDate);

    }

    public void selectTaskStatusPending() throws InterruptedException {
        this.selectFirstOptionFromDropdown(ddlTaskStatus);
    }

    public void clickCreateButton() throws InterruptedException {
        this.click(btnCreate);
    }

    /** Update Task */
    private By btnEditTask = By.xpath("//button[@class='px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors']");
    private By txtNewTaskTitle = By.xpath("//input[@class='w-full p-2 border border-gray-300 rounded-md' and @type='text']");
    private By txtNewTaskDescription = By.xpath("//textarea[@class='w-full p-2 border border-gray-300 rounded-md']");
    private By btnUpdate = By.xpath("//button[normalize-space(text())='Actualizar']");

    public void clickEditTaskButton() throws InterruptedException {
        this.click(btnEditTask);
    }

    public void editNewTaskTitle(String newTitle) throws InterruptedException {
        this.sendText(newTitle, txtNewTaskTitle);
    }

    public void editNewTaskDescription(String newDescription) throws InterruptedException {
        this.sendText(newDescription, txtNewTaskDescription);
    }

    public void clickUpdateButton() throws InterruptedException {
        this.click(btnUpdate);
    }

    public void updateTask(String newTitle, String newDescription) throws InterruptedException {
        clickEditTaskButton();
        editNewTaskTitle(newTitle);
        editNewTaskDescription(newDescription);
        clickUpdateButton();
    }


    /** Delete Task */
    private By btnDelete = By.xpath("//button[@class='absolute top-2 right-2 text-red-500 hover:text-white hover:bg-red-500 p-2 rounded-full transition-colors']");
    private By btnConfirmDelete = By.xpath("//button[normalize-space(text())='Aceptar']");
    public void clickDeleteButton() throws InterruptedException {
        this.click(btnDelete);
    }

    public void clickConfirmDeleteButton() throws InterruptedException {
        this.click(btnConfirmDelete);
    }







}