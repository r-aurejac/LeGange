package com.example.legange.UI.Score;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.legange.RuleClasses.Player;
import com.example.legange.R;
import com.example.legange.RuleInterface;
import com.example.legange.UI.BaseFragment;


public class ScoreItem extends BaseFragment {


    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;


    private Player player;

    private TextView nameText;
    private TextView scoreText;
    public ScoreItem() {
        // Required empty public constructor
    }

    public static ScoreItem newInstance(Player player) {
        ScoreItem fragment = new ScoreItem();
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



        View view = inflater.inflate(R.layout.item_score, container, false);

        scoreText = (TextView) view.findViewById(R.id.score_text);
        nameText = (TextView) view.findViewById(R.id.name_text);
        if (player != null) {
            printScore();
        }
        else printHeader();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.toNextRule();
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


