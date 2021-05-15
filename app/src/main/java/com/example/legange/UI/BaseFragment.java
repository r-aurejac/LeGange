package com.example.legange.UI;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.legange.Navigation.NavigationInterface;
import com.example.legange.Navigation.ScreenType;


public abstract class BaseFragment extends Fragment {

    protected static final String RULE = "rule";

    NavigationInterface navigationInterface;

    public BaseFragment() {
        // Required empty public constructor
    }

    public void toNextScreen(ScreenType nextScreen)
    {
        navigationInterface.toNextScreen(nextScreen);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationInterface) {
            navigationInterface = (NavigationInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        navigationInterface = null;
    }

}
