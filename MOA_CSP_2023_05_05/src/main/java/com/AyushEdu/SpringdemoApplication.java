package com.AyushEdu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.AyushEdu.controller.Counselling_Institute.AES;

@SpringBootApplication
//@EnableScheduling
public class SpringdemoApplication {

	public static void main(String[] args) {
		
		
		System.err.println("Current Directory = " + System.getProperty("user.dir"));
		SpringApplication.run(SpringdemoApplication.class, args);
		
		
	}

}
