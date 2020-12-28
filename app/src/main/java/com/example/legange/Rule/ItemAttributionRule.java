package com.example.legange.Rule;


import com.example.legange.Item.Item;
import com.example.legange.Player.Player;

import java.util.ArrayList;

public class ItemAttributionRule extends Rule {


    public Player player;

    public ItemAttributionRule(Player player, Item item) {
        super("name", "description", 0);

        player.items.add(item);
        this.name = item.getName();
        this.texts.set(0,player.getName() + " obtient le/la " + item.getName() + " " + item.getDescription());


    }


    public void ItemAttribution(Player player) {

    }






}
