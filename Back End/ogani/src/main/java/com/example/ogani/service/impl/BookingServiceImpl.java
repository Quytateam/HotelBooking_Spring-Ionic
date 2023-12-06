package com.example.ogani.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ogani.entity.Booking;
import com.example.ogani.entity.Chef;
import com.example.ogani.entity.Users;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.CreateBookingRequest;
import com.example.ogani.repository.BookingRepository;
import com.example.ogani.repository.ChefRepository;
import com.example.ogani.repository.UsersRepository;
import com.example.ogani.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private ChefRepository chefRepository;

    @Override
    public void placeBooking(CreateBookingRequest request) {
        // TODO Auto-generated method stub
        Booking booking = new Booking();
        Users users = usersRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("Not Found User With Id:" + request.getUserId()));
        Chef chef = chefRepository.findById(request.getChefId()).orElseThrow(() -> new NotFoundException("Not Found User With Id:" + request.getChefId()));
        booking.setUsers(users);
        booking.setChef(chef);
        booking.setTitle(request.getTitle());
        booking.setDescription(request.getDescription());
        booking.setImage(request.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        try{
            Date startTime = dateFormat.parse(request.getStartTime());
            Date endTime = dateFormat.parse(request.getEndTime());
            booking.setStartTime(startTime);
            booking.setEndTime(endTime);
            booking.setEmail(users.getEmail());
            booking.setPhone(users.getPhone());
            booking.setAddress(request.getAddress());
            java.time.Instant startInstant = startTime.toInstant();
            java.time.Instant endInstant = endTime.toInstant();
            Duration duration = Duration.between(startInstant, endInstant);
            double hours = duration.toHoursPart(); // Lấy phần giờ thập phân
            double totalPrice = Math.ceil(hours) * chef.getPrice();
            booking.setTotalPrice(totalPrice);
            booking.setDate(new Date());
            bookingRepository.save(booking);
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> getListByChefId(long chefId) {
        return bookingRepository.getBookingByChefId(chefId);
    }
}
