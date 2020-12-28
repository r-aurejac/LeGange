package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.legange.GameData.IndividualRuleData;
import com.example.legange.GameData.ItemData;
import com.example.legange.Player.PlayerList;
import com.example.legange.Rule.ItemAttributionRule;
import com.example.legange.Rule.WritingRule;
import com.example.legange.GameData.AnnouncementData;
import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.Rule.Rule;
import com.example.legange.GameData.GroupRuleData;
import com.example.legange.RuleInterface;
import com.example.legange.UI.Score.ScoreFragment;
import com.example.legange.UI.SelectPlayer.SelectPlayerFragment;
import com.example.legange.str;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RuleInterface {


    int currentGameTurn = 4;

    int playerIndex = 0;
    int groupRuleIndex = 0;
    int individualRuleIndex =0;
    int introductionRuleIndex = 0;
    ItemData itemData;
    int gamePhase = 0;
    Rule currentRule;
    boolean isPersoRuleTurn = false;
    private final static String PLAYERS = "players";
    ArrayList<Player> players;
    ArrayList<Rule> introductionRules,invidiualRules;

    GroupRuleData groupRuleData;
    IndividualRuleData individualRuleData;
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

        groupRuleData = new GroupRuleData();
        individualRuleData = new IndividualRuleData();
        gameLinearLayout = (LinearLayout) findViewById(R.id.game_linear_layout);

        setIntroductionRules();


    }


    private void setIntroductionRules()
    {
        AnnouncementData announcementData = new AnnouncementData();

        introductionRules = new ArrayList<>();

        introductionRules.add(announcementData.getAnnouncement().get(0));
        itemData = new ItemData();
        for(int i = 0; i < itemData.baseItems.size(); i++)
            introductionRules.add(new ItemAttributionRule(players.get(i),itemData.baseItems.get(i)));
        introductionRules.add(announcementData.getAnnouncement().get(1));



        nextRule();
    }

    void nextRule() {

        if(groupRuleIndex >= groupRuleData.getRules().size())
            gamePhase = 5;

        switch(gamePhase)
        {

            case(0):
            {
                if(introductionRuleIndex < introductionRules.size()) {
                    currentRule = introductionRules.get(introductionRuleIndex);
                    showNextScreen();
                    introductionRuleIndex++;
                }
                else gamePhase = 1;
                break;
            }
            case(1):
            {
                currentRule = individualRuleData.getRules().get(individualRuleIndex);
                showPlayerFragment();

                individualRuleIndex++;
                if(individualRuleIndex>= individualRuleData.getRules().size())
                    individualRuleIndex = 0;

                playerIndex++;
                if(playerIndex>= players.size()) {
                    playerIndex = 0;
                    currentGameTurn ++;
                    Log.d("test", "turn" +String.valueOf(currentGameTurn));
                }
                gamePhase = 2;
                break;
            }
            case(2):
            {
                currentRule = groupRuleData.getRules().get(groupRuleIndex);
                showNextScreen();
                groupRuleIndex++;

                gamePhase = 1;
                break;
            }
            case(5):
            {
                Collections.sort(players);

                //if(thereIsEquality())
                AnnouncementData ad = new AnnouncementData();

                currentRule = ad.getEndAnnouncement(0,players.get(0));
                showNextScreen();


                gamePhase = 6;
            }
            case(6):
            {
                Intent intent = MainActivity.newIntent(getApplicationContext());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        }

    }


    private void showNextScreen()
    {
        if(currentRule.getIndice() < currentRule.getSize()) {
            currentRule.show(this.getSupportFragmentManager());
        }
        else
            if(gamePhase>0)
                showScore();
            else nextRule();

    }
    private void showPlayerFragment()
    {

        gameLinearLayout.removeAllViews();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.game_linear_layout, PlayerFragment.newInstance(players.get(playerIndex),currentRule.texts.get(0)));
        fragmentTransaction.commit();


    }

    private void showScore()
    {
        gameLinearLayout.removeAllViews();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.game_linear_layout, ScoreFragment.newInstance(players,currentRule.texts.get(0)));
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





    private void openGift()
    {
        currentRule = groupRuleData.getGift();
        showNextScreen();
    }

    private boolean thereIsEquality()
    {
        int count = 0;
        for(int i = 1; i<players.size(); i++)
        {
            if(players.get(0).getScore()==players.get(i).getScore())
                count++;
        }
        return count > 0;
    }

    @Override
    public void toNextScreen() {
        showNextScreen();
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
        Rule chefRule = new WritingRule("Règle du Chef",rule,0,1,1);
        introductionRules.add(chefRule);
        showNextScreen();

    }

}
