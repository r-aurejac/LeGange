package com.example.legange;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class PirateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Player> players;
    private ArrayList<Player> attackers;
    private String mParam2;
    private  TextView tvs[];
    int turn = 1;
    private RuleInterface mListener;
    int turnIndex = 0;
    int treasure = 0;
    int trap = 0;
    int attackerIndex = 0;

    public PirateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param players Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PirateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PirateFragment newInstance(ArrayList<Player> players, String param2) {
        PirateFragment fragment = new PirateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, players);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pirate, container, false);
        attackers = new ArrayList<Player>();
        for(int i = 0; i<players.size();i++) {
            if (!players.get(i).getRole().equals("pirate"))
                attackers.add(players.get(i));
        }

        initViews(view);


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
       tvs[0].setText("le pirate doit cacher son trésor");
   }

    private void viewClicked(int i)
    {
        switch (turnIndex) {
            case 0 :
                tvs[0].setText("le pirate doit placer son piège");
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
                    tvs[0].setText(attackers.get(attackerIndex).getName() + " à perdu il boit 5 gorgées");
                    alertDialog(attackers.get(attackerIndex).getName() + " à perdu, il boit 5 gorgées");
                    Player.findPlayerByName(players,attackers.get(attackerIndex).getName()).incrementScore(-1);
                    Player.findPlayerByRole(players,"pirate").incrementScore(2);
                    mListener.onRuleEnd();
                }
                else if(i == treasure) {
                    tvs[i].setText("gagné");
                    tvs[0].setText(attackers.get(attackerIndex).getName() + " à gagné");
                    alertDialog(attackers.get(attackerIndex).getName() + " à gagné");
                    Player.findPlayerByName(players,attackers.get(attackerIndex).getName()).incrementScore(2);
                    mListener.onRuleEnd();
                }
                else {
                    attackerIndex++;
                    tvs[0].setText("Au tour de " + attackers.get(attackerIndex).getName());
                    tvs[i].setText("vide");

                }



                break;

            case 3:

                break;
        }

    }
    private void alertDialog(String text) {
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }
}
