package com.example.legange.UI.Score;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
    private static final String RULEINFO = "param2";
    private LinearLayout linearLayout;
    private Button buttonCorrection,buttonInfo;
    private String ruleInfo = "Aucune info disponible";
    private ArrayList<Player> players;

    Boolean correction = false;
    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance(ArrayList<Player> players, String ruleInfo) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, players);
        args.putString(RULEINFO,ruleInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(PLAYER);
            ruleInfo = (String) getArguments().getString(RULEINFO);
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
                if(!correction)
                showCorrection();
                else showScore();
            }
        });
        buttonInfo = view.findViewById(R.id.info_button);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRuleInfo();
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


    void showRuleInfo()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(ruleInfo)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });

        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

}

