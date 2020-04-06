package com.example.legange.Rule;


import com.example.legange.Player.Player;

public class RoleAttributionRule extends Rule {


    public Player player;

    public RoleAttributionRule(String name, String description, int points, int nextScreen) {
        super(name, description, points, nextScreen);


    }


    public void playerAttribution(Player player) {
        player.setRole(this.getName());
        this.player = player;
        String string = this.texts.get(this.getIndice()).replace("player", player.getName());
        this.texts.set(this.getIndice(), string);
    }






}
