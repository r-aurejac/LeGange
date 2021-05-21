package com.example.legange.Rounds;

import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Rand;

public class JungleRound extends Round{

    public JungleRound() {

        rules.add(new TextBloc(ScreenType.NEXTRULE,"La Jungle","Une série de règle totalement random va suivre"));


        rules.add(new TextBloc(ScreenType.SCORE,"Chromosome 21",
                "Règle du 21, le premier qui se trompe gagne 3 points, être désigné au 21 = 1 cul sec"));

        rules.add(new TextBloc(ScreenType.SCORE,"Poker de rognés",
                "La mise de départ est une gorgé, chacun leur tour les joueurs peuvent soit ajouter une gorgé, " +
                        "soit doubler soit se coucher. Pour se coucher il faut boire la moitié de la mise en cour. " +
                        "Le dernier joueur present perds 3 points et boit sa mise"));


        rules.add(new TextBloc(ScreenType.SCORE,"Il faut choisir",
                "votez tous pour un joueur qui va Gagner 1 point et boire 3 gorgées, si il n'y a aucune majorité tout le monde boit cul sec"));


        rules.add(new TextBloc(ScreenType.SCORE,"couleur",
                "tous le joueurs qui portent un vetement "+ Rand.randColorString() + " boivent 2 gorgées et perde 1 point"));

        rules.add(new TextBloc(ScreenType.SCORE,"shot",
                " tournée de shot, chaque joueur qui ne prend pas un shot de pur gagne 2 point"));

        rules.add(new TextBloc(ScreenType.SCORE,"Chromosome 21 le retour",
                "Règle du 21 mais à l'envers, le premier qui se trompe gagne 3 points, être désigné au 21 = 1 cul sec"));
    }

}
