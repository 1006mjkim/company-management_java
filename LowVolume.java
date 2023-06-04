package com.example.company;

//purchase less than 100K products (not as consistent as high volume customers)
public class LowVolume extends Customer{
    //default constructor
    LowVolume(){
        super();
    }

    //parameterized constructor
    LowVolume(String name, int salesVolume){
        super(name, salesVolume);
    }
}
