package com.example.legange.Rule;


import com.example.legange.Player.PlayerList;

public class RoleRule extends Rule {
    boolean playersAttributed = false;

    public RoleRule(String name, String description, int points, String role, int nextScreen) {
        super(name, description, points, nextScreen);

        if(PlayerList.findPlayerByRole(role) != null)
            this.getRulePlayers().add(PlayerList.findPlayerByRole(role));
        playersAttribution();

    }

    public void playersAttribution()
    {
        if (!playersAttributed) {
            for (int i = 0; i < this.getRulePlayers().size(); i++) {


                String string = this.texts.get(this.getIndice()).replace("player" + String.valueOf(i + 1), this.getRulePlayers().get(i).getName());
                //String string = this.texts.get(this.getIndice()).replace("player1" , player.getName());
                this.texts.set(this.getIndice(), string);

            }
            playersAttributed = true;
        }

    }

}
