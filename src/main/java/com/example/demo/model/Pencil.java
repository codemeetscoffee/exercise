package com.example.demo.model;

import com.example.demo.dto.PencilDto;
import com.example.demo.dto.StudentDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pencil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Pencil(String pencilName){
        this.name = pencilName;
    }
}
