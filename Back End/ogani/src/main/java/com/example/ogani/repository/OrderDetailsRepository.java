package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Query(value = "Select * from order_details WHERE order_id = :orderId", nativeQuery = true)
    List<OrderDetails> getListDetails(@Param("orderId") long orderId);
}
