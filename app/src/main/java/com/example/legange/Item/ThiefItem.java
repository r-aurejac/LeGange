package com.example.legange.Item;

import com.example.legange.Player.Player;
import com.example.legange.Player.PlayerList;
import com.example.legange.Rand;

import java.util.ArrayList;
import java.util.Collections;

public class ThiefItem extends ActiveItem {
    public ThiefItem(String name, String description, int durability, int percentageOfSucess) {
        super(name, description, durability,percentageOfSucess);
    }

    public boolean use(Player user)
    {
        if(super.use(user))
        {
            ArrayList<Player> playersWithItems = new ArrayList<>();
            for(Player player : PlayerList.players) {
                if (!player.items.isEmpty())
                    playersWithItems.add(player);
            }
            Player randPlayer = playersWithItems.get(Rand.randInt(0,playersWithItems.size()));
            Collections.shuffle(randPlayer.items);
            user.items.add(randPlayer.items.get(0));
            randPlayer.items.remove(0);



            return true;
        }
        return false;

    }
}
