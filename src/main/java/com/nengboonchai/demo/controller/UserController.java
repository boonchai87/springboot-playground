package com.nengboonchai.demo.controller;

import com.thaiitwork.exception.ResourceNotFoundException;
import com.thaiitwork.model.User;
import com.thaiitwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @GetMapping("")//don't use @GetMapping("/")
    public String retrive(Model model) {
        model.addAttribute("users", repository.findAll());
        return "user/retrive";
    }
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") long id,Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getName(),"id",id));
        model.addAttribute("user", user);
        return "user/show";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping("/create")
    public String create(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/create";
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        repository.save(user);
        return "redirect:";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getName(),"id",id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "redirect:/user/edit/"+id;
        }
        user.setUpdated(new Date());
        if( !user.getPassword().trim().equalsIgnoreCase("") ) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
        }
        repository.save(user);
        return "redirect:/user";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getName(),"id",id));
        repository.delete(user);
        return "redirect:/user";
    }
}
