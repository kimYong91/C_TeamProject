package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.data.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, String> {
}
