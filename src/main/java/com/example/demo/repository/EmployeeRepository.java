package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findByName(String name);
    List<Employee> findByNameAndLocation(String name, String location);

    //containing e keyword
    //select * from table where name like "%cri%"
    List<Employee> findByNameContaining(String keyword);
    List<Employee> findByNameContaining(String keyword, Sort sort);

    @Query("FROM Employee WHERE name=:name OR location=:location")
    List<Employee> getEmployeeByNameAndLocation(
            /*@Param sa bindui alt nume de param*/
            String name, String location
    );

    @Transactional//necesar
    @Modifying //necesar la orice operatie care modifica put post delete, nu trebe la get
    @Query("DELETE FROM Employee WHERE name=:name")
    Integer deleteEmployeeByName(String name);
}
