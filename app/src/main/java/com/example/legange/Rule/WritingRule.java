package com.example.legange.Rule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.R;
import com.example.legange.UI.WriteFragment;

public class WritingRule extends RandomPlayesrRule {

    public int phases =0;

    public WritingRule(String name, String description, int points, int playersNumber, int phases) {
        super(name, description, points, playersNumber);

        this.phases = phases;
        size++;
    }


    @Override
      public void show(FragmentManager fragmentManager)
    {

        if(this.getIndice() == 1) {
            //FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.game_linear_layout, WriteFragment.newInstance(this,phases));
            fragmentTransaction.commit();
            incrIndice();
        }
        else super.show(fragmentManager);
    }

}

