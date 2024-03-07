package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "tbl_employee")
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // de la hibernate 5x nu mai mere cu auto classic, trebuie identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
//    @JsonProperty("some_other_name")
    @NotNull(message = "name should not be null")
    private String name;
//    @JsonIgnore
    private Long age = 0L;  //default value 0
    private String location;
    @Email(message = "please enter the valid email address")
    private String email;
//    @Column(name = "department_column")
    @NotNull(message = "department should not be null")
    private String department;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @JoinColumn(name = "id")
    @OneToOne()
    private Department department_emp;
}
