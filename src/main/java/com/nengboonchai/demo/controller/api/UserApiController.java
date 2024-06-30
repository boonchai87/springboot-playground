package com.nengboonchai.demo.controller.api;

import com.nengboonchai.demo.exception.ResourceNotFoundException;
import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.repository.UserRepository;
import com.nengboonchai.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    // create
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user, BindingResult result) {
        user.setId(null);
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        System.out.println("ddddd"+user.getId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // retrive
    @GetMapping
    public ResponseEntity<List<User>> retrive(@RequestParam(name = "pageNo",required = false,defaultValue = "1") int pageNo,
                          @RequestParam(name = "sortField",required = false) String sortField,
                          @RequestParam(name="sortDir",required = false) String sortDir
                          ) {
        int pageSize = 5;

        Page<User> page = userService.findPaginate(pageNo, pageSize, sortField, sortDir);
        List<User> listObjects = page.getContent();
        return new ResponseEntity<List<User>>(listObjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        Optional<User> optional = userRepository.findById(id);
        if( optional.isPresent() ){

            return new ResponseEntity<User>(optional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    // update
    @PostMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id,@RequestBody @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            System.out.println(result);
            //return "redirect:/user/edit/"+id;
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User oldUser = optional.get();
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setUpdated(new Date());
            if (!user.getPassword().trim().equalsIgnoreCase("")) {
                oldUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            }
            userService.saveUser(oldUser);
            return new ResponseEntity<>(oldUser,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<User> memberOptional = userRepository.findById(id);
        if (memberOptional.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}