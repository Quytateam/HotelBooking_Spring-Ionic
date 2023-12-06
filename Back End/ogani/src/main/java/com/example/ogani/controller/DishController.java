package com.example.ogani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ogani.entity.Dish;
import com.example.ogani.model.request.CreateDishRequest;
import com.example.ogani.model.request.UpdateQuantityDishRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.DishService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/dish")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách món ăn")
    public ResponseEntity<List<Dish>> getList(){
        List<Dish> list = dishService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/newest/{number}")
    @Operation(summary="Lấy ra danh sách món ăn mới nhất giới hạn số lượng = number")
    public ResponseEntity<List<Dish>> getListNewest(@PathVariable int number){
        List<Dish> list = dishService.getListNewest(number);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/price")
    @Operation(summary="Lấy ra danh sách 8 món ăn có giá từ thấp nhất đến cao")
    public ResponseEntity<List<Dish>> getListByPrice(){
        List<Dish> list = dishService.getListByPrice();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/related/{id}")
    @Operation(summary="Lấy ra ngẫu nhiên 4 món ăn bằng category_id")
    public ResponseEntity<List<Dish>> getListRelatedDish(@PathVariable long id){
        List<Dish> list = dishService.findRelatedDish(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{id}")
    @Operation(summary="Lấy ra danh sách món ăn bằng id của danh mục")
    public ResponseEntity<List<Dish>> getListDishByCategory(@PathVariable long id){
        List<Dish> list =  dishService.getListDishByCategory(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/range")
    @Operation(summary="Lấy ra danh sách món ăn ở các mức giá từ min đến max")
    public ResponseEntity<List<Dish>> getListDishByPriceRange(@RequestParam("id") long id,@RequestParam("min") int min, @RequestParam("max") int max){
        List<Dish> list = dishService.getListByPriceRange(id, min, max);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary="Lấy món ăn bằng id")
    public ResponseEntity<Dish> getDish(@PathVariable long id){
        Dish Dish = dishService.getDish(id);

        return ResponseEntity.ok(Dish);
    }

    @GetMapping("/search")
    @Operation(summary="Tìm kiếm món ăn bằng keyword")
    public ResponseEntity<List<Dish>> searchDish(@RequestParam("keyword") String keyword){
        List<Dish> list = dishService.searchDish(keyword);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary="Tạo mới món ăn")
    public ResponseEntity<Dish> createDish(@RequestBody CreateDishRequest request){
        Dish Dish = dishService.createDish(request);

        return ResponseEntity.ok(Dish);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm món ăn bằng id và cập nhật món ăn đó")
    public ResponseEntity<Dish> updateDish(@PathVariable long id,@RequestBody CreateDishRequest request){
        Dish Dish = dishService.updateDish(id, request);

        return ResponseEntity.ok(Dish);
    }

    @PutMapping("/update/quantity/{id}")
    @Operation(summary="Tìm món ăn bằng id và cập nhật món ăn đó")
    public ResponseEntity<Dish> updateQuantityDish(@PathVariable long id,@RequestBody UpdateQuantityDishRequest request){
        Dish Dish = dishService.updateQuantityDish(id, request);

        return ResponseEntity.ok(Dish);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa món ăn bằng id")
    public ResponseEntity<?> deleteDish(@PathVariable long id){
        dishService.deleteDish(id);

        return ResponseEntity.ok(new MessageResponse("Dish is delete"));
    }
}
