package com.example.legange.Events;

import com.example.legange.Player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Lottery {

    private static Lottery lottery;

    public static Lottery getInstance()
    {
        if(lottery != null)
        {
            return lottery;
        }
        else
        {
            lottery = new Lottery();
            return lottery;
        }
    }

    static int ticketsSize = 35;
    public static String getStringTicketsSize() {return String.valueOf(ticketsSize);}

    public ArrayList<LotteryTicket> tickets;

    private Lottery()
    {
        tickets = new ArrayList<>();
        addPrizes();
        fillAndShuffle();
        Collections.shuffle(tickets);
    }

    void addPrizes()
    {
        tickets.add(new LotteryTicket("Un cul sec",0));
        tickets.add(new LotteryTicket("Un cul sec",0));
        tickets.add(new LotteryTicket("3 points",3));
        tickets.add(new LotteryTicket("Un grand verre d'eau",0));
        tickets.add(new LotteryTicket("Un shot pur",0));
        tickets.add(new LotteryTicket("Deux shots coupés",0));
        tickets.add(new LotteryTicket("1 points",3));
        tickets.add(new LotteryTicket("1 points",3));
        tickets.add(new LotteryTicket("2 points",3));
        tickets.add(new LotteryTicket("2 points",3));
        tickets.add(new LotteryTicket("Deux shots coupés",0));
    }

    void fillAndShuffle()
    {
        for(int i = tickets.size(); i< ticketsSize; i++)
        {
            tickets.add(new LotteryTicket("Rien",0));
        }
        Collections.shuffle(tickets);
    }

    class LotteryTicket {
        String prize;
        int useCount = 0;
        int points = 0;
        public LotteryTicket(String prize, int points)
        {
            this.prize = prize;
            this.points = points;
        }
        void incrUseNumber()
        {
            useCount++;
        }
        String getStringUseCount()
        {
            return String.valueOf(useCount);
        }

    }

    public String useTicket(Player player, int ticketNumber) {

        String rewardText;

        if (ticketNumber >= 0 && ticketNumber < tickets.size()) {
            LotteryTicket ticket = tickets.get(ticketNumber);
            player.incrementScore(ticket.points);

            if (ticket.useCount == 0) {
                rewardText = ticket.prize;
                player.deleteTicket();
            } else {
                rewardText = "ticket déjà utilisé " + ticket.getStringUseCount()
                        + "fois, boit " + ticket.getStringUseCount() + " fois";
            }

            ticket.useCount++;

        }

        else rewardText = "Numéro invalide";
        return rewardText;
    }

}

