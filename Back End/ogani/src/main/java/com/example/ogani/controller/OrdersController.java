package com.example.ogani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ogani.entity.OrderDetails;
import com.example.ogani.entity.Orders;
import com.example.ogani.model.request.CreateOrderRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.OrdersService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/create")
    @Operation(summary="Đặt món ăn")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest request){

        ordersService.placeOrder(request);

        return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
    }

    @GetMapping("/userup")
    @Operation(summary="Lấy ra danh sách đặt hàng cập nhật mới nhất của người dùng bằng id")
    public ResponseEntity<List<Orders>> getOrdersByUserDESC(@RequestParam("userId") long userId){
        List<Orders> list = ordersService.getOrdersByUserDESC(userId);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/userpast")
    @Operation(summary="Lấy ra danh sách đặt hàng của người dùng bằng id")
    public ResponseEntity<List<Orders>> getOrdersByUserASC(@RequestParam("userId") long userId){
        List<Orders> list = ordersService.getOrdersByUserASC(userId);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail")
    @Operation(summary="Lấy ra chi tiết đơn hàng")
    public ResponseEntity<List<OrderDetails>> getDetails(@RequestParam("orderId") long orderId){
        List<OrderDetails> list = ordersService.getListDetails(orderId);

        return ResponseEntity.ok(list);
    }
}
