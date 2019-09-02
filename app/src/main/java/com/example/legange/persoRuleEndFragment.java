package com.example.legange;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;



public class persoRuleEndFragment extends Fragment  {


    private static final String PLAYER = "param1";
    private static final String POINTS = "param2";
    private LinearLayout linearLayout;
    private int points;
    private Button ouiButton;
    private Button nonButton;
    private Player player;

    private RuleInterface mListener;

    public persoRuleEndFragment() {
        // Required empty public constructor
    }

    public static persoRuleEndFragment newInstance(Player player, int points) {
        persoRuleEndFragment fragment = new persoRuleEndFragment();
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
                mListener.onScoreEnd();
            }
        });

        ouiButton = (Button) view.findViewById(R.id.oui_button);
        ouiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onOuiClicked();
            }
        });
        nonButton = (Button) view.findViewById(R.id.oui_button);
        nonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onNonClicked();
            }
        });
        return view;
    }

   private void onOuiClicked() {
        player.incrementScore(points);
        mListener.onRuleEnd();
   }
    private void onNonClicked() {
        mListener.onRuleEnd();
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

