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

import com.example.ogani.entity.Chef;
import com.example.ogani.model.request.CreateChefRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.service.ChefService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/chef")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChefController {
    @Autowired
    private ChefService chefService;

    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách đầu bếp")
    public ResponseEntity<List<Chef>> getList(){
        List<Chef> list = chefService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary="Lấy đầu bếp bằng id")
    public ResponseEntity<Chef> getChef(@PathVariable long id){
        Chef Chef = chefService.getChef(id);

        return ResponseEntity.ok(Chef);
    }

    @PostMapping("/create")
    @Operation(summary="Tạo mới đầu bếp")
    public ResponseEntity<Chef> createChef(@RequestBody CreateChefRequest request){
        Chef Chef = chefService.createChef(request);

        return ResponseEntity.ok(Chef);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm đầu bếp bằng id và cập nhật đầu bếp đó")
    public ResponseEntity<Chef> updateChef(@PathVariable long id,@RequestBody CreateChefRequest request){
        Chef Chef = chefService.updateChef(id, request);

        return ResponseEntity.ok(Chef);
    }

    @GetMapping("/random")
    @Operation(summary="Lấy ra danh sách ngẫu nhiên number đầu bếp")
    public ResponseEntity<List<Chef>> getListRandom(@RequestParam("number") long number){
        List<Chef> list = chefService.getListRandom(number);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa đầu bếp bằng id")
    public ResponseEntity<?> deleteChef(@PathVariable long id){
        chefService.deleteChef(id);

        return ResponseEntity.ok(new MessageResponse("Chef is delete"));
    }
}
