package com.example.legange.Item;

import com.example.legange.Player.Player;
import com.example.legange.Rand;

public class ActiveItem extends Item {
    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    private int durability = 1;
    private int percentageOfSucess = 100;
    public ActiveItem(String name, String description, int durability, int percentageOfSucess) {
        super(name, description);
        this.durability = durability;
        if(percentageOfSucess>0 && percentageOfSucess<100)
            this.percentageOfSucess = percentageOfSucess;
    }

    public boolean use(Player user)
    {
        if(Rand.randInt(0,100) > percentageOfSucess) {
            durability--;
            return true;
        }
        else return false;
    }


}
