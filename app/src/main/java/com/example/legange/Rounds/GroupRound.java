package com.example.legange.Rounds;

import com.example.legange.Bloc.RandomPlayersTextBloc;
import com.example.legange.Bloc.TextBloc;
import com.example.legange.Navigation.ScreenType;

public class GroupRound extends Round {

    public GroupRound() {
        rules.add(new TextBloc(ScreenType.NEXTRULE, "Règles de groupes", "Une série de règle de groupe va suivre"));

        rules.add(new RandomPlayersTextBloc(ScreenType.SCORE, "Catégorie", "player1 choisit une catégorie, " +
                "celui qui répète ou n'a plus d'idée gagne 1 point", 1));

        rules.add(new RandomPlayersTextBloc(ScreenType.SCORE, "Rime", "player1 choisit une rime, " +
                "celui qui répète ou n'a plus d'idée gagne 1 point", 1));

        rules.add(new RandomPlayersTextBloc(ScreenType.SCORE, "Trouve ou boit",
                " player1 imagine ce qu'il veut (objet, personne, équipe, groupe),chacun son tour donne une question (oui,non) " +
                        "et une réponse, celui qui trouve gagne perd 1 point",
                1));

    }
}
