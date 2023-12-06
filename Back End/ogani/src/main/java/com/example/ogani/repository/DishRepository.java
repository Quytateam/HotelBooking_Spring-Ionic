package com.example.ogani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ogani.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long>{
    
    @Query(value = "SELECT * FROM Dish WHERE ROWNUM <= :number ORDER BY id DESC", nativeQuery = true)
    List<Dish> getListNewest(@Param("number") int number);

    @Query(value = "SELECT * FROM Dish WHERE ROWNUM <= 8 ORDER BY price", nativeQuery = true)
    List<Dish> getListByPrice();

    @Query(value = "SELECT * FROM Dish WHERE category_id = :id ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 4 ROWS ONLY"
            , nativeQuery = true)
    List<Dish> findRelatedDish(@Param("id") long id);

    @Query(value = "SELECT * FROM Dish WHERE category_id = :id", nativeQuery = true)
    List<Dish> getListDishByCategory(@Param("id") long id);

    @Query(value = "SELECT * FROM Dish WHERE category_id = :id AND price BETWEEN :min AND :max", nativeQuery = true)
    List<Dish> getListDishByPriceRange(@Param("id") long id, @Param("min") int min, @Param("max") int max);

    @Query(value = "SELECT p FROM Dish p WHERE p.name LIKE %:keyword% ORDER BY p.id DESC")
    List<Dish> searchDish(@Param("keyword") String keyword);
}
