package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByTeacherName(String name);

    @Query("FROM Student WHERE teacher.name=:name")
    List<Student> getStudentsByTeacherName(String name);
}
