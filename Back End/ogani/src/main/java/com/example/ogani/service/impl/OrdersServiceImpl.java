package com.example.ogani.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Orders;
import com.example.ogani.entity.Dish;
import com.example.ogani.entity.OrderDetails;
import com.example.ogani.entity.Users;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateOrderDetailRequest;
import com.example.ogani.model.request.CreateOrderRequest;
import com.example.ogani.repository.DishRepository;
import com.example.ogani.repository.OrderDetailsRepository;
import com.example.ogani.repository.OrdersRepository;
import com.example.ogani.repository.UsersRepository;
import com.example.ogani.service.OrdersService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public void placeOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Date date = java.sql.Date.valueOf(LocalDate.now());
        String currentDate = new SimpleDateFormat("dd-MM-YY").format(date);
        Users users = usersRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("Not Found User With Id:" + request.getUserId())); 
        long userId = users.getId();

       Orders o= ordersRepository.existsByDateOrder(currentDate,userId);
        if(o!= null){
            // SimpleDateFormat sdf = new SimpleDateFormat("DD-Mon-YY");
            // String formattedDate = sdf.format(currentDate);
            long orderId = ordersRepository.getOrderExist(currentDate,userId);
            Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Not Found User With Id:" + orderId)); 
            long totalPrice = orders.getTotalPrice();
            for(CreateOrderDetailRequest rq: request.getOrderDetails()){
                OrderDetails orderDetails = new OrderDetails();
                Dish dish = dishRepository.findById(rq.getDishId()).orElseThrow(() -> new NotFoundException("Not Found Dish With Id:" + rq.getDishId()));
                orderDetails.setOrder(orders);
                orderDetails.setDish(dish);
                orderDetails.setPrice(dish.getPrice());
                orderDetails.setQuantity(rq.getQuantity());
                orderDetails.setSubTotal(dish.getPrice()* rq.getQuantity());
                orderDetails.setEnable(0);
                totalPrice += orderDetails.getSubTotal();
                orderDetailsRepository.save(orderDetails);
                
            }
            orders.setTotalPrice(totalPrice);
            ordersRepository.save(orders);
        }else{
            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setAddress(request.getAddress());
            orders.setEmail(users.getEmail());
            orders.setPhone(users.getPhone());
            orders.setDate(java.sql.Date.valueOf(LocalDate.now()));
            ordersRepository.save(orders);
            long totalPrice = 0;
            for(CreateOrderDetailRequest rq: request.getOrderDetails()){
                OrderDetails orderDetails = new OrderDetails();
                Dish dish = dishRepository.findById(rq.getDishId()).orElseThrow(() -> new NotFoundException("Not Found Dish With Id:" + rq.getDishId()));
                orderDetails.setOrder(orders);
                orderDetails.setDish(dish);
                orderDetails.setPrice(dish.getPrice());
                orderDetails.setQuantity(rq.getQuantity());
                orderDetails.setSubTotal(dish.getPrice()* rq.getQuantity());
                orderDetails.setEnable(0);
                totalPrice += orderDetails.getSubTotal();
                orderDetailsRepository.save(orderDetails);
                
            }
            orders.setTotalPrice(totalPrice);
            ordersRepository.save(orders);
        }
    }

    @Override
    public List<Orders> getOrdersByUserDESC(long userId) {
        return ordersRepository.getOrdersByUserDESC(userId);
    }

    @Override
    public List<Orders> getOrdersByUserASC(long userId) {
        return ordersRepository.getOrdersByUserASC(userId);
    }

    @Override
    public List<OrderDetails> getListDetails(long orderId) {
        return orderDetailsRepository.getListDetails(orderId);
    }

}
