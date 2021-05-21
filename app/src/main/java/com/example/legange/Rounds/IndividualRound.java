package com.example.legange.Rounds;


import com.example.legange.Bloc.AbstractBloc;
import com.example.legange.Bloc.RandomPlayersTextBloc;
import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;

import java.util.ArrayList;
import java.util.Collections;

public class IndividualRound extends Round {


    public IndividualRound()
    {
        rules.add(new TextBloc(ScreenType.NEXTRULE,"Défies","Une série de règles individuelles va suivre"));

        ArrayList<AbstractBloc> tempRules = new ArrayList<>();
        ArrayList<Player> players = PlayersManager.getPlayerListCopy();
        Collections.shuffle(players);

        tempRules.add(new TextBloc(ScreenType.SCORE,"musique",
                players.get(0).getName() + " Player tente de gagner 3 points en donnant un titre ou un chanteur/groupe qui n'a aucun rapport avec la musique en cours" ));

        tempRules.add(new TextBloc(ScreenType.SCORE,"classement",
                players.get(1).getName() + " Si Player peut réciter le classement dans le désordre il gagne 3 points"));

        tempRules.add(new TextBloc(ScreenType.SCORE,"Grosse Merde", PlayersManager.players.get(1).getName() + " bravo tu remportes 3 point sans rien faire, sauf si tu prouves que tu es la pire des merde" +
                players.get(2).getName() + " et que tu ne les mérites pas dans un discours de 30 secondes" ));

        tempRules.add(new TextBloc(ScreenType.SCORE, "pile ou face",
                players.get(3).getName() + "Player fait un pile ou face si il gagne, il obtient 3 points, si non il distribue 3 gorgées"));

        rules.add(new RandomPlayersTextBloc(ScreenType.SCORE,"vrai ou faux",
                players.get(4).getName() + " donne une information réelle ou fictive. Les autres joueur votent chacun leur tour, " +
                        "tout le monde à bon -> player gagne 3 point, " +
                        "si non 2 gorgées pour ceux se trompent (4 gorgées si tout le monde)",
                1));



        for(int i = 0; i < players.size(); i++) {

            if(i < tempRules.size())
            {
                rules.add(tempRules.get(i));
            }
            else {
                rules.add(new TextBloc(ScreenType.SCORE, "Shi Fu Puit",
                        players.get(i).getName() + "  défie le joueur de ton choix Au Shi Fu Puit, shi fu mi avec puit autorisé 3 manched gagnantes, le juouer qui gagne, obtient 3 point"));
            }
        }



        rules.add(new TextBloc(ScreenType.LOTTERY,"Lotterie", "Il est temps de revenir à la lotterie"));

    }



}
