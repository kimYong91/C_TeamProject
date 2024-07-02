package com.itda.C_TeamProject.user.data;

import jakarta.persistence.*;

@Entity
@Table(name = "diettest")
public class Food {

    @Id
    @Column(name = "식품코드")
    private String id;

    @Column(name = "식품명")
    private String name;

    @Column(name = "에너지(kcal)")
    private float energy;

    @Column(name = "단백질(g)")
    private float protein;

    @Column(name = "지방(g)")
    private float fat;

    @Column(name = "탄수화물(g)")
    private float carbohydrate;

    @Column(name = "당류(g)")
    private float sugars;

    @Column(name = "나트륨(mg)")
    private float sodium;

    public Food() {}

    public Food(String id, String name, float energy, float protein, float fat, float carbohydrate, float sugars, float sodium) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.sugars = sugars;
        this.sodium = sodium;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getEnergy() {
        return energy;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }
}
