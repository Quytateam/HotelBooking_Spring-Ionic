package com.example.ogani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}
