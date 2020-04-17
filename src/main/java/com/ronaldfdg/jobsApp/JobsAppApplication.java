package com.ronaldfdg.jobsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JobsAppApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(JobsAppApplication.class, args);
	}

}
