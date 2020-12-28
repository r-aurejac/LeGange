package com.example.legange.Rule;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.UI.Grid.GridFragment;

import java.util.ArrayList;

public class GridRule extends Rule {

    public GridRule(String name, String description, int points, int nextScreen) {
        super(name, description, points);
    }



    public void showGrid(FragmentManager fragmentManager, ArrayList<Player> players)
    {
        //FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, GridFragment.newInstance(players));
        fragmentTransaction.commit();
    }

}
