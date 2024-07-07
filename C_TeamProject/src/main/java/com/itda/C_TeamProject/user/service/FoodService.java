package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.data.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAllFoods();
    Food saveFood(Food food);
}
