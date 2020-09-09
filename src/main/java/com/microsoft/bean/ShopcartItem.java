package com.microsoft.bean;

import com.google.gson.annotations.Expose;

public class ShopcartItem {

    private String idSelector;
    private String name;
    @Expose
    private double price;

    public ShopcartItem(String id,String name, Double price){
        this.idSelector = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getIdSelector() {
        return this.idSelector;
    }

    public void setIdSelector(String idSelector) {
        this.idSelector = idSelector;
    }


    
}
