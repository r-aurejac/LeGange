package com.example.legange.Rounds;

import com.example.legange.Bloc.BlackJackBloc;
import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.PlayersManager;

public class FinalRound extends Round{
    public FinalRound(){

        rules.add(new TextBloc(ScreenType.SCORE,"BRAVO CHAMPION", PlayersManager.getPlayerRankingList().get(0).getName() + "Remporte le Gange félicitation !!!!!"));
    }
}
