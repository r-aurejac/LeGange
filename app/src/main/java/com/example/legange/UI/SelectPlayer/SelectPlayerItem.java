package com.example.legange.UI.SelectPlayer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.RuleInterface;


public class SelectPlayerItem extends Fragment {


    private static final String PLAYER = "param1";
    private LinearLayout linearLayout;
    private TextView playerTv;
    public CheckBox playerCb;

    public Player player;

    private RuleInterface mListener;
    private TextView textView;

    public SelectPlayerItem() {
        // Required empty public constructor
    }

    public static SelectPlayerItem newInstance(Player player) {
        SelectPlayerItem fragment = new SelectPlayerItem();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(PLAYER);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_select_player, container, false);
        playerTv = (TextView) view.findViewById(R.id.player_tv);
        playerCb = (CheckBox) view.findViewById(R.id.player_cb);

        if (player != null) {
            printName();
        }


        return view;
    }

   public void printName()
   {
       playerTv.setText(player.getName());

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
