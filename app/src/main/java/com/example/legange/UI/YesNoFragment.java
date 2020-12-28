package com.example.legange.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.legange.Player.Player;
import com.example.legange.R;


public class YesNoFragment extends BaseFragment  {


    private static final String PLAYER = "param1";
    private static final String POINTS = "param2";
    private LinearLayout linearLayout;
    private int points;
    private Button ouiButton;
    private Button nonButton;
    private Player player;



    public YesNoFragment() {
        // Required empty public constructor
    }

    public static YesNoFragment newInstance(Player player, int points) {
        YesNoFragment fragment = new YesNoFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, player);
        args.putInt(POINTS,points);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(PLAYER);
            points = getArguments().getInt(POINTS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_perso_rule_end, container, false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.toNextScreen();
            }
        });

        ouiButton = (Button) view.findViewById(R.id.oui_button);
        ouiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onOuiClicked();
            }
        });
        nonButton = (Button) view.findViewById(R.id.non_button);
        nonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onNonClicked();
            }
        });
        return view;
    }

   private void onOuiClicked() {
        if(points>0)
        player.incrementScore(points);

        mListener.toNextScreen();
   }
    private void onNonClicked() {
        if(points<0)
            player.incrementScore(points);
        mListener.toNextScreen();
    }






}

