package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.kafka.ProducerKafaService;
import com.nengboonchai.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }
    @GetMapping("/hello")
    public String getMethodName() {
        return "hello";
    }

    @GetMapping("/register")
    public String showCreateForm(Model model) {
        System.out.println("r-----------------------------");
        model.addAttribute("user", new User());
        return "user/create";
    }

    @Autowired
    ProducerKafaService producer;

    @GetMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam(name = "message",required = false,defaultValue = "ccc") String message) {
        producer.sendMessageNoWait("quickstart-events","",message);
        return "ok";
    }
}