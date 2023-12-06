package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.Chef;

@Repository
public interface ChefRepository extends JpaRepository<Chef,Long>{
    @Query(value = "SELECT * FROM chef ORDER BY DBMS_RANDOM.VALUE FETCH FIRST :number ROWS ONLY", nativeQuery = true)
    List<Chef> getListRandom(@Param("number") long number);
}
