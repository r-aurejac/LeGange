package com.example.legange.Player;

import com.example.legange.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable, Comparable {

    private String role;

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    private String roleDescription ="";

    public int getRule_score() {
        return rule_score;
    }

    public void setRule_score(int rule_score) {
        this.rule_score = rule_score;
    }

    private int rule_score = 0;

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
        items = new ArrayList<>();
    }

    public ArrayList<Item> items;


    @Override
    public int compareTo(Object o) {

        Player player = (Player) o;
        return (this.score - player.score);
    }
}
