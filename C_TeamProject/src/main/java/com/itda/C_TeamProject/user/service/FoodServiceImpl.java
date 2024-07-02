package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.data.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }
}