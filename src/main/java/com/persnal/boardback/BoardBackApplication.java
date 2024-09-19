package com.persnal.boardback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {})
public class BoardBackApplication {
	public static void main(String[] args) {
		//주석추가
		SpringApplication.run(BoardBackApplication.class, args);

	}

}
