package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.ServiceDetail;
import com.example.ogani.entity.Services;
import com.example.ogani.model.request.CreateServiceRequest;

public interface ServicesService {

    List<Services> getList();

    void placeService(CreateServiceRequest request);

    List<Services> getServiceByChef(long id);

    List<ServiceDetail> getServiceDetail(long id);
}
