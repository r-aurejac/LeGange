package com.example.legange.Rounds;

import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;


public class IntroductionRound extends Round {

    public IntroductionRound() {

        super();
        TextBloc intro1 = new TextBloc(ScreenType.NEXTRULE,"Intro", "Bienvenue dans cette nouvelle edition du Gange");
        rules.add(intro1);
        TextBloc intro2 = new TextBloc(ScreenType.NEXTRULE,"Intro", "Les participants vont tenter de marquer des points et gagner des gorgées en s'affrontant sur differentes épreuves");
        rules.add(intro2);
        TextBloc intro3 = new TextBloc(ScreenType.NEXTRULE,"Intro", "Le joeuur avec le meilleur score aura le privilège de boire la sainte eau du Gange");
        rules.add(intro3);
        TextBloc intro4 = new TextBloc(ScreenType.NEXTRULE,"Intro", "Chaque joueur verse un peu de son verre dans le Gange");
        rules.add(intro4);
    }

}


