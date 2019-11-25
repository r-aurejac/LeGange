package com.example.legange.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.legange.Class.Player;
import com.example.legange.R;
import com.example.legange.RuleInterface;


public class ScoreItem extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;
    // TODO: Rename and change types of parameters

    private Player player;

    private RuleInterface mListener;

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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RuleInterface) {
            mListener = (RuleInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}


