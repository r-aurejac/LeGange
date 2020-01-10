package com.example.legange.GameData;

import com.example.legange.Class.Player;
import com.example.legange.Class.Rule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;

public class RoleData {


    public ArrayList<Rule> roles;


 public RoleData() {
     roles = new ArrayList<>();
     setRoles();
     Collections.shuffle(roles);

}

    private void setRoles()
    {


        roles.add(new Rule(str.VICTIME,
                "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
                0, str.UNKNOWN));

        roles.add(new Rule(str.PRECOCE,
                "player est le précoce. Toujours en avance, il Cul-sec un verre plein avant de commencer le jeu.",
                0, str.UNKNOWN));

        roles.add(new Rule(str.CHANCEUX,
                "player est le chanceux. Il commence avec 1 points supplémentaires, LA CHANCE.", 1, str.UNKNOWN));

        roles.add(new Rule(str.GENEREUX,
                "player est très généreux, si il remporte le gange, il pourra le partager avec le 2ème joueur ayant le moins de points",
                0, str.UNKNOWN));

        roles.add(new Rule(str.RADIN,
                "player est le Radin",
                0, str.UNKNOWN));

        roles.add(new Rule(str.PARIEUR,
                "player est le parieur , si il finit premier il distribue un cul sec mais si il finit avant dernier il le boit. ",
                0, str.UNKNOWN));

        roles.add(new Rule(str.CHEF,
                "player est le chef ",
                0, str.UNKNOWN));
        Collections.shuffle(roles);
    }



    public ArrayList<Rule> getRoles(ArrayList<Player> players)
    {

        ArrayList<Rule> finalRoles = new ArrayList<Rule>();
        Rule role;
        for (int i = 0; i< players.size(); i++) {
            if (i == 0)
                role = new Rule(str.HINDOU,
                        "player est l'Hindou, il pourra personnaliser le gange. ",
                        0, str.UNKNOWN);
            else
                role = roles.get(i-1);

            role.getRulePlayers().add(players.get(i));
            players.get(i).setRole(role.getName());
            String string = role.getDescription().replace("player",players.get(i).getName());
            role.setDescription(string);
            finalRoles.add(role);
        }




        return finalRoles;
    }

    //_______Pirate___________

    public ArrayList<Rule> getRadinRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("Trésor",
            "Le radin va devoir cacher 2 petits sac d'or, attention l'un est piègé ! L'action se déroule sur l'écran suivant",
            0, str.PIRATE));

        Rules.add(new Rule("Prêt d'argent à taux d'interet",
                "Le radin choisit un joueur qui gagne entre 1 et 5 point mais qui devra en boire le double ",
                -2, str.GROUP));
        return Rules;
    }

    public ArrayList<Rule> getChefRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("Nouvelle règle",
                "écrit la règle de son choix sur l'écran suivant, la sentance max est 1 cul sec ou 10 gorgées divisibles, pas de point en jeu ",
                0, str.WRITE_NEW_RULE));

        Rules.add(new Rule("Mutinerie",
                "Une rebellion a été lancé par le charismatique player, si il réussi il deviendra le nouveau chef. Choisissez au bout de 3 de trahir le chef ou de rester fidèle, les minoritaires (y compris le chef) perdent 2 points et boivent 3 gorgées ",
                0, str.GIFT));


        return Rules;
    }

    public ArrayList<Rule> getParieurRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("Pari truqué",
                "Si le parieur coté est dernier il gagne 4 points, si non il boit 2 gorgées",
                4, str.PERSO,player));

        Rules.add(new Rule("Pari truqué",
                "Si le parieur coté est premier tous les autres perdent 1 point, si non il boit 2 gorgées",
                -1, str.GROUP));


        return Rules;
    }

    public ArrayList<Rule> getVictimeRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("vengeance",
                "ceux qui ont déjà distribué des gorgées à la victime boivent 2 gorgées",
                0, str.UNKNOWN));

        Rules.add(new Rule("plus grosse victime tu meurs",
                "Si la victime a bu à la règle précèdente elle gagne 4 points, Si non elle boit le nombre de gorgées max bu à la règle precédente",
                4, str.PERSO, player));


        return Rules;
    }
    public ArrayList<Rule> getHindouRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("un vrai indien",
                "L'Hindou boit une gorgée du gange ou perd 2 points",
                -2, str.PERSO,player));

        Rules.add(new Rule("Le vrai roi du gange",
                "Le guerrier indien boit 1 gorgée de 3 alcool differents pour gagner 2 point ",
                2, str.PERSO,player));


        return Rules;
    }


    public ArrayList<Rule> getRoleRules(ArrayList<Player> players)
    {

        Player p;
     ArrayList<Rule> roleRules = new ArrayList<Rule>();
        if(Player.findPlayerByRole(players,str.RADIN)!=null)
            roleRules.addAll(getRadinRules());

        if(Player.findPlayerByRole(players,str.CHEF)!=null)
            roleRules.addAll(getChefRules());

        p = Player.findPlayerByRole(players,str.PARIEUR);
        if(p!=null)
            roleRules.addAll(getParieurRules(p));

        p = Player.findPlayerByRole(players,str.VICTIME);
        if(p!=null)
            roleRules.addAll(getVictimeRules(p));

        p = Player.findPlayerByRole(players,str.HINDOU);
        if(p!=null)
            roleRules.addAll(getHindouRules(p));

        for(Rule rule : roleRules)
        {
            rule.setDescription(rule.getDescription().replace("player",Player.getRandomPlayer(players).getName()));

        }
        return roleRules;
    }







}



