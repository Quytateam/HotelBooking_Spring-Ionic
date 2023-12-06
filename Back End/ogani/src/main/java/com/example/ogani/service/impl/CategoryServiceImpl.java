package com.example.ogani.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Category;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateCategoryRequest;
import com.example.ogani.repository.CategoryRepository;
import com.example.ogani.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        List<Category> list = categoryRepository.findAll(Sort.by("id").descending());
        return list;
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        // TODO Auto-generated method stub
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        categoryRepository.delete(category);
    }
    
}
