package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    SendMailService sendMailService;

    @GetMapping(value = "/sendemail")
    public String sendEmail() throws Exception{
        sendMailService.sendmail();
        return "Email sent successfullyxxx";
    }

}
