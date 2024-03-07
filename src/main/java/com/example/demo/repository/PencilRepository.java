package com.example.demo.repository;

import com.example.demo.model.Pencil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PencilRepository extends JpaRepository<Pencil, Long> {
}
