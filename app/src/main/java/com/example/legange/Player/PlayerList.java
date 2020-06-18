package com.example.legange.Player;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class PlayerList {
    public static ArrayList<Player> players;

    public static void setPlayerList(ArrayList<Player> list)
    {
        players = list;
    }


    public static Player findPlayerByName(String name)
    {
        for(Player player : players) {
            if (player.getName().equals(name))
                return player;

        }
        return null;
    }
    public static Player findPlayerByRole(String role)
    {
        for(Player player : players) {
            if (player.getRole().equals(role))
                return player;

        }
        return null;
    }

    public static Player getRandomPlayer()
    {
        Random r = new Random();
        return players.get( r.nextInt((players.size()-1 - 0) + 1) + 0);
    }


}
