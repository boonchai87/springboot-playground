package com.nengboonchai.demo.service;

import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService{
    @Autowired
    private UserRepository userRepository;

    //@Autowired
    private BCryptPasswordEncoder bcryptEncoder=new BCryptPasswordEncoder();
    //private PasswordEncoder bcryptEncoder=new PasswordEncoder();

    public Page<User> findPaginate(int pageNum, int pageSize, String sortField, String sortDirection){
        System.out.println("pageNum="+pageNum+",pageSize ="+pageSize+",sortField="+sortField+",sortDirection="+sortDirection);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        if( sortField!=null && !sortField.trim().equals("") ) {
            if( sortDirection!=null && (sortDirection.equals("desc") || sortDirection.equals("asc")) ) {
                Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                        Sort.by(sortField).descending();
                pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            }/*else{
                pageable = PageRequest.of(pageNum - 1, pageSize);
            }*/
        }
        System.out.println(pageable);
        return this.userRepository.findAll(pageable);
    }
}
