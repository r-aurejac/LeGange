package com.example.legange.Rounds;

import com.example.legange.Bloc.TextBloc;
import com.example.legange.Bloc.WriteTextBloc;
import com.example.legange.Navigation.ScreenType;
import com.example.legange.Player.Player;
import com.example.legange.Player.PlayersManager;

public class WriteRound extends Round {


    public WriteRound() {
        rules.add(new TextBloc(ScreenType.NEXTRULE, "écrivains", "Chaque joueur aura son tour pour donner une consigne (blague, texte à completer, question etc...), les autres devront répondrent anonymement " +
                "en écrivant sur le téléphone, l'auteur de la consigne désignera la réponse qui mérite de gagner 3 points"));

        for(Player player : PlayersManager.players)
        {
            String description = "Au tour de " + player.getName();
            rules.add(new TextBloc(ScreenType.NEXTRULE,"Ecrivain" ,description));
            rules.add(new WriteTextBloc(ScreenType.SCORE,PlayersManager.players.size()-1));
        }
    }

}
