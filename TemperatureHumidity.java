package com.example.company;

//store instruments that are sensitive to temperature and humidity
public class TemperatureHumidity extends Warehouse{
    //desired temperature and humidity level
    protected double temperature, humidity;

    //default constructor
    TemperatureHumidity(){
        super();
        temperature = 0;
        humidity = 0;
    }

    //parameterized constructor
    TemperatureHumidity(double temperature, double humidity, String name, int volume){
        super(name, volume);
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
