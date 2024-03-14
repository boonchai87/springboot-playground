package com.nengboonchai.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumeService {
//    @Autowired
//    RestTemplate restTemplate;


//    public String getProductList() {
//       HttpHeaders headers = new HttpHeaders();
//       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//       HttpEntity <String> entity = new HttpEntity<String>(headers);
      
//       return restTemplate.exchange("http://localhost:8080/api/member", HttpMethod.GET, entity, String.class).getBody();
//    }
}
