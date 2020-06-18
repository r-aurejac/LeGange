package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.legange.Player.PlayerList;
import com.example.legange.Rule.RandomPlayerRule;
import com.example.legange.Rule.RoleAttributionRule;
import com.example.legange.Rule.WritingRule;
import com.example.legange.GameData.AnnouncementData;
import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.GameData.RoleData;
import com.example.legange.Rule.Rule;
import com.example.legange.GameData.RuleData;
import com.example.legange.RuleInterface;
import com.example.legange.UI.Grid.GridFragment;
import com.example.legange.UI.Score.ScoreFragment;
import com.example.legange.UI.SelectPlayer.SelectPlayerFragment;
import com.example.legange.str;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RuleInterface {



    LinearLayout linearLayout;
    int roleIndex = 0;
    RoleData roleData;
    int announcementIndex = 0;

    int ruleIndex = 0;

    boolean gameEnding = false;

    Rule currentRule;

    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    ArrayList<Rule> rules;

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

        Collections.shuffle(players);
        PlayerList.setPlayerList(players);
        roleData = new RoleData(players);
        ruleData = new RuleData();
        gameLinearLayout = (LinearLayout) findViewById(R.id.game_linear_layout);

        organizeRules();


    }



    private void organizeRules()
    {
        AnnouncementData announcementData = new AnnouncementData();

        rules = new ArrayList<>();





        rules.add(announcementData.getAnnouncement().get(0));

        for(int i = 0; i < players.size(); i++)
            rules.add(roleData.getRoles().get(i));
        rules.add(announcementData.getAnnouncement().get(1));
        for(Rule rule : ruleData.getRules())
            rules.add(rule);


        nextRule();
    }

    void nextRule() {


        if (ruleIndex<rules.size()) {
            currentRule = rules.get(ruleIndex);
            showRule();
            ruleIndex++;

        }

        else if(!gameEnding)
            end();

        else { Intent intent = MainActivity.newIntent(getApplicationContext());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);}


    }


    private void showRule()
    {
        
        if(currentRule.getIndice() < currentRule.getSize()) {


            currentRule.show(this.getSupportFragmentManager(), players);

        }
        else nextRule();


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
        currentRule.setNextScreen(str.SCORE);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, GridFragment.newInstance(players));

        fragmentTransaction.commit();
    }


    private void showPersoRuleEnd()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, YesNoFragment.newInstance(currentRule.getRulePlayers().get(0),(currentRule.getPoints()))); //A BESOIN DE FIX
        fragmentTransaction.commit();
    }
    private void showPlayerSelection()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, SelectPlayerFragment.newInstance(players, currentRule.getPoints()));
        fragmentTransaction.commit();
    }



    private void end()
    {

        Collections.sort(players);

        //if(thereIsEquality())
        AnnouncementData ad = new AnnouncementData();

        if(players.get(0).getRole().equals(str.CREME))
        {
            currentRule = ad.getEndAnnouncement(1,players.get(0), players.get(1));
            showRule();
        }
        else
        {
            currentRule = ad.getEndAnnouncement(0,players.get(0));
            showRule();
        }

        gameEnding =true;

    }

    private void openGift()
    {
        currentRule = ruleData.getGift();
        showRule();
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

    @Override
    public void onRuleEnd() {

        if(currentRule.getIndice() < currentRule.getSize()) // si  tous les ecrans de la règle en cours n'ont pas été affiché
            showRule();
        else {

            if ((currentRule.getNextScreen() == str.GROUP))
                showPlayerSelection();
            else if (currentRule.getNextScreen() == str.PERSO)
                showPersoRuleEnd();
            else if (currentRule.getNextScreen() == str.UNKNOWN)
                showRule();
            else if (currentRule.getNextScreen() == str.SCORE)
                showScore();

            else if (currentRule.getNextScreen() == str.GIFT)
                openGift();
            else if (currentRule.getNextScreen() == str.PIRATE)
                showPirateRule();
            else if (currentRule.getNextScreen() == str.ANNOUNCEMENT)
                showRule();
            else if (currentRule.getNextScreen() == str.END_ANNOUNCEMENT)
                showRule();
        }
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
        Rule chefRule = new WritingRule("Règle du Chef",rule,0,str.UNKNOWN,1,1);
        rules.add(chefRule);
        showRule();

    }

}
