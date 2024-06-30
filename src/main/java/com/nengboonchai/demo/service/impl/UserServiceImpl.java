package com.nengboonchai.demo.service.impl;

import com.nengboonchai.demo.dto.UserDto;
import com.nengboonchai.demo.model.Role;
import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.repository.RoleRepository;
import com.nengboonchai.demo.repository.UserRepository;
import com.nengboonchai.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        //System.out.println(pageable);
        return this.userRepository.findAll(pageable);
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
