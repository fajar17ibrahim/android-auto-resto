package com.autoresto.model;

import java.util.List;

public class Menu {

    private List<Food> foodList;

    private List<Drink> drinkList;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Drink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }

    public Menu(List<Food> foodList, List<Drink> drinkList) {
        this.foodList = foodList;
        this.drinkList = drinkList;
    }


}
