package com.example.legange.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.Rule.Rule;

import java.util.ArrayList;


public class RuleFragment extends BaseFragment {


    private static final String RULE = "param1";
    private static final String PLAYERS = "param2";
    private static final String INDICE = "param3";

    private Rule rule;
    private ArrayList<Player> players;
    private TextView ruleText,titleText;

    private int indice = 0;
    public RuleFragment() {

    }

    public static RuleFragment newInstance(Rule rule, ArrayList<Player> players,int indice) {
        RuleFragment fragment = new RuleFragment();
        Bundle args = new Bundle();
        args.putSerializable(RULE, rule);
        args.putSerializable(PLAYERS, players);
        args.putInt(INDICE,indice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rule = (Rule) getArguments().getSerializable(RULE);
            players = (ArrayList<Player>) getArguments().getSerializable(PLAYERS);
            indice = getArguments().getInt(INDICE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



       View view = inflater.inflate(R.layout.fragment_rule, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRuleEnd();
            }
        });
        ruleText = (TextView) view.findViewById(R.id.rule_text_view);
        ruleText.setText(rule.texts.get(indice));
        ruleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRuleEnd();
            }
        });
        titleText =(TextView) view.findViewById(R.id.title_text_view);
        titleText.setText(rule.getName());
        return view;
    }






}
