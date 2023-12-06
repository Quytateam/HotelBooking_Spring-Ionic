package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.Chef;
import com.example.ogani.model.request.CreateChefRequest;

public interface ChefService {
    List<Chef> getList();

    Chef getChef(long id);

    Chef createChef(CreateChefRequest request);

    Chef updateChef(long id, CreateChefRequest request);

    List<Chef> getListRandom(long number);

    void deleteChef(long id);
}
