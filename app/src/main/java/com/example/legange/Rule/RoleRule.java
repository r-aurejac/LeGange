package com.example.legange.Rule;

import com.example.legange.Player.Player;
import com.example.legange.Player.PlayerList;

public class RoleRule extends Rule {


    public RoleRule(String name, String description, int points, String role, int nextScreen) {
        super(name, description, points, nextScreen);

        if(PlayerList.findPlayerByRole(role) != null)
       this.getRulePlayers().add(PlayerList.findPlayerByRole(role));


    }



}
