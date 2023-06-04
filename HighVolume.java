package com.example.company;

//purchase 100K or more products (consistently)
public class HighVolume extends Customer{
    //default constructor
    HighVolume(){
        super();
    }

    //parameterized constructor
    HighVolume(String name, int salesVolume){
        super(name, salesVolume);
    }
}
