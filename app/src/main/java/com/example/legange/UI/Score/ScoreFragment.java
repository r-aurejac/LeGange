package com.example.legange.UI.Score;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.legange.Navigation.DisplayManager;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;
import com.example.legange.R;
import com.example.legange.UI.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;


public class ScoreFragment extends BaseFragment {


    private static final String RULEINFO = "param2";
    private LinearLayout linearLayout;
    private Button correctionButton, infoButton, nextButton;
    private String ruleInfo = "Aucune info disponible";
    private ArrayList<Player> players;

    Boolean correction = false;
    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance(String ruleInfo) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putString(RULEINFO,ruleInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ruleInfo = (String) getArguments().getString(RULEINFO);
        }
        players = PlayersManager.players;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_score, container, false);
        correctionButton = view.findViewById(R.id.button_modif);
        nextButton = view.findViewById(R.id.score_next_button);
        correctionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!correction)
                showCorrection();
                else showScore();
            }
        });
        infoButton = view.findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRuleInfo();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextScreen(ScreenType.NEXTRULE);
            }
        });

        showScore();
        return view;
    }

    public void showScore() {
        correction = false;
        ArrayList<Player> sortedPlayers = PlayersManager.getPlayerRankingList();
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
        ArrayList<Player> sortedPlayers = PlayersManager.getPlayerRankingList();
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

