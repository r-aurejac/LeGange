package com.example.legange.Rounds;

import com.example.legange.Bloc.BlackJackBloc;
import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;

public class BlackJackRound extends Round{

    public BlackJackRound(){

        rules.add(new TextBloc(ScreenType.NEXTRULE,"White Jack","Chacun votre additionez des valeurs aléatoires de 1 à 11. Dépasser 21 -> 4 points, 21 -> -1 point, 20 -> 1 point,  moins de 20 -> 2 points"));
        rules.add(new BlackJackBloc(ScreenType.SCORE));
    }
}
