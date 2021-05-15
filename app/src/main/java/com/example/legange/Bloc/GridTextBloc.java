package com.example.legange.Bloc;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.Player.Player;
import com.example.legange.Navigation.ScreenType;

import java.util.ArrayList;

public class GridTextBloc extends TextBloc {

    public GridTextBloc(ScreenType nextScreen, String name, String description, int points) {
        super(nextScreen, name, description);
    }



    public void showGrid(FragmentManager fragmentManager, ArrayList<Player> players)
    {
        //FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.game_linear_layout, ruleFragment);
        fragmentTransaction.commit();
    }

}
