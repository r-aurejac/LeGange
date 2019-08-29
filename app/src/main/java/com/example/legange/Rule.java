package com.example.legange;

import java.io.Serializable;

public class Rule implements Serializable {
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    private int points;
    private static final int ROLE_ATTRIBUTION = 0;
    private static final int TEXT = 1;


    public Rule(String name, String description, int type)
    {
        this.name = name;
        this.description = description;
        this.points = type;
    }

}
