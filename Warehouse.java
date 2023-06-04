package com.example.company;

public abstract class Warehouse {
    protected String name;
    protected int volume;

    //default constructor
    Warehouse(){
        name = "";
        volume = 0;
    }

    //parameterized constructor
    Warehouse(String name, int volume){
        this.name = name;
        this.volume = volume;
    }

    //getter for name
    public String getName(){
        return name;
    }

    //getter for volume
    public int getVolume(){
        return volume;
    }
}
