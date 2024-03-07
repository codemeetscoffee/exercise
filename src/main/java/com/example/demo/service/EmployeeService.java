package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {
     List<Employee> getEmployees(Integer pageNumber, Integer pageSize);
     Employee saveEmployee(Employee employee);
     Employee getSingleEmployee(Long id);
     void deleteEmployee(Long id);
     Employee updateEmployee(Long id, Employee employee);
     List<Employee> getEmployeesByName(String name);
     List<Employee> getEmployeeByNameAndLocation(String name, String location);
     List<Employee> getEmployeesByKeyword(String name);
     List<Employee> getAndSortEmployeesByKeyword(String name);
}
