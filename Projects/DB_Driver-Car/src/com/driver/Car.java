package com.driver;

public class Car {
    private Long id;
    private String model;
    private String color;
    private Driver driver;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car(Long id, String model, String color, Driver driver) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.driver = driver;
    }
}
