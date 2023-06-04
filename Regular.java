package com.example.company;

//store instruments that are not too sensitive to temperature and humidity
public class Regular extends Warehouse{
    //default constructor
    Regular(){
        super();
    }

    //parameterized constructor
    Regular(String name, int volume){
        super(name, volume);
    }
}
