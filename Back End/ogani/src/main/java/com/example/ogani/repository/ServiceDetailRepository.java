package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.ServiceDetail;

@Repository
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Long>{
    @Query(value = "SELECT * FROM service_detail WHERE service_id = :serviceId ORDER BY dish_id DESC", nativeQuery = true)
    List<ServiceDetail> getServiceDetail(@Param("serviceId") long serviceId);
}
