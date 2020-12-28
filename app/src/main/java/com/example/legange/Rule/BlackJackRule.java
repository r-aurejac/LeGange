package com.example.legange.Rule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.UI.BlackJackFragment;


import java.util.ArrayList;


public class BlackJackRule extends Rule {



    public BlackJackRule(String name, String description, int points) {
        super(name, description, points);
        size = 2;
    }


@Override
    public void show(FragmentManager fragmentManager, ArrayList<Player> players)
    {
        if(this.getIndice() == 1) {
            //FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.game_linear_layout, BlackJackFragment.newInstance(players));
            fragmentTransaction.commit();
        }
        else super.show(fragmentManager,players);
    }
}
