package com.example.ogani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ogani.entity.Booking;
import com.example.ogani.model.request.CreateBookingRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    @Operation(summary="Đặt trước")
    public ResponseEntity<?> placeOrder(@RequestBody CreateBookingRequest request){

        bookingService.placeBooking(request);

        return ResponseEntity.ok(new MessageResponse("Booking Placed Successfully!"));
    }

    @GetMapping("/chef/{id}")
    @Operation(summary="Lấy ra danh sách đầu bếp được đặt trước theo id")
    public ResponseEntity<List<Booking>> getListByChefId(@PathVariable long id){
        List<Booking> list = bookingService.getListByChefId(id);

        return ResponseEntity.ok(list);
    }
}
