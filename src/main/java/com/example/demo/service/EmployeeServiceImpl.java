package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private static List<Employee> list = new ArrayList<>();
    private final EmployeeRepository employeeRepository;


    static {
        Employee e1 = new Employee();
        e1.setName("Cristi");
        e1.setAge(30L);
        list.add(e1);

        Employee e2 = new Employee();
        e2.setName("ovidiu");
        e2.setAge(35L);
        list.add(e2);
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees(Integer pageNumber, Integer pageSize) {
//        return list;
//        return employeeRepository.findAll();
        //pageNumber incepe de la zero 0
        
        //PAGE FARA SORT
//        Pageable pages = PageRequest.of(pageNumber, pageSize);

        //PAGE AND SORT
        //POTI DA MAI MULTE FIELDS LA SORT EXEMPLU "id", "name" etc
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        return employeeRepository.findAll(pages).getContent();
    }


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        throw new RuntimeException("no employee with id " + id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()){
            throw new RuntimeException("no employee with id " + id);
        }
        Employee employeeUpdate = optionalEmployee.get();
        employeeUpdate.setName(employee.getName());
        employeeUpdate.setEmail(employee.getEmail());
        employeeUpdate.setAge(employee.getAge());
        employeeUpdate.setDepartment(employee.getDepartment());
        employeeUpdate.setLocation(employee.getLocation());
       return employeeRepository.save(employeeUpdate);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
       return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeeByNameAndLocation(String name, String location) {
        return employeeRepository.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @Override
    public List<Employee> getAndSortEmployeesByKeyword(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return employeeRepository.findByNameContaining(name,sort);
    }
}
