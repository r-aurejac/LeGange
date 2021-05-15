package com.example.legange.Events;

import java.util.ArrayList;
import java.util.Collections;

public class Lottery {

    int size = 20;

    ArrayList<LotteryTicket> tickets;

    public Lottery()
    {
        addPrizes();
        completeWithLosingtickets();
        Collections.shuffle(tickets);
    }

    void addPrizes()
    {
        tickets.add(new LotteryTicket("Un cul sec"));
        tickets.add(new LotteryTicket("5 points"));
        tickets.add(new LotteryTicket("Un grand verre d'eau"));
        tickets.add(new LotteryTicket("Un grand verre d'eau"));

    }

    void completeWithLosingtickets()
    {
        for(int i = tickets.size(); i<size; i++)
        {
            tickets.add(new LotteryTicket("Rien"));
        }
    }

    class LotteryTicket {
        String prize;
        int useNumber = 0;

        void incrUseNumber()
        {
            useNumber++;
        }

        public LotteryTicket(String prize)
        {
            this.prize = prize;
        }


    }

}

