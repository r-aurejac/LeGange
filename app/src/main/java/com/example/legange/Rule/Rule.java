package com.example.legange.Rule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.legange.Player.Player;
import com.example.legange.R;
import com.example.legange.UI.RuleFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Rule implements Serializable {


    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNextScreen(int nextscreen) {this.nextScreen = nextscreen;}

    private String name;

    public ArrayList<Player> getRulePlayers() {
        return rulePlayers;
    }

    private ArrayList<Player> rulePlayers;

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    private int points;



    public int getNextScreen() {
        return nextScreen;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    private int indice = 0;

    public int getSize() {
        return size;
    }

    protected int size = 1;

    private int nextScreen;
    private static final int ROLE_ATTRIBUTION = 0;
    private static final int TEXT = 1;

    public ArrayList<String> texts;

    public Rule(String name, String description,int points, int nextScreen)
    {
        texts = new  ArrayList<String>();
        texts.add(description);
        this.name = name;

        this.points = points;
        this.nextScreen = nextScreen;
        rulePlayers = new ArrayList<>();

    }


    public void show(FragmentManager fragmentManager, ArrayList<Player> players)
    {

        //FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.game_linear_layout, RuleFragment.newInstance(this, players,indice));
        fragmentTransaction.commit();
        indice++;

    }



    public void addText(String text)
    {
       texts.add(text);
       size++;
    }

    public void incrIndice()
    {
        indice++;
    }

}
