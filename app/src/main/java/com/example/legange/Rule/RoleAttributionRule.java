package com.example.legange.Rule;


import com.example.legange.Player.Player;

public class RoleAttributionRule extends Rule {


    public Player player;

    public RoleAttributionRule(String name, String description, int points) {
        super(name, description, points);


    }


    public void playerAttribution(Player player) {
        player.setRole(this.getName());
        player.setRoleDescription(this.texts.get(0));
        this.player = player;
        String string = this.texts.get(this.getIndice()).replace("player", player.getName());
        this.texts.set(this.getIndice(), string);
    }






}
