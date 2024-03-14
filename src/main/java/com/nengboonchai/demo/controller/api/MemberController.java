package com.nengboonchai.demo.controller.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nengboonchai.demo.model.Member;
import com.nengboonchai.demo.repository.MemberRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/member")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin// allow all endpoints
public class MemberController {
	@Autowired
    private MemberRepository repository;
	
    // Create
	@PostMapping("")
	public ResponseEntity create(@Valid @RequestBody Member object) {
        Member savedEntity = repository.save(object);
		return ResponseEntity.ok(savedEntity);
	}

    // retrive all
    @GetMapping("")
    public Page<Member> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    // retrive by id
    @GetMapping("/{id}")
    public Member findById(@PathVariable(value = "id") Long id) {
		return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    // Update
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable(value = "id") Long id,
						   @Valid @RequestBody Member newObject) {

	    Member oldObject = repository.findById(id).orElseThrow(RuntimeException::new);

	    oldObject.setFirstName(newObject.getFirstName());
	    oldObject.setEmail(newObject.getEmail());

	    Member updateObject = repository.save(oldObject);
	    return ResponseEntity.ok(updateObject);
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity deleteNote(@PathVariable(value = "id") Long id) {
	    Member oldObject = repository.findById(id).orElseThrow(RuntimeException::new);

	    repository.delete(oldObject);
	    return ResponseEntity.ok().build();
	}

}
