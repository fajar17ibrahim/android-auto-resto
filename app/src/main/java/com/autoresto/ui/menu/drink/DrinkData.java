package com.autoresto.ui.menu.drink;

import com.autoresto.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkData {
    public static String[][] data = new String[][] {
            {"Minuman 1", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 2", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 3", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 4", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 5", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 6", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 7", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 8", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 9", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"},
            {"Minuman 10", "https://img.okezone.com/content/2018/12/27/298/1996775/3-rekomendasi-minuman-segar-yang-praktis-tenggorokan-langsung-adem-LvODj8yhf9.jpg", "10.000"}

    };

    public static List<Drink> getListData() {
        List<Drink> list = new ArrayList<>();
        for(String[] aData : data) {
            Drink drink = new Drink();
            drink.setName(aData[0]);
            drink.setPhoto(aData[1]);
            drink.setPrice(aData[2]);

            list.add(drink);
        }
        return list;
    }
}
