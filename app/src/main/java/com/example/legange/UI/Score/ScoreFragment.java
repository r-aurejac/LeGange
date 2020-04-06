package com.example.legange.UI.Score;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.UI.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;


public class ScoreFragment extends BaseFragment {


    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;
    private Button buttonCorrection;

    private ArrayList<Player> players;

    Boolean correction = false;
    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance(ArrayList<Player> players) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, players);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(PLAYER);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_score, container, false);
        buttonCorrection = view.findViewById(R.id.button_modif);
        buttonCorrection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correction == false)
                showCorrection();
                else showScore();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.toNextRule();
            }
        });

        showScore();
        return view;
    }

    public void showScore() {
        correction = false;
        ArrayList<Player> sortedPlayers = new ArrayList<Player>();
        sortedPlayers.addAll(players);
        Collections.sort(sortedPlayers);
        Collections.reverse(sortedPlayers);
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.score_linear_layout, new ScoreItem());
        for (int i = 0; i < sortedPlayers.size(); i++) {
            fragmentTransaction.add(R.id.score_linear_layout, ScoreItem.newInstance(sortedPlayers.get(i)));

        }
        fragmentTransaction.commit();
    }
    public void showCorrection() {
        correction = true;
        ArrayList<Player> sortedPlayers = new ArrayList<Player>();
        sortedPlayers.addAll(players);
        Collections.sort(sortedPlayers);
        Collections.reverse(sortedPlayers);
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.score_linear_layout, new ScoreItem());
        for (int i = 0; i < sortedPlayers.size(); i++) {
            fragmentTransaction.add(R.id.score_linear_layout, ScoreModifItem.newInstance(sortedPlayers.get(i)));

        }
        fragmentTransaction.commit();
    }




}

