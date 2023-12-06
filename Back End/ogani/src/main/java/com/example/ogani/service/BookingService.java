package com.example.ogani.service;

import java.util.List;

import com.example.ogani.entity.Booking;
import com.example.ogani.model.request.CreateBookingRequest;

public interface BookingService {
    void placeBooking(CreateBookingRequest request);

    List<Booking> getListByChefId(long chefId);
}
