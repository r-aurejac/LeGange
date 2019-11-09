package com.example.legange;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class SelectPlayerFragment extends Fragment {


    private static final String PLAYER = "param1";
    private static final String POINTS = "param2";
    private LinearLayout linearLayout;
    private int points;
    private Button validerButton;
    private ArrayList<Player> players;
    private  ArrayList<SelectPlayerItem> selectPlayerItems;
    private RuleInterface mListener;
    private TextView textView;
    public SelectPlayerFragment() {
        // Required empty public constructor
    }

    public static SelectPlayerFragment newInstance(ArrayList<Player> players, int points) {
        SelectPlayerFragment fragment = new SelectPlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, players);
        args.putInt(POINTS,points);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(PLAYER);
            points = getArguments().getInt(POINTS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_select_player, container, false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onScoreEnd();
            }
        });
        selectPlayerItems = new ArrayList<SelectPlayerItem>();
        showScore();
        validerButton = (Button) view.findViewById(R.id.valider_button);
        validerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValiderClicked();
            }
        });
        textView = (TextView) view.findViewById(R.id.select_player_text);

        if(points<0)
            textView.setText("cocher le ou les perdants ");
        else
            textView.setText("cocher le ou les gagnants ");
        return view;
    }

    public void showScore() {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < players.size(); i++) {
            SelectPlayerItem selectPlayerItem = SelectPlayerItem.newInstance(players.get(i));
            fragmentTransaction.add(R.id.players_linear_layout, selectPlayerItem);
            selectPlayerItems.add(selectPlayerItem);
        }
        fragmentTransaction.commit();
    }

    private void onValiderClicked()
    {
        for(SelectPlayerItem selectPlayerItem : selectPlayerItems) {

            if(selectPlayerItem.playerCb.isChecked())
           Player.findPlayerByName(players,selectPlayerItem.player.getName()).incrementScore(points);
        }
        mListener.onPointsAttributionEnd();

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


