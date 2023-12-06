package com.example.ogani.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Blog;
import com.example.ogani.entity.Category;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateBlogRequest;
import com.example.ogani.repository.BlogRepository;
import com.example.ogani.repository.CategoryRepository;
import com.example.ogani.service.BlogService;
import java.sql.Timestamp;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Blog> getList() {
        // TODO Auto-generated method stub
        return blogRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Blog getBlog(long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        return blog;
    }

    @Override
    public Blog createBlog(CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = new Blog();
        blog.setImage(request.getImage());
        blog.setTitle(request.getTitle());
        blog.setCreateAt(new Timestamp(System.currentTimeMillis()));
        blog.setDescription(request.getDescription());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        blog.setCategory(category);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(long id, CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.setImage(request.getImage());
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public void deleteBlog(long id) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blogRepository.delete(blog);
    }

    @Override
    public List<Blog> getListNewest(int limit) {
        // TODO Auto-generated method stub
        List<Blog> list = blogRepository.getListNewest(limit);
        return list;
    }


}
