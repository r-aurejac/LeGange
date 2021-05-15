package com.example.legange.Bloc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.R;
import com.example.legange.UI.BaseFragment;
import com.example.legange.Navigation.ScreenType;

import java.io.Serializable;

public abstract class AbstractBloc implements Serializable{

    public BaseFragment blocFragment;

    public ScreenType nextScreen = ScreenType.NEXTRULE;

    AbstractBloc(ScreenType nextScreen)
    {
        this.nextScreen = nextScreen;
    }

    public void show(FragmentManager fragmentManager)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, blocFragment);
        fragmentTransaction.commit();
    }

}
