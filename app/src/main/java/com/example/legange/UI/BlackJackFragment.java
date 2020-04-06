package com.example.legange.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.legange.Player.Player;
import com.example.legange.R;

import java.util.ArrayList;


public class BlackJackFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView totalText,joueurText,valueText;
    private Button suivantButton,relancerButton;
    private ArrayList<Player> players;



    private int score,playerIndex;
    public BlackJackFragment() {
        // Required empty public constructor
    }



    public static BlackJackFragment newInstance(ArrayList<Player> players) {
        BlackJackFragment fragment = new BlackJackFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, players);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_black_jack, container, false);

        relancerButton = (Button) view.findViewById(R.id.relancer_button);
        suivantButton = (Button) view.findViewById(R.id.suivant_button);
        valueText = (TextView) view.findViewById(R.id.value_text);
        totalText =  (TextView) view.findViewById(R.id.total_text);
        joueurText = (TextView) view.findViewById(R.id.joueur_text);

        relancerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relancer();
            }
        });

        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suivant();
            }
        });

        score = 0;
        totalText.setText(String.valueOf(0));
        valueText.setText(String.valueOf(0));
        playerIndex = 0;
        joueurText.setText(players.get(playerIndex).getName());
        return view;
    }


    private void relancer()
    {
        int rand = 1 + (int)(Math.random() * ((13 - 1) + 1));
        score = score + rand;
        players.get(playerIndex).setRule_score(score);
        totalText.setText(String.valueOf(score));
        valueText.setText(String.valueOf(rand));
    }
    private void suivant()
    {
        playerIndex++;
        if(playerIndex < players.size()) {

            score = 0;
            joueurText.setText(players.get(playerIndex).getName());
            totalText.setText(String.valueOf(0));
            valueText.setText(String.valueOf(0));
        }
        else end();
    }

    private void end()
    {
        for(Player player : players) {
            if (player.getRule_score()==21)
                player.incrementScore(4);
            else if (player.getRule_score()==20)
                player.incrementScore(2);
            else if (player.getRule_score()==19)
                player.incrementScore(1);
            else if (player.getRule_score()>21)
                player.incrementScore(-1);
        }
        mListener.toPlayerSelection();
    }



}
