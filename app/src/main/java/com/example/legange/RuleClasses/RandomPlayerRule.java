package com.example.legange.RuleClasses;


import java.util.ArrayList;



public class RandomPlayerRule extends Rule {

    int playersNumber = 0;
    boolean playersAttributed = false;

    public RandomPlayerRule(String name, String description, int points, int nextScreen, int playersNumber) {
        super(name, description, points, nextScreen);

    this.playersNumber = playersNumber;
    }


    public void playersAttribution(ArrayList<Player> players)
    {
        if (!playersAttributed) {
            for (int i = 0; i < playersNumber; i++) {

                Player player = Player.getRandomPlayer(players);
                String string = this.texts.get(this.getIndice()).replace("player" + String.valueOf(i + 1), player.getName());
                //String string = this.texts.get(this.getIndice()).replace("player1" , player.getName());
                this.texts.set(this.getIndice(), string);
                getRulePlayers().add(player);
            }
        playersAttributed = true;
        }

    }

}
