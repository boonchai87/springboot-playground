package com.nengboonchai.demo.controller.api;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nengboonchai.demo.DemoApplication;
import com.nengboonchai.demo.model.Member;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/member/remote")
public class RemoteMemberController {
   private static final Logger logger = LoggerFactory.getLogger(RemoteMemberController.class);
   @Autowired
   private RestTemplate restTemplate;

   // Create
   @PostMapping("")
   public String create(@Valid @RequestBody Member object) {
       HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Member> entity = new HttpEntity<Member>(object,headers);

      return restTemplate.exchange("http://localhost:8080/api/member", HttpMethod.POST, entity, String.class).getBody();
   }

   // retrive all
   @GetMapping(value = "")
   public String retreiveAll() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      
      return restTemplate.exchange("http://localhost:8080/api/member", HttpMethod.GET, entity, String.class).getBody();
   }

   // retrive by id
   @GetMapping("/{id}")
   public String findById(@PathVariable(value = "id") Long id) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      logger.info("id"+id);
      return restTemplate.exchange("http://localhost:8080/api/member/"+id, HttpMethod.GET, entity, String.class).getBody();
   }

   // Update
   @PutMapping("/{id}")
   public String update(@PathVariable(value = "id") Long id,
         @Valid @RequestBody Member newObject) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Member> entity = new HttpEntity<Member>(newObject,headers);
   
      return restTemplate.exchange("http://localhost:8080/api/member/"+id, HttpMethod.PUT, entity, String.class).getBody();
   }

   // Delete
   @DeleteMapping("/{id}")
   public String deleteNote(@PathVariable(value = "id") Long id) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Member> entity = new HttpEntity<Member>(headers);

      return restTemplate.exchange(
         "http://localhost:8080/api/member/"+id, HttpMethod.DELETE, entity, String.class).getBody();
   }
}
