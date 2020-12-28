package com.example.legange.Rule;


import com.example.legange.Player.Player;
import com.example.legange.Player.PlayerList;

import java.util.ArrayList;



public class RandomPlayerRule extends Rule {

    int playersNumber = 0;
    boolean playersAttributed = false;

    public RandomPlayerRule(String name, String description, int points, int playersNumber) {
        super(name, description, points);

    this.playersNumber = playersNumber;
    playersAttribution();
    }


    public void playersAttribution()
    {
        if (!playersAttributed) {
            for (int i = 0; i < playersNumber; i++) {

                Player player = PlayerList.getRandomPlayer();
                String string = this.texts.get(this.getIndice()).replace("player" + String.valueOf(i + 1), player.getName());
                //String string = this.texts.get(this.getIndice()).replace("player1" , player.getName());
                this.texts.set(this.getIndice(), string);
                getRulePlayers().add(player);
            }
        playersAttributed = true;
        }

    }

}
