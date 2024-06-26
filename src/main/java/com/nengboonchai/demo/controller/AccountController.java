package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AccountController {
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/signup")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }
}
