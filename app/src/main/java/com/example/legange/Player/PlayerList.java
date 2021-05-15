package com.example.legange.Player;

import android.util.Log;

import com.example.legange.Rand;

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


    public static Player getRandomPlayer()
    {
        Random r = new Random();
        return players.get(Rand.randInt(0,players.size()-1));
    }

    public static String[] playersToStringArray()
    {

        String[] playersStringArray = new String[players.size()];
        for(int i = 0; i< players.size();i++)
            playersStringArray[i] = players.get(i).getName();

        return playersStringArray;
    }

    public static ArrayList<Player> getPlayerListCopy()
    {
        return new ArrayList<>(players);

    }


}
