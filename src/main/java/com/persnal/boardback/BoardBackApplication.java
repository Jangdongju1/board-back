package com.persnal.boardback;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.thymeleaf.engine.IterationStatusVar;

import java.io.FilterOutputStream;

@SpringBootApplication(exclude = {})
public class BoardBackApplication {
	public static void main(String[] args) {
		BoardBackApplication t = new BoardBackApplication();
		SpringApplication.run(BoardBackApplication.class, args);

	}

}
