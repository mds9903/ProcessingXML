package org.example;

public class Car {
    private String color;
    private String type;

    public Car(){

    }
    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "org.example.Main.org.example.Car{" +
                "color='" + color + "\'," +
                "type='" + type + "\'" +
                '}';
    }
}