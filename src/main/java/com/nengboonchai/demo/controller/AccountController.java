package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.dto.UserDto;
import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
// https://www.javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html#google_vignette
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/registerForm")
    public String showRegistrationForm(Model model){
        System.out.println("register form");
        // create model object to store form data
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "account/registerForm";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/save")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("userDto", userDto);
            return "account/registerForm";
        }

        User existingUser = userService.findUserByEmail(userDto.getEmail());
        System.out.println(existingUser);
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("userDto", userDto);
            return "/account/registerForm";
        }
        userService.saveUser(userDto);
        return "redirect:/account/registerForm?success";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "account/loginForm";
    }
}
