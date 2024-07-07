package com.itda.C_TeamProject.user.controller;


import com.itda.C_TeamProject.user.data.FoodDAO;
import com.itda.C_TeamProject.user.data.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodDAO foodDAO;

    @Autowired
    public FoodController(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return foodDAO.getAllFoods();
    }

    @PostMapping
    public FoodDTO addFood(@RequestBody FoodDTO foodDTO) {
        return foodDAO.saveFood(foodDTO);
    }
}