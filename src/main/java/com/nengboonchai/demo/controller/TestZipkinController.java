package com.nengboonchai.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestZipkinController {
    private static final Logger log = LoggerFactory.getLogger(TestZipkinController.class.getName());
    @RequestMapping("/testZipkin")
    public String index() {
        log.info("Index API is calling");
        return "Welcome Sleuth!";
    }
}
