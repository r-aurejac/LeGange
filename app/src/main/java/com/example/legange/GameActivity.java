package com.example.legange;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RuleInterface {

    private static final int ANNOUNCEMENT = 0;
    private static final int ROLE = 1;
    private static final int GROUP = 2;
    private static final int PERSO = 3;
    private static final int WRITE = 4;
    private static final int END_ANNOUNCEMENT = 5;
    LinearLayout linearLayout;
    int roleIndex = 0;
    boolean announcement = true;
    int announcementIndex = 0;
    boolean roleAttribution = false;
    boolean rulesPlaying =false;
    int ruleIndex = 0;
    int introIndex =0;
    boolean ruleChange = true;
    boolean gameEnding = false;
    int groupRuleIndex = 0;
    int persoRuleIndex = 0;
    Rule ruleCache;

    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    ArrayList<Rule> roles,rules;
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
        organizeRules();


    }


    @Override
    public void onRuleEnd() {
            if(ruleCache.getType() == GROUP)
                showPlayerSelection();
            else if(ruleCache.getType() == PERSO)
                showPersoRuleEnd();
            else if(ruleCache.getType() == ANNOUNCEMENT)
                nextRule();
            else if(ruleCache.getType() == ROLE)
                nextRule();
            else if(ruleCache.getType() == END_ANNOUNCEMENT)
                showScore();


    }

    @Override
    public void onScoreEnd() {

            nextRule();
    }

    @Override
    public void onPointsAttributionEnd() {
        showScore();
    }

    private void organizeRules()
    {
        roles = new ArrayList<>();
        rules = new ArrayList<>();
        for(int i = 0; i<players.size();i++) {
            roles.add(data.getRole(players.get(i), i));
            rules.add(data.getGroupRule(players.get(i), i));
            rules.add(data.getPersoRule(players.get(i), i));
        }


        nextRule();
    }

    void nextRule() {
        if (announcementIndex<2) {
            ruleCache = data.getAnnouncement(announcementIndex);
            showTextRule(ruleCache);
            announcementIndex++;
        }
        else if (roleIndex<players.size()) {
            ruleCache = roles.get(roleIndex);
            ruleCache.getRulePlayers().get(0).incrementScore(ruleCache.getPoints());
            showTextRule(ruleCache);

            roleIndex++;
        }
        else if (ruleIndex<rules.size()) {
            ruleCache = rules.get(ruleIndex);
            showTextRule(ruleCache);
            ruleIndex++;
            Log.d("test on rule end", String.valueOf(ruleIndex));
        }

        else if(gameEnding == false)
            end();

        else { Intent intent = MainActivity.newIntent(getApplicationContext());
            getApplicationContext().startActivity(intent);}


    }


    private void showTextRule(Rule rule)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, RuleFragment.newInstance(rule, players));
        fragmentTransaction.commit();
    }


    private void showScore()
    {
        gameLinearLayout.removeAllViews();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.game_linear_layout, ScoreFragment.newInstance(players));
        fragmentTransaction.commit();

    }

    private void showPirateRule()
    {
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.game_linear_layout, PirateFragment.newInstance(players,"test"));
                fragmentTransaction.commit();

    }


    private void showPersoRuleEnd()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, PersoRuleEndFragment.newInstance(ruleCache.getRulePlayers().get(0),(ruleCache.getPoints())));
        fragmentTransaction.commit();


    }
    private void showPlayerSelection()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, SelectPlayerFragment.newInstance(players,ruleCache.getPoints()));
        fragmentTransaction.commit();

    }

    private void end()
    {

        Collections.sort(players);
        if(players.get(0).getRole().equals("guerrier indien"))
        {
            ruleCache=data.getEndAnnouncement(0,players.get(0), players.get(1));
            showTextRule(ruleCache);
        }
        else
        {
            ruleCache = data.getEndAnnouncement(1,players.get(0));
            showTextRule(ruleCache);
        }

        gameEnding =true;

    }



}
