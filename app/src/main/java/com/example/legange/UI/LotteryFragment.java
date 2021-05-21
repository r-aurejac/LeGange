package com.example.legange.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.legange.Events.Lottery;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;
import com.example.legange.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LotteryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LotteryFragment extends BaseFragment {

    Button lotteryButton;
    TextView ticketsTv, rewardTv, playerTv;
    EditText lotteryInputText;
    ArrayList<Player> playersWithTickets;
    int playerIndex = 0;
    Lottery lottery;

    public LotteryFragment() {
        // Required empty public constructor
    }


    public static LotteryFragment newInstance() {
        LotteryFragment fragment = new LotteryFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lottery, container, false);

        lotteryButton = view.findViewById(R.id.lottery_use_button);
        lotteryInputText = view.findViewById(R.id.lottery_input_text);
        ticketsTv = view.findViewById(R.id.lottery_tickets_tv);
        rewardTv = view.findViewById(R.id.lottery_reward_tv);
        playerTv = view.findViewById(R.id.lottery_player_tv);

        lotteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLotteryButtonClicked();
            }
        });
        init();
        return view;
    }

    void init() {
        if (PlayersManager.playersHaveTickets()) {
            lottery = Lottery.getInstance();
            playersWithTickets = new ArrayList<>();

            for (Player player : PlayersManager.players) {
                if (player.getTicketsNumber() > 0) {
                    playersWithTickets.add(player);
                }
            }
            updatePlayerInfo();
        }
        else
            {
            toNextScreen(ScreenType.NEXTRULE);
        }
    }

    void onLotteryButtonClicked()
    {
            hideKeyboardFrom(getContext(),getView());
            if (getCurrentPlayer().getTicketsNumber() > 0) {
                play();
            } else {
                nextPlayer();
            }



    }

    void nextPlayer()
    {
        rewardTv.setText("");
        playerIndex++;
        if(playerIndex < playersWithTickets.size()) {
            updatePlayerInfo();
        }
        else
        {
            toNextScreen(ScreenType.SCORE);
        }
    }

    void updatePlayerInfo()
    {
        lotteryInputText.setText("");
        playerTv.setText(getCurrentPlayer().getName());
        ticketsTv.setText(String.valueOf(getCurrentPlayer().getTicketsNumber()));

        if(getCurrentPlayer().getTicketsNumber() > 0)
        {
            lotteryInputText.setVisibility(View.VISIBLE);
            lotteryButton.setText("Utiliser");
        }
        else
            {
                lotteryInputText.setVisibility(View.GONE);
                lotteryButton.setText("Suivant");
            }
    }

    void play()
    {
        if (!lotteryInputText.getText().toString().isEmpty()) {
            int ticketNumber = Integer.parseInt(lotteryInputText.getText().toString());
            String rewardtext = lottery.useTicket(getCurrentPlayer(), ticketNumber);
            rewardTv.setText(rewardtext);
        }
        updatePlayerInfo();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

/*
    void onLotteryButtonClicked() {

        if (playerIndex >= playersWithTickets.size()) {
            toNextScreen(ScreenType.SCORE);
        }

        if(getCurrentPlayer().getTicketsNumber()==0) {
            nextPlayer();
        }
        else
        {
            play();
        }
    }

    void nextPlayer()
    {
        playerIndex++;
        playerTv.setText(getCurrentPlayer().getName());
        ticketsTv.setText(String.valueOf(getCurrentPlayer().getTicketsNumber()));

    }

    void play() {

        if (!lotteryInputText.getText().toString().isEmpty()) {
            int ticketNumber = Integer.parseInt(lotteryInputText.getText().toString());
            String rewardtext = lottery.useTicket(getCurrentPlayer(), ticketNumber);
            rewardTv.setText(rewardtext);

        }

        ticketsTv.setText(String.valueOf(getCurrentPlayer().getTicketsNumber()));

        if(getCurrentPlayer().getTicketsNumber()>1) {
            lotteryInputText.setVisibility(View.GONE);
            lotteryButton.setText("Suivant");
        }
        else
        {
            lotteryInputText.setVisibility(View.VISIBLE);
            lotteryButton.setText("Utiliser");
        }
    }
*/
    Player getCurrentPlayer() {
        return playersWithTickets.get(playerIndex);
    }


}