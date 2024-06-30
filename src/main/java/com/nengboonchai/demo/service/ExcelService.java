package com.nengboonchai.demo.service;

import com.nengboonchai.demo.model.User;
import com.nengboonchai.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelService {
    @Autowired
    private UserRepository userRepository;

    public void exportToPdf(HttpServletResponse response) {
        // get all user
//        List<UserDTO> data = userRepo.getUserList();
//
//        // export to pdf
//        userExportToPdfService.exportToPDF(response, data);
    }

    public void exportToExcel(HttpServletResponse response) {
    }
}
