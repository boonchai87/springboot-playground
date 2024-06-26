package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.exception.ResourceNotFoundException;
import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.repository.UserRepository;
import com.nengboonchai.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;


    private BCryptPasswordEncoder bcryptEncoder=new BCryptPasswordEncoder();

    @GetMapping("/create")
    public String showSignUpForm(User user) {
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
        return "redirect:/user";
    }

    @GetMapping("")//don't use @GetMapping("/")
    public String index(Model model) {
        return "redirect:/user/";
    }

//    @GetMapping("/")//don't use @GetMapping("/")
//    public String retrive(Model model) {
//        model.addAttribute("users", repository.findAll());
//        return "user/retrive";
//    }

    // https://www.geeksforgeeks.org/spring-mvc-crud-with-example/
    @GetMapping("/")//don't use @GetMapping("/")
    public String retrive(@RequestParam(name = "pageNo",required = false,defaultValue = "1") int pageNo,
                          @RequestParam(name = "sortField",required = false) String sortField,
                          @RequestParam(name="sortDir",required = false) String sortDir,
                          Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginate(pageNo, pageSize, sortField, sortDir);
        List<User> listObjects = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        if( sortField==null)
            sortField="";
        model.addAttribute("sortField", sortField);

        if( sortDir!=null && (sortDir.equals("desc") || sortDir.equals("asc")) ){
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }else{
            model.addAttribute("sortDir", "asc");
            model.addAttribute("reverseSortDir", "desc");
        }


        model.addAttribute("users", listObjects);
        return "user/retrive";
    }

    @GetMapping("/view/{id}")
    public String show(@PathVariable("id") Long id,Model model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getName(),"id",id));
        model.addAttribute("user", user);
        return "user/view";
    }

    // show edit form
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
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
            //return "redirect:/user/edit/"+id;
            return "user/edit";
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