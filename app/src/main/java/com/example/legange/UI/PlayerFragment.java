package com.example.legange.UI;

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

import com.example.legange.Item.Item;
import com.example.legange.Player.Player;
import com.example.legange.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PLAYER = "param1";
    private static final String INCOMMINGRULE = "param2";

    // TODO: Rename and change types of parameters
    private Player player;
    TextView titleTV,incommingRuleTV;
    Button nextButton;
    String incommingRule;
    LinearLayout inventoryLayout;

    public PlayerFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static PlayerFragment newInstance(Player player, String incommingRule) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, player);
        args.putString(INCOMMINGRULE,incommingRule);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player = (Player) getArguments().getSerializable(PLAYER);
            incommingRule = getArguments().getString(INCOMMINGRULE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);


        titleTV = view.findViewById(R.id.title_tv);
        titleTV.setText("Au tour de " + player.getName());


        incommingRuleTV = view.findViewById(R.id.incommingRule_tv);
        incommingRuleTV.setText(incommingRule);
        nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toNextScreen();
            }
        });
        inventoryLayout = view.findViewById(R.id.inventory_layout);
        showPlayerItems();
        return view;
    }


    void showPlayerItems()
    {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < player.items.size(); i++) {

            fragmentTransaction.add(R.id.inventory_layout, ItemItem.newInstance(player.items.get(i),player));

        }
        fragmentTransaction.commit();
    }
}