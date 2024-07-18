package com.nengboonchai.demo.controller;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TestCircuitBrekaerController {

    // https://www.baeldung.com/spring-retry
    @RequestMapping(value = "/testRetry")
    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 100))
    public String hello() throws Exception {
        System.out.println(1/0);// must throw exception for let retry use for trigger
        Thread.sleep(30000);
        return "Welcome Hystrix";
    }

    @Recover
    private String fallback_hello() {
        return "Request fails. It takes long time to response";
    }
}
