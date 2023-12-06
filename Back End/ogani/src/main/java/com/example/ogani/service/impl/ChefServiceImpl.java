package com.example.ogani.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Chef;
import com.example.ogani.entity.Dish;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateChefRequest;
import com.example.ogani.repository.ChefRepository;
import com.example.ogani.repository.DishRepository;
import com.example.ogani.service.ChefService;

@Service
public class ChefServiceImpl implements ChefService{
    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<Chef> getList(){
        // TODO Auto-generated method stub
        return chefRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Chef getChef(long id){
        // TODO Auto-generated method stub
        Chef chef = chefRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));

        return chef;
    }

    @Override
    public Chef createChef(CreateChefRequest request){
        Chef chef = new Chef();
        chef.setName(request.getName());
        chef.setAddress(request.getAddress());
        chef.setStar(request.getStar());
        chef.setReview(request.getReview());
        chef.setImage(request.getImage());
        chef.setWorkingTime(request.getWorkingTime());
        chef.setPrice(request.getPrice());
        chef.setDistance(request.getDistance());
        Set<Dish> dishs = new HashSet<>();
        for(Long dishId : request.getDishs()){
            Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Not Found Dish"));
            dishs.add(dish);
        }
        chef.setDishs(dishs);
        chefRepository.save(chef);
        return chef;
    }

    @Override
    public Chef updateChef(long id, CreateChefRequest request){
        // TODO Auto-generated method stub
        Chef chef= chefRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Chef With Id: " + id));
        chef.setName(request.getName());
        chef.setAddress(request.getAddress());
        chef.setStar(request.getStar());
        chef.setReview(request.getReview());
        chef.setImage(request.getImage());
        chef.setWorkingTime(request.getWorkingTime());
        chef.setPrice(request.getPrice());
        chef.setDistance(request.getDistance());
        chefRepository.save(chef);
        return chef;
    }

    @Override
    public List<Chef> getListRandom(long number){
        // TODO Auto-generated method stub
        List<Chef> list = chefRepository.getListRandom(number);
        return list;
    }

    @Override
    public void deleteChef(long id){
        // TODO Auto-generated method stub
        Chef chef= chefRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Chef With Id: " + id));
        chefRepository.delete(chef);
    }
}
