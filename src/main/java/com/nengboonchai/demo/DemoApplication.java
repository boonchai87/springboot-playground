package com.nengboonchai.demo;

import com.nengboonchai.demo.exception.RestTemplateResponseErrorHandler;
import com.nengboonchai.demo.service.ConsumeKafkaService;
import com.nengboonchai.demo.kafka.ProducerKafaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableRetry
public class DemoApplication implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	//@Autowired
	//RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
//		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//		RestTemplate restTemplate = restTemplateBuilder
//				.errorHandler(new RestTemplateResponseErrorHandler())
//				.build();
//		return restTemplate;
	}



	@Autowired
	ProducerKafaService produce;

	@Autowired
	ConsumeKafkaService consume;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("This is the first thing that will run before spring.");
		logger.debug("this is a debug message");
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");

//		String topic ="quickstart-events";
//		produce.sendMessageNoWait(topic,"test");
		//consume.listenGroupFoo();
	}
}
