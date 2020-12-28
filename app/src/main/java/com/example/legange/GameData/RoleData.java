package com.example.legange.GameData;

import com.example.legange.Player.Player;
import com.example.legange.Player.PlayerList;
import com.example.legange.Rule.RoleAttributionRule;
import com.example.legange.Rule.RoleRule;
import com.example.legange.Rule.Rule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;

public class RoleData {


    public ArrayList<RoleAttributionRule> roles;
    ArrayList<Player> players;


 public RoleData(ArrayList<Player> players) {
     this.players = players;
     roles = new ArrayList<>();
     setRoles();
     Collections.shuffle(roles);

}

    private void setRoles()
    {
        roles.add(new RoleAttributionRule(str.CREME,
                "player est la crème. Il est tellement sympa que si il remporte le Gange il le partagera avec le deuxième joueur ayant le moins de point",
                0));

        roles.add(new RoleAttributionRule(str.VICTIME,
                "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
                0));

        roles.add(new RoleAttributionRule(str.PRECOCE,
                "player est le précoce. Toujours en avance, il Cul-sec un verre avant de commencer le jeu.",
                0));

        roles.add(new RoleAttributionRule(str.CHANCEUX,
                "player est le chanceux. Il commence avec 1 points supplémentaires, LA CHANCE.", 1));

        roles.add(new RoleAttributionRule(str.ATTARDE,
                "player est l'attardé il dispose d'une vie pour annuler sa défaite lors d'un jeu, c'est pas de sa faute il n'avait pas compris",
                0));

        roles.add(new RoleAttributionRule(str.VOLEUR,
                "player est le voleur",
                0));

        roles.add(new RoleAttributionRule(str.PARIEUR,
                "player est le parieur , il choisit 2 joueurs et déisgne lequel sera devant au classement ",
                0));

        //toujours avoir un meneur ---> il est placé à l'indice 0 après le mélange
        Collections.shuffle(roles);
        roles.add(0,new RoleAttributionRule(str.CHEF,
                "player est le Meneur, son charisme naturel fait que tout le monde l'écoute, c'est à lui de trancher en cas d'égalité ",
                0));

        for(int i = 0; i < players.size(); i++)
            roles.get(i).playerAttribution(players.get(i));

    }



    public ArrayList<RoleAttributionRule> getRoles()
    {
        return roles;
    }



}



