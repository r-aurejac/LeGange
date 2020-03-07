package com.example.legange.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.legange.Class.Player;
import com.example.legange.R;
import com.example.legange.Class.Rule;
import com.example.legange.RuleInterface;

import java.util.ArrayList;


public class RuleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RULE = "param1";
    private static final String PLAYERS = "param2";
    private static final String INDICE = "param3";
    // TODO: Rename and change types of parameters
    private Rule rule;
    private ArrayList<Player> players;
    private TextView ruleText,titleText;
    private RuleInterface mListener;
    private int indice = 0;
    public RuleFragment() {
        // Required empty public constructor
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
