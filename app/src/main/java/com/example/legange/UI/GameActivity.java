package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.legange.Rounds.IndividualRuleData;
import com.example.legange.Player.PlayerList;
import com.example.legange.Rounds.Round;
import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.DisplayManager;
import com.example.legange.Navigation.NavigationInterface;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Rounds.RoundsManager;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements NavigationInterface {


    int playerIndex = 0;
    int gameIndex = 0;
    TextBloc currentRule;
    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    ArrayList<Round> rounds;
    int currentRoundIndice = 0;
    LinearLayout gameLinearLayout;
    DisplayManager displayManager;
    RoundsManager roundsManager;

    public static Intent newIntent(Context context, ArrayList<Player> players) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(PLAYERS, players);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getIntent() != null) {
            players = (ArrayList<Player>) getIntent().getSerializableExtra(PLAYERS);
        }
        Collections.shuffle(players);
        PlayerList.setPlayerList(players);

        gameLinearLayout = (LinearLayout) findViewById(R.id.game_linear_layout);
        displayManager = new DisplayManager(gameLinearLayout, getSupportFragmentManager());
        roundsManager = new RoundsManager();

        nextRule();
    }

    private void nextRule() {
        displayManager.show(roundsManager.getNextRule().blocFragment);
    }

    @Override
    public void toNextScreen(ScreenType nextScreen) {

        switch (nextScreen) {
            case NEXTRULE:
                nextRule();
                break;

            case SCORE:
                displayManager.showScore();
                break;
        }

    }


}
