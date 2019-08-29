package com.example.legange;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RuleInterface {

    LinearLayout linearLayout;
    int roleIndex = 0;
    int ruleIndex = 0;
    int introIndex =0;
    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    Data data;
    LinearLayout gameLinearLayout;
    TableLayout scoreTableLayout;
    public static Intent newIntent(Context context, ArrayList<Player> players) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(PLAYERS,players);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (getIntent() != null) {
            players = (ArrayList<Player>) getIntent().getSerializableExtra(PLAYERS);
        }
        data = new Data();
        gameLinearLayout = (LinearLayout) findViewById(R.id.game_linear_layout);
        Collections.shuffle(players);
      nextRule();

    }


    @Override
    public void onRuleEnd() {
        nextRule();
    }

    @Override
    public void onScoreEnd() {
        nextRule();
    }


    private void showTextRule(Rule rule)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, RuleFragment.newInstance(rule, players));
        fragmentTransaction.commit();
    }


    private void roleAttribution()
    {
        if(roleIndex != players.size()) {
            Rule role = data.getRole(players.get(roleIndex).getName(),roleIndex);
            players.get(roleIndex).setRole(role.getName());
            showTextRule(role);
            roleIndex++;
        }
        else {
            ruleIndex = 0;
            nextRule();
        }

    }
    private void showScore()
    {
        gameLinearLayout.removeAllViews();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.game_linear_layout, ScoreFragment.newInstance(players));
        fragmentTransaction.commit();
        ruleIndex = 3;
    }

    private void showPirateRule()
    {
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.game_linear_layout, PirateFragment.newInstance(players,"test"));
                fragmentTransaction.commit();

    }
    private void intro()
    {
        showTextRule(data.getAnnouncement(introIndex));
        introIndex++;
        if(introIndex == 1)
            ruleIndex = 1;
        if(introIndex == 2)
            ruleIndex = 3;
    }



    void nextRule() {
        switch (ruleIndex) {
            case 0 :
                intro();
                break;
            case 1:
                roleAttribution();
                break;

            case 2:
                showScore();
                break;

            case 3:
                showPirateRule();
                break;
        }
    }

}
