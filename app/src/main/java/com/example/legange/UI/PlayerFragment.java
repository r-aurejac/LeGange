package com.example.legange.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.RuleInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PLAYER = "param1";


    // TODO: Rename and change types of parameters
    private Player player;
    TextView descriptionTV,titleTV;
    Button nextButton;


    public PlayerFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static PlayerFragment newInstance(Player player) {
        PlayerFragment fragment = new PlayerFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);


        titleTV = view.findViewById(R.id.title_tv);
        titleTV.setText("Au tour de" + player.getName());
        descriptionTV = view.findViewById(R.id.description_tv);
        descriptionTV.setText(player.getRoleDescription());
        nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toNextScreen();
            }
        });
        return view;
    }
}