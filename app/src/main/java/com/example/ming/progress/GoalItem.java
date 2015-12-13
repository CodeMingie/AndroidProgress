package com.example.ming.progress;

public class GoalItem {

    private String name;
    private int units;
    private long id;

    public GoalItem(long id, String name, int units) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }
}
