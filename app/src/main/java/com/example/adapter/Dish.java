package com.example.adapter;

public class Dish {
    public String name = "";
    public String ingredients_list = "";
    public String steps_list = "";
    public String strURL = "";
    public Dish(){}
    public Dish(String name, String ingredientsList, String stepsList, String strURL) {
        this.name = name;
        this.ingredients_list = ingredientsList;
        this.steps_list = stepsList;
        this.strURL = strURL;
    }
}
