package com.solxyz.irohaapp.service;

import com.solxyz.irohaapp.entity.Department;
import com.solxyz.irohaapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    public List<Department> getDepartmentList() {
        return repository.findAll();
    }

}
