package com.example.ogani.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Chef;
import com.example.ogani.entity.Dish;
import com.example.ogani.entity.ServiceDetail;
import com.example.ogani.entity.ServiceDetailId;
import com.example.ogani.entity.Services;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateServiceDetailRequest;
import com.example.ogani.model.request.CreateServiceRequest;
import com.example.ogani.repository.ChefRepository;
import com.example.ogani.repository.DishRepository;
import com.example.ogani.repository.ServiceDetailRepository;
import com.example.ogani.repository.ServicesRepository;
import com.example.ogani.service.ServicesService;

import java.util.List;
import java.util.Locale;

@Service
public class ServicesServiceImpl implements ServicesService{

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServiceDetailRepository serviceDetailRepository;

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private DishRepository dishRepository;
    
    @Override
    public void placeService(CreateServiceRequest request) {
        Services service = new Services();
        Chef chef = chefRepository.findById(request.getChefId()).orElseThrow(() -> new NotFoundException("Not Found Chef With Id:" + request.getChefId()));
        service.setChef(chef);
        // SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);
        try{
            Date serviceDate = dateFormat.parse(request.getServiceDate());
            service.setServiceDate(serviceDate);
            servicesRepository.save(service);
            Set<ServiceDetail> serviceDetails = new HashSet<>();
            for(CreateServiceDetailRequest rq: request.getServiceDetail()){
                ServiceDetailId serviceDetailsId = new ServiceDetailId();
                ServiceDetail serviceDetail = new ServiceDetail();
                Dish dish = dishRepository.findById(rq.getDishId()).orElseThrow(() -> new NotFoundException("Not Found Dish"));
                serviceDetailsId.setServiceId(service.getId());
                serviceDetailsId.setDishId(dish.getId());
                serviceDetail.setId(serviceDetailsId);
                serviceDetail.setDish(dish);
                serviceDetail.setTime(rq.getTime());
                serviceDetails.add(serviceDetail);
                serviceDetailRepository.save(serviceDetail);
            }
            service.setServicedetail(serviceDetails);
            servicesRepository.save(service);
        }catch(ParseException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Services> getList() {
        return servicesRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Services> getServiceByChef(long id) {
        // TODO Auto-generated method stub

        return servicesRepository.getServiceByChef(id);
    }

    @Override
    public List<ServiceDetail> getServiceDetail(long id) {
        // TODO Auto-generated method stub

        return serviceDetailRepository.getServiceDetail(id);
    }
}
