package com.example.rest.app.service;

import com.example.rest.app.model.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long id);

    Department createDepartment(Department department);

    List<Department> findAll();

    void deleteById(Long id);


}