package com.example.rest.app.service.impl;

import com.example.rest.app.model.Department;
import com.example.rest.app.repository.DepartmentRepository;
import com.example.rest.app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department createDepartment(Department department) {
        Department department1 = departmentRepository.save(department);
        return department1;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}