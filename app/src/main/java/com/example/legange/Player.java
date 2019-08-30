package com.example.legange;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void incrementScore(int inc)
    {
        score += inc;
    }


    private  String name;
    private int score;

    public Player(String name)
    {
        this.name = name;
    }

    public static Player findPlayerByName(ArrayList<Player> players, String name)
    {
        for(Player player : players) {
            if (player.name.equals(name))
                return player;

        }
        return null;
    }
    public static Player findPlayerByRole(ArrayList<Player> players, String role)
    {
        for(Player player : players) {
            if (player.role.equals(role))
                return player;

        }
        return null;
    }

}
