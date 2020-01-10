package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.legange.GameData.AnnouncementData;
import com.example.legange.Class.Player;
import com.example.legange.R;
import com.example.legange.GameData.RoleData;
import com.example.legange.Class.Rule;
import com.example.legange.GameData.RuleData;
import com.example.legange.RuleInterface;
import com.example.legange.str;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RuleInterface {



    LinearLayout linearLayout;
    int roleIndex = 0;

    int announcementIndex = 0;

    int ruleIndex = 0;

    boolean gameEnding = false;

    Rule ruleCache;

    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    ArrayList<Rule> roles,rules;
    RuleData ruleData;
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
        ruleData = new RuleData();
        gameLinearLayout = (LinearLayout) findViewById(R.id.game_linear_layout);
        Collections.shuffle(players);
        organizeRules();


    }


    @Override
    public void onRuleEnd() {
            if( (ruleCache.getNextScreen() == str.GROUP))
                showPlayerSelection();
            else if(ruleCache.getNextScreen() == str.PERSO)
                showPersoRuleEnd();
            else if(ruleCache.getNextScreen() == str.UNKNOWN)
                nextRule();
            else if(ruleCache.getNextScreen() == str.SCORE)
                showScore();
            else if(ruleCache.getNextScreen() == str.WRITE_NEW_RULE)
                showWritingRule(1);
            else if(ruleCache.getNextScreen() == str.WRITE)
                showWritingRule(players.size());
            else if(ruleCache.getNextScreen() == str.BLACKJACK)
                showBlackJack(ruleCache);
            else if(ruleCache.getNextScreen() == str.GIFT)
               openGift();
            else if(ruleCache.getNextScreen() == str.PIRATE)
                showPirateRule();
            else if(ruleCache.getNextScreen() == str.ANNOUNCEMENT)
                nextRule();
            else if(ruleCache.getNextScreen() == str.END_ANNOUNCEMENT)
                nextRule();

    }


    @Override
    public void toScore() {
        showScore();
    }

    @Override
    public void toNextRule() {
        nextRule();
    }

    @Override
    public void toPlayerSelection() {
        showPlayerSelection();
    }

    @Override
    public void OnChefRuleEnd(String rule) {
        Rule chefRule = new Rule("Règle du Chef",rule,0,str.UNKNOWN);
        rules.add(chefRule);
        nextRule();
    }


    private void organizeRules()
    {
        RoleData roleData = new RoleData();

        int k =0;
        roles = roleData.getRoles(players);
        rules = new ArrayList<>();
        for(int i = 0; i<players.size();i++) {
            rules.add(ruleData.getPersoRule(players.get(i), i));
            k++;
        }
        for(int i = k; i< ruleData.persoRules.size(); i++) {
            rules.add(ruleData.getPersoRule(Player.getRandomPlayer(players), i));
        }
        int j = 0;
        for(int i = 0; i< ruleData.groupRules.size(); i++)
        {
            rules.add(ruleData.getGroupRule(Player.getRandomPlayer(players), i));

        }
        rules.addAll(roleData.getRoleRules(players));
        Collections.shuffle(rules);


        nextRule();
    }

    void nextRule() {
        if (announcementIndex==0) {
            AnnouncementData ad = new AnnouncementData();
            ruleCache = ad.getAnnouncement(announcementIndex);
            showRule(ruleCache);
            announcementIndex++;
        }
        else if (roleIndex<players.size()) {
            ruleCache = roles.get(roleIndex);
            ruleCache.getRulePlayers().get(0).incrementScore(ruleCache.getPoints());
            showRule(ruleCache);

            roleIndex++;
        }
        else if((announcementIndex==1))
        {
            AnnouncementData ad = new AnnouncementData();
            ruleCache = ad.getAnnouncement(announcementIndex);
            showRule(ruleCache);
            announcementIndex++;
        }

        else if (ruleIndex<rules.size()) {
            ruleCache = rules.get(ruleIndex);
            showRule(ruleCache);
            ruleIndex++;

        }

        else if(!gameEnding)
            end();

        else { Intent intent = MainActivity.newIntent(getApplicationContext());
            getApplicationContext().startActivity(intent);}


    }


    private void showRule(Rule rule)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, RuleFragment.newInstance(rule, players));
        fragmentTransaction.commit();
    }
    private void showWritingRule(int phases)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, WriteFragment.newInstance(ruleCache, players,phases));


        fragmentTransaction.commit();
    }
    private void showBlackJack(Rule rule)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, BlackJackFragment.newInstance(players));
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
        ruleCache.setNextScreen(str.SCORE);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, PirateFragment.newInstance(players,"test"));

        fragmentTransaction.commit();
    }


    private void showPersoRuleEnd()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, PersoRuleEndFragment.newInstance(ruleCache.getRulePlayers().get(0),(ruleCache.getPoints()))); //A BESOIN DE FIX
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

        //if(thereIsEquality())
        AnnouncementData ad = new AnnouncementData();

        if(players.get(0).getRole().equals(str.HINDOU))
        {
            ruleCache= ad.getEndAnnouncement(1,players.get(0), players.get(1));
            showRule(ruleCache);
        }
        else
        {
            ruleCache = ad.getEndAnnouncement(0,players.get(0));
            showRule(ruleCache);
        }

        gameEnding =true;

    }

    private void openGift()
    {
        ruleCache = ruleData.getGift();
        showRule(ruleCache);
    }

    private boolean thereIsEquality()
    {
        int count = 0;
        for(int i = 1; i<players.size(); i++)
        {
            if(players.get(0).getScore()==players.get(i).getScore())
                count++;
        }
        if (count>0)
            return true;
        else return false;
    }



}
