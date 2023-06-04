package com.example.company;

public abstract class Customer {
    protected String name;
    protected int salesVolume;

    //default constructor
    Customer(){
        name = "";
        salesVolume = 0;
    }

    //parameterized constructor
    Customer(String name, int salesVolume){
        this.name = name;
        this.salesVolume = salesVolume;
    }

    //getter for name
    public String getName(){
        return name;
    }

    //getter for volume
    public int getSalesVolume(){
        return salesVolume;
    }
}
