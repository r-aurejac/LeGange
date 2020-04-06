package com.example.legange.UI.Grid;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.Player.PlayerList;
import com.example.legange.UI.BaseFragment;
import com.example.legange.str;

import java.util.ArrayList;



public class GridFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ArrayList<Player> players;
    private ArrayList<Player> attackers;

    private  TextView tvs[];
    private Player pirate;

    private String name;
    int turnIndex = 0;
    int treasure = 0;
    int trap = 0;
    int attackerIndex = 0;
    LinearLayout linearLayout;
    public GridFragment() {
        // Required empty public constructor
    }


    public static GridFragment newInstance(ArrayList<Player> players) {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, players);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pirate, container, false);
        linearLayout = view.findViewById(R.id.linear_layout);
        attackers = new ArrayList<Player>();
        for(int i = 0; i<players.size();i++) {
            if (!players.get(i).getRole().equals(str.VOLEUR))
                attackers.add(players.get(i));

        }
        pirate = PlayerList.findPlayerByRole(str.VOLEUR);
        initViews(view);


        return view;
    }


   private  void initViews(View view)
   {
       tvs = new TextView[10];
       tvs[0] = view.findViewById(R.id.textViewRule);
       tvs[1] = view.findViewById(R.id.textView1);
       tvs[2] = view.findViewById(R.id.textView2);
       tvs[3] = view.findViewById(R.id.textView3);
       tvs[4] = view.findViewById(R.id.textView4);
       tvs[5] = view.findViewById(R.id.textView5);
       tvs[6] = view.findViewById(R.id.textView6);
       tvs[7] = view.findViewById(R.id.textView7);
       tvs[8] = view.findViewById(R.id.textView8);
       tvs[9] = view.findViewById(R.id.textView9);

       for(int i = 1;i<10;i++) {
           final int u = i;
           tvs[i].setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   viewClicked(u);
               }

           });
           tvs[i].setText("X");
       }
       tvs[0].setText( pirate.getName() + " doit cacher son trésor");
   }

   private void end1()
    {
        linearLayout.removeAllViews();
        TextView tv = new TextView(getContext());
        String string = name + " à perdu il boit 2 gorgées, le pirate gagne 2 points";
        tv.setText(string);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(25);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.toNextRule();
            }
        });
        linearLayout.addView(tv);
    }
    private void end2()
    {
        linearLayout.removeAllViews();
        TextView tv = new TextView(getContext());
        String string = name + " à gagné il distribue 2 gorgées et gagne 2 points";
        tv.setText(string);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(25);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.toNextRule();
            }
        });
        linearLayout.addView(tv);
    }

    private void viewClicked(int i)
    {
        switch (turnIndex) {
            case 0 :
                tvs[0].setText(pirate.getName() + " doit placer son piège");
                tvs[i].setText("tresor");
                treasure = i;
                turnIndex = 1;
                alertDialog("trésor placé");
                break;
            case 1:
                if(i!=treasure) {
                    tvs[0].setText("Au tour de " + attackers.get(attackerIndex).getName());
                    tvs[treasure].setText("X");
                    trap = i;
                    tvs[trap].setText("X");
                    alertDialog("piège placé");
                    turnIndex = 2;

                }
                else alertDialog("le piège ne peut pas être au même endroit que le trésor");
                break;

            case 2:

                if (attackerIndex==attackers.size())
                    attackerIndex = 0;

                if(i == trap) {
                    tvs[i].setText("perdu");
                    name = attackers.get(attackerIndex).getName();
                    players.get(attackerIndex).incrementScore(-1);
                    pirate.incrementScore(1);
                    end1();
                }
                else if(i == treasure) {
                    tvs[i].setText("gagné");
                    name = attackers.get(attackerIndex).getName();
                    players.get(attackerIndex).incrementScore(1);
                    end2();
                }
                else {
                    int j = attackerIndex+1;
                    if (j==attackers.size())
                        j= 0;
                    tvs[0].setText("Au tour de " + attackers.get(j).getName());
                    tvs[i].setText("vide");

                }
                attackerIndex++;


                break;


        }



    }
    private void alertDialog(String text) {
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }
}
