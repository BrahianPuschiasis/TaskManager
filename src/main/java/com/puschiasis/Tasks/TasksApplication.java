package com.puschiasis.Tasks;

import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//http://localhost:5167/swagger-ui/index.html#/ (port depends on execution)
@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {

		SpringApplication.run(TasksApplication.class, args);

	}

}
