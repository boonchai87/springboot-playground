package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/register")
    public String showCreateForm(Model model) {
        System.out.println("r-----------------------------");
        model.addAttribute("user", new User());
        return "user/create";
    }
}
