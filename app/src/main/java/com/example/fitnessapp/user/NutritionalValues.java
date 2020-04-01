package com.example.fitnessapp.user;

import java.util.Dictionary;
import java.util.List;

public class NutritionalValues {

    private int cal;
    private int pro;
    private int fat;
    private int carboh;


    public NutritionalValues(User user) {


        Diet diet = user.getDiet();
        List<Meal> meals = diet.getMeals();
        Dictionary<String, ProductDataBase> productDataBase = user.getProductDataBase();

        int cal = 0;
        int pro = 0;
        int fat = 0;
        int carboh = 0;

        for (Meal meal : meals) {

            List<Product> products = meal.getProducts();

            for (Product product : products) {

                String productName = product.getProductName();
                ProductDataBase productDetails = productDataBase.get(productName);

                cal += productDetails.getCalories();
                pro += productDetails.getProteins();
                fat += productDetails.getFats();
                carboh += productDetails.getCarbohydrates();

            }


        }

        this.cal = cal;
        this.pro = pro;
        this.fat = fat;
        this.carboh = carboh;

    }


    public int getCal() {
        return cal;
    }
    public void setCal(int cal) {
        this.cal = cal;
    }
    public double getPro() {
        return pro;
    }
    public void setPro(int pro) {
        this.pro = pro;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(int fat) {
        this.fat = fat;
    }
    public double getCarboh() {
        return carboh;
    }
    public void setCarboh(int carboh) {
        this.carboh = carboh;
    }

    @Override
    public String toString() {
        return "NutritionalValues{" +
                "cal=" + cal +
                ", pro=" + pro +
                ", fat=" + fat +
                ", carboh=" + carboh +
                '}';
    }
}
