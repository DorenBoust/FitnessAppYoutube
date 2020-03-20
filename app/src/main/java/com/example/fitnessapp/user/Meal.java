package com.example.fitnessapp.user;

import java.util.List;

public class Meal {
    private String name;
    private String time;
    private String numberOfProduct;
    private List<Product> products;

    public Meal(String name, String time, String numberOfProduct, List<Product> products) {
        this.name = name;
        this.time = time;
        this.numberOfProduct = numberOfProduct;
        this.products = products;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getNumberOfProduct() {
        return numberOfProduct;
    }
    public void setNumberOfProduct(String numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", numberOfProduct='" + numberOfProduct + '\'' +
                ", products=" + products +
                '}';
    }
}
