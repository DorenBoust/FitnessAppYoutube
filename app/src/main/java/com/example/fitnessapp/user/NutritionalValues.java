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
                String unit = product.getUnit();
                double qty = Double.parseDouble(product.getQty());


                ProductDataBase productDetails = productDataBase.get(productName);

                switch (unit){
                    case "גרם":

                        cal += (qty / 100.0) * productDetails.getCalories();
                        pro += (qty / 100.0) * productDetails.getProteins();
                        fat += (qty / 100.0) * productDetails.getFats();
                        carboh += (qty / 100.0) * productDetails.getCarbohydrates();

                        break;

                    case "כפיות":

                        cal += (qty / 5.0) * productDetails.getCalories();
                        pro += (qty / 5.0) * productDetails.getProteins();
                        fat += (qty / 5.0) * productDetails.getFats();
                        carboh += (qty / 5.0) * productDetails.getCarbohydrates();

                        break;

                    case "כפות":

                        cal += (qty / 10.0) * productDetails.getCalories();
                        pro += (qty / 10.0) * productDetails.getProteins();
                        fat += (qty / 10.0) * productDetails.getFats();
                        carboh += (qty / 10.0) * productDetails.getCarbohydrates();

                        break;

                    case "כוסות":

                        cal += (qty * 2) * productDetails.getCalories(); // avrage gram per cup 200 gram. if have (3 cups * 2) * 100 gram = 600
                        pro += (qty * 2) * productDetails.getProteins();
                        fat += (qty * 2) * productDetails.getFats();
                        carboh += (qty * 2) * productDetails.getCarbohydrates();

                        break;

                    case "יחידות":

                        cal += ((qty * productDetails.getAvrageGram()) / 100) * productDetails.getCalories();  // ((2 unit * 250 gram per unit) /100) * 100 gram
                        pro += ((qty * productDetails.getAvrageGram()) / 100) * productDetails.getProteins();
                        fat += ((qty * productDetails.getAvrageGram()) / 100) * productDetails.getFats();
                        carboh += ((qty * productDetails.getAvrageGram()) / 100) * productDetails.getCarbohydrates();

                        break;



                }



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
