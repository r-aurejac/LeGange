package com.example.legange.Class;

import java.util.ArrayList;

public class RoleRule extends Rule {


    public RoleRule(String name, String description, int points, int nextScreen, Player player) {
        super(name, description, points, nextScreen);

       this.getRulePlayers().add(player);

    }



}
