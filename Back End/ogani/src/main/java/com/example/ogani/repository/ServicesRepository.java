package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>{
    
    @Query(value = "SELECT * FROM service WHERE chef_id = :chefId ORDER BY service_date ASC", nativeQuery = true)
    List<Services> getServiceByChef(@Param("chefId") long chefId);
    
}
