package com.example.legange.Player;



import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable, Comparable {

    private int rule_score = 0;
    private  String name;
    private int score;

    private int ticketsNumber = 0;

    public Player(String name)
    {
        this.name = name;

    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public void addTickets(int number) {
        ticketsNumber += number;
    }

    public void deleteTicket() {
        ticketsNumber--;
    }

    public int getRule_score() {
        return rule_score;
    }

    public void setRule_score(int rule_score) {
        this.rule_score = rule_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int inc)
    {
        score += inc;
    }

    @Override
    public int compareTo(Object o) {

        Player player = (Player) o;
        return (this.score - player.score);
    }
}
