package com.itda.C_TeamProject.user.data;

import java.util.List;

public interface FoodDAO {
    List<FoodDTO> getAllFoods();
    FoodDTO saveFood(FoodDTO foodDTO);
}