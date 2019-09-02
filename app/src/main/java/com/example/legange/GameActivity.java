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
    boolean ruleChange = true;
    boolean end = false;
    int groupRuleIndex = 0;
    int persoRuleIndex = 0;
    Rule ruleCache;
    Player playerCache;
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

        if(end!=true)
        nextRule();
        else {
            Intent intent = MainActivity.newIntent(getApplicationContext());
            getApplicationContext().startActivity(intent);
        }
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
        if(ruleChange) {
            ruleIndex = 6;
            ruleChange = false;
        }
        else {
            ruleIndex = 7;
            ruleChange = true;
        }
    }

    private void showPirateRule()
    {
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.game_linear_layout, PirateFragment.newInstance(players,"test"));
                fragmentTransaction.commit();
                ruleIndex = 2;

    }


    private void intro()
    {
        showTextRule(data.getAnnouncement(introIndex));
        introIndex++;
        if(introIndex == 1)
            ruleIndex = 1;
        if(introIndex == 2) {
            if(Player.findPlayerByRole(players,"pirate")!=null)
            ruleIndex = 3;
            else ruleIndex = 3;
        }
    }

    private void groupRule()
    {
        if(groupRuleIndex<players.size()) {
            ruleCache = data.getGroupRule(persoRuleIndex, players.get(groupRuleIndex).getName());
            playerCache = Player.getRandomPlayer(players);
            showTextRule(ruleCache);
            groupRuleIndex++;
            ruleIndex = 4;
        }
    }
    private void persoRule()
    {
        if(groupRuleIndex<players.size()) {
            ruleCache = data.getPersoRule(persoRuleIndex, players.get(persoRuleIndex).getName());
            playerCache = players.get(persoRuleIndex);
            showTextRule(ruleCache);
            ruleIndex = 5;
        }

    }
    private void showPersoRuleEnd()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, persoRuleEndFragment.newInstance(playerCache,(ruleCache.getPoints())));
        fragmentTransaction.commit();
        persoRuleIndex++;
        ruleIndex = 2;
    }
    private void showPlayerSelection()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, SelectPlayerFragment.newInstance(players,ruleCache.getPoints()));
        fragmentTransaction.commit();
        ruleIndex = 2;

    }

    private void end()
    {
        end = true;
        Collections.sort(players);
        if(players.get(0).getRole().equals("guerrier indien"))
        showTextRule(data.getAnnouncement(2,players.get(1).getName()));
        else showTextRule(data.getAnnouncement(2,players.get(0).getName()));
    }

    void nextRule() {

        if(persoRuleIndex >= players.size())
            end();

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

            case 4:
                showPlayerSelection();
                break;

            case 5:
                showPersoRuleEnd();
            break;
            case 6:
                persoRule();
                break;
            case 7:
                groupRule();
                break;
        }
    }


}
