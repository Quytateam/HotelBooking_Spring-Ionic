package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query(value = "SELECT * FROM booking WHERE chef_id = :chefId ORDER BY id DESC", nativeQuery = true)
    List<Booking> getBookingByChefId(@Param("chefId") long chefId);
}
