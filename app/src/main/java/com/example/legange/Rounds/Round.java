package com.example.legange.Rounds;

import android.support.v4.app.FragmentManager;

import com.example.legange.Bloc.AbstractBloc;


import java.util.ArrayList;

public abstract class Round {

    public ArrayList<AbstractBloc> rules;

    private int ruleIndex = 0;

    protected Round()
    {
        rules = new ArrayList<>();
    }


    public AbstractBloc getNextRule()
    {
        ruleIndex++;
        return rules.get(ruleIndex-1);
    }

    public boolean isFinished()
    {
        return ruleIndex >= rules.size();
    }


}
