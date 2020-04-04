package com.example.legange.RuleClasses;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.R;
import com.example.legange.UI.WriteFragment;

import java.util.ArrayList;

public class WritingRule extends RandomPlayerRule {

    public int phases =0;

    public WritingRule(String name, String description, int points, int nextScreen, int playersNumber, int phases) {
        super(name, description, points, nextScreen, playersNumber);

        this.phases = phases;
        size++;
    }


    @Override
      public void show(FragmentManager fragmentManager, ArrayList<Player> players)
    {

        if(this.getIndice() == 1) {
            //FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.game_linear_layout, WriteFragment.newInstance(this, players,phases));
            fragmentTransaction.commit();
        }
        else super.show(fragmentManager,players);
    }

}

