package com.nengboonchai.demo.controller;

import com.nengboonchai.demo.exception.ResourceNotFoundException;
import com.nengboonchai.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;

    // create
    @PostMapping(value = "/template/user")
    public String createProducts(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/user", HttpMethod.POST, entity, String.class).getBody();
    }

    // retrive
    @GetMapping(value = "/template/users")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:8080/api/user", HttpMethod.GET, entity, String.class).getBody();
    }

    // retrive
    @GetMapping(value = "/template/users/{id}")
    public User findById(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try{
            return restTemplate.exchange("http://localhost:8080/api/user/"+id, HttpMethod.GET, entity, User.class).getBody();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ResourceNotFoundException("user","id",id);
        }
    }

    // update
    @PutMapping(value = "/template/users/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/user/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping(value = "/template/users/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/user/"+id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
