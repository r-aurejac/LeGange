package com.example.legange.UI.Score;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.legange.Player.Player;
import com.example.legange.R;


public class ScoreModifItem extends Fragment {


    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;


    private Player player;


    private Button buttonPlus, buttonMoins;
    private TextView nameText;
    private TextView scoreText;
    public ScoreModifItem() {
        // Required empty public constructor
    }

    public static ScoreModifItem newInstance(Player player) {
        ScoreModifItem fragment = new ScoreModifItem();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(PLAYER);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.item_score_modif, container, false);
        buttonMoins = (Button) view.findViewById(R.id.button_moins);
        buttonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.incrementScore(-1);
                printScore();
            }
        });
        buttonPlus = (Button) view.findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.incrementScore(+1);
                printScore();
            }
        });
        scoreText = (TextView) view.findViewById(R.id.score_text);
        nameText = (TextView) view.findViewById(R.id.name_text);
        if (player != null) {
            printScore();
        }
        else printHeader();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mListener.toNextScreen();
            }
        });
        return view;
    }

    private void printHeader()
    {

        scoreText.setText("Score");
        nameText.setText("Nom");
    }

    private void printScore()
    {

        scoreText.setText(String.valueOf(player.getScore()));
        nameText.setText(player.getName());
    }





}



