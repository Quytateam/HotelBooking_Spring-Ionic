package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.Category;
import com.example.ogani.model.request.CreateCategoryRequest;

public interface CategoryService {
    List<Category> findAll();

    Category createCategory(CreateCategoryRequest request);

    void deleteCategory(long id);
}
