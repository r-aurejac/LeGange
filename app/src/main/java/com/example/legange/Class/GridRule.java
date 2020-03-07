package com.example.legange.Class;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.R;
import com.example.legange.UI.PirateFragment;
import com.example.legange.UI.WriteFragment;

import java.util.ArrayList;

public class GridRule extends Rule {

    public GridRule(String name, String description, int points, int nextScreen) {
        super(name, description, points, nextScreen);
    }



    public void showGrid(FragmentManager fragmentManager, ArrayList<Player> players)
    {
        //FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, PirateFragment.newInstance(players));
        fragmentTransaction.commit();
    }

}
