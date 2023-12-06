package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.Dish;
import com.example.ogani.model.request.CreateDishRequest;
import com.example.ogani.model.request.UpdateQuantityDishRequest;

public interface DishService {
    List<Dish> getList();

    List<Dish> getListNewest(int number);

    List<Dish> getListByPrice();

    List<Dish> findRelatedDish(long id);

    List<Dish> getListDishByCategory(long id);

    List<Dish> getListByPriceRange(long id,int min, int max);

    List<Dish> searchDish(String keyword);

    Dish getDish(long id);

    Dish createDish(CreateDishRequest request);

    Dish updateDish(long id, CreateDishRequest request);
    
    Dish updateQuantityDish(long id, UpdateQuantityDishRequest request);

    void deleteDish(long id);
}
