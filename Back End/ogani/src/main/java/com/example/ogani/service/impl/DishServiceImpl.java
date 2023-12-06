package com.example.ogani.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Category;
import com.example.ogani.entity.Dish;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateDishRequest;
import com.example.ogani.model.request.UpdateQuantityDishRequest;
import com.example.ogani.repository.CategoryRepository;
import com.example.ogani.repository.DishRepository;
import com.example.ogani.service.DishService;

@Service
public class DishServiceImpl implements DishService{
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Dish> getList() {
        // TODO Auto-generated method stub
        return dishRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Dish getDish(long id) {
        // TODO Auto-generated method stub
        Dish dish= dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));

        return dish;
    }

    @Override
    public Dish createDish(CreateDishRequest request) {
        // TODO Auto-generated method stub
        Dish dish = new Dish();
        dish.setImage(request.getImage());
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        dish.setCategory(category);
        dish.setTime(request.getTime());
        dish.setDistance(request.getDistance());
        dish.setQuantity(request.getQuantity());
        dish.setDescription(request.getDescription());
        dishRepository.save(dish);
        return dish;
    }

    @Override
    public Dish updateDish(long id, CreateDishRequest request) {
        // TODO Auto-generated method stub
        Dish dish= dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Dish With Id: " + id));
        dish.setImage(request.getImage());
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        dish.setCategory(category);
        dish.setTime(request.getTime());
        dish.setDistance(request.getDistance());
        dish.setQuantity(request.getQuantity());
        dish.setDescription(request.getDescription());
        dishRepository.save(dish);

        return dish;
    }

    @Override
    public Dish updateQuantityDish(long id, UpdateQuantityDishRequest request) {
        // TODO Auto-generated method stub
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        int currentQuantity = dish.getQuantity();
        int newQuantity = currentQuantity + request.getQuantity();
        dish.setQuantity(newQuantity);
        dishRepository.save(dish);

        return dish;
    }

    @Override
    public void deleteDish(long id) {
        // TODO Auto-generated method stub
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        dishRepository.delete(dish);
    }

    @Override
    public List<Dish> getListNewest(int number) {
        // TODO Auto-generated method stub
        List<Dish> list = dishRepository.getListNewest(number);
        return list;
    }

    @Override
    public List<Dish> getListByPrice() {
        // TODO Auto-generated method stub
        return dishRepository.getListByPrice();
    }

    @Override
    public List<Dish> findRelatedDish(long id){
        List<Dish> list = dishRepository.findRelatedDish(id);
        return list;

    }

    @Override
    public List<Dish> getListDishByCategory(long id){
        List<Dish> list =dishRepository.getListDishByCategory(id);
        return list;
    }

    @Override
    public List<Dish> getListByPriceRange(long id,int min, int max){
        List<Dish> list =dishRepository.getListDishByPriceRange(id, min, max);
        return list;
    }

    @Override
    public List<Dish> searchDish(String keyword) {
        // TODO Auto-generated method stub
        List<Dish> list = dishRepository.searchDish(keyword);
        return list;
    }
}
