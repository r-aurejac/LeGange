package com.example.legange.Rounds;

import com.example.legange.Bloc.TextBloc;
import com.example.legange.Events.Lottery;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;


public class IntroductionRound extends Round {

    public IntroductionRound() {

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Intro", "Bienvenue dans cette nouvelle edition du Gange"));

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Intro", "Les participants vont tenter de marquer des points et gagner des gorgées en s'affrontant sur differentes épreuves"));

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Intro", "Le joueur avec le meilleur score aura le privilège de boire la sainte eau du Gange"));

        for(int i = 0; i< PlayersManager.players.size(); i++)
        {
            String description = "Ce soir nous recevons : " + PlayersManager.players.get(i).getName();
            if(i<PlayersManager.players.size()-1) {
                description += " ,un mot pour les téléspectateur ?";
            }
            else {
                description +=  " qui finit son verre cul sec";
            }

            rules.add(new TextBloc(ScreenType.NEXTRULE,"Intro", description));
        }

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Intro", "Chaque joueur verse un peu de son verre dans le Gange"));

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Lotterie", "Vous pouvez tentez de gagner des points et d'autres " +
                "récompenses exceptionelles en jouant à la lotterie"));

        rules.add(new TextBloc(ScreenType.NEXTRULE,"Lotterie", "Pour cela il vous faut obtenir des tickets lors des differentes épreuves, " +
                "libre à vous de choisir les numéros de vos tickets, ils vont de 0 à " + Lottery.getStringTicketsSize()));

        rules.add(new TextBloc(ScreenType.LOTTERY,"Lotterie", "Si vous Choisissez un numéro déjà utilisé votre ticket sera invalidé. " +
                "Vous devrez en chosir un autre mais vous gagnerez des gorgés à boire pour vous consoler"));


        for (Player player : PlayersManager.players)
        {
            player.addTickets(1);
        }
    }

}


