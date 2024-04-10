package com.example.app;
public class Item {
    public Item(String name, String position, Float salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Float getSalary() {
        return salary;
    }

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    String position;
    Float salary;
}