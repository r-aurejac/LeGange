package com.example.legange;

import java.io.Serializable;

public class Player implements Serializable {

    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private  String name;
    private int score;

    public Player(String name)
    {
        this.name = name;
    }

}
