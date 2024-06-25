package com.nengboonchai.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	//@Autowired
	//RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	/*@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}*/

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("This is the first thing that will run before spring.");
		logger.debug("this is a debug message");
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
	}
}
