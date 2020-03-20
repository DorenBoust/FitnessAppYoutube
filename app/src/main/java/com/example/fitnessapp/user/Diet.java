package com.example.fitnessapp.user;

import java.util.List;

public class Diet {
    private String numberOfMeals;
    private List<Meal> meals;

    public Diet(String numberOfMeals, List<Meal> meals) {
        this.numberOfMeals = numberOfMeals;
        this.meals = meals;
    }

    public String getNumberOfMeals() {
        return numberOfMeals;
    }
    public void setNumberOfMeals(String numberOfMeals) {
        this.numberOfMeals = numberOfMeals;
    }
    public List<Meal> getMeals() {
        return meals;
    }
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "numberOfMeals='" + numberOfMeals + '\'' +
                ", meals=" + meals +
                '}';
    }
}
