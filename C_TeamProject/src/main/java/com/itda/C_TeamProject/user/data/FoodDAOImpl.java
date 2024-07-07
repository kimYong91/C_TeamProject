package com.itda.C_TeamProject.user.data;


import com.itda.C_TeamProject.user.service.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FoodDAOImpl implements FoodDAO {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodDAOImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public FoodDTO saveFood(FoodDTO foodDTO) {
        Food food = convertToEntity(foodDTO);
        Food savedFood = foodRepository.save(food);
        return convertToDTO(savedFood);
    }

    private FoodDTO convertToDTO(Food food) {
        return new FoodDTO(
                food.getName(),
                food.getEnergy(),
                food.getProtein(),
                food.getFat(),
                food.getCarbohydrate(),
                food.getSugars(),
                food.getSodium()
        );
    }

    private Food convertToEntity(FoodDTO foodDTO) {
        return new Food(
                foodDTO.get식품명(),
                foodDTO.get에너지(),
                foodDTO.get단백질(),
                foodDTO.get지방(),
                foodDTO.get탄수화물(),
                foodDTO.get당류(),
                foodDTO.get나트륨()
        );
    }
}