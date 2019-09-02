package com.example.legange;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class ScoreFragment extends Fragment {


    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;


    private ArrayList<Player> players;

    private RuleInterface mListener;

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


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onScoreEnd();
            }
        });

        showScore();
        return view;
    }

    public void showScore() {
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

