package com.example.ogani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ogani.entity.ServiceDetail;
import com.example.ogani.entity.Services;
import com.example.ogani.model.request.CreateServiceRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.ServicesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách dịch vụ")
    public ResponseEntity<List<Services>> getList(){
        List<Services> list = servicesService.getList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary="Tạo dịch vụ")
    public ResponseEntity<?> placeService(@RequestBody CreateServiceRequest request){

        servicesService.placeService(request);

        return ResponseEntity.ok(new MessageResponse("Service Placed Successfully!"));
    }

    @GetMapping("/chef")
    @Operation(summary="Lấy ra danh sách dịch vụ của đầu bếp bằng id")
    public ResponseEntity<List<Services>> getServiceByChef(@RequestParam("chefId") long chefId){
        List<Services> list = servicesService.getServiceByChef(chefId);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail")
    @Operation(summary="Lấy ra danh sách chi tiết dịch vụ của đầu bếp bằng id")
    public ResponseEntity<List<ServiceDetail>> getServiceDetail(@RequestParam("serviceId") long serviceId){
        List<ServiceDetail> list = servicesService.getServiceDetail(serviceId);

        return ResponseEntity.ok(list);
    }
}
