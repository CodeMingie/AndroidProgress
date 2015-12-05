package com.example.ming.progress;

public class GoalItem {

    private String name;
    private int units;
    private int id;

    public GoalItem(String name, int units, int id) {
        this.name = name;
        this.units = units;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getId() {
        return id;
    }
}
