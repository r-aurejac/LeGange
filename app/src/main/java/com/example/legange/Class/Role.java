package com.example.legange.Class;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;




public class Role extends Rule {


    public Player player;

    public Role(String name, String description, int points, int nextScreen) {
        super(name, description, points, nextScreen);


    }


    public void playerAttribution(Player player) {
        player.setRole(this.getName());
        this.player = player;
        String string = this.texts.get(this.getIndice()).replace("player", player.getName());
        this.texts.set(this.getIndice(), string);
    }






}