package com.example.legange.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.legange.Class.Player;
import com.example.legange.R;
import com.example.legange.RuleInterface;


public class PersoRuleEndFragment extends Fragment  {


    private static final String PLAYER = "param1";
    private static final String POINTS = "param2";
    private LinearLayout linearLayout;
    private int points;
    private Button ouiButton;
    private Button nonButton;
    private Player player;

    private RuleInterface mListener;

    public PersoRuleEndFragment() {
        // Required empty public constructor
    }

    public static PersoRuleEndFragment newInstance(Player player, int points) {
        PersoRuleEndFragment fragment = new PersoRuleEndFragment();
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
                mListener.toNextRule();
            }
        });

        ouiButton = (Button) view.findViewById(R.id.oui_button);
        ouiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onOuiClicked();
            }
        });
        nonButton = (Button) view.findViewById(R.id.non_button);
        nonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onNonClicked();
            }
        });
        return view;
    }

   private void onOuiClicked() {
        if(points>0)
        player.incrementScore(points);

        mListener.toScore();
   }
    private void onNonClicked() {
        if(points<0)
            player.incrementScore(points);
        mListener.toScore();
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

