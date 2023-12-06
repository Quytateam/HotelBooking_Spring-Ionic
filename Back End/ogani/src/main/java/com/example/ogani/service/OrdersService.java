package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.OrderDetails;
import com.example.ogani.entity.Orders;
import com.example.ogani.model.request.CreateOrderRequest;

public interface OrdersService {
    
    void placeOrder(CreateOrderRequest request);

    List<Orders> getOrdersByUserDESC(long userId);

    List<Orders> getOrdersByUserASC(long userId);

    List<OrderDetails> getListDetails(long orderId);
}
