package com.example.legange.Rule;

import com.example.legange.Player.Player;
import com.example.legange.Player.PlayerList;

import java.util.ArrayList;

public class SelectedPlayersRule extends Rule {

    ArrayList<Player> players;
    boolean playersAttributed = false;
    public SelectedPlayersRule(String name, String description, int points, ArrayList<Player> players) {
        super(name, description, points);

        this.players = players;
        playersAttribution();
    }
    public SelectedPlayersRule(String name, String description, int points, Player player) {
        super(name, description, points);

        this.players = new ArrayList<>();
        players.add(player);
        playersAttribution();
    }


    private void playersAttribution()
    {
        if (!playersAttributed) {
            for (int i = 0; i < getRulePlayers().size(); i++) {

                Player player = players.get(i);
                String string = this.texts.get(this.getIndice()).replace("player" + String.valueOf(i + 1), player.getName());
                //String string = this.texts.get(this.getIndice()).replace("player1" , player.getName());
                this.texts.set(this.getIndice(), string);
                getRulePlayers().add(player);
            }
            playersAttributed = true;
        }

    }
}
