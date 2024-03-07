package com.example.demo.model;

import com.example.demo.dto.PencilDto;
import com.example.demo.dto.StudentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "id")
    private Teacher teacher;

    public Student(StudentDto dto){
        this.name = dto.getName();
    }
}
