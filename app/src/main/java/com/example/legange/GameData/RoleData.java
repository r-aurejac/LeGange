package com.example.legange.GameData;

import com.example.legange.Class.Player;
import com.example.legange.Class.Role;
import com.example.legange.Class.RoleRule;
import com.example.legange.Class.Rule;
import com.example.legange.Class.WritingRule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;

public class RoleData {


    public ArrayList<Rule> roles;
    ArrayList<Player> players;


 public RoleData(ArrayList<Player> players) {
     this.players = players;
     roles = new ArrayList<>();
     setRoles();
     Collections.shuffle(roles);

}

    private void setRoles()
    {
        roles.add(new Role(str.CREME,
                "player est la crème. Il est tellement sympa que si il remporte le Gange il le partagera avec le deuxième joueur ayant le moins de point",
                0, str.UNKNOWN));

        roles.add(new Role(str.VICTIME,
                "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
                0, str.UNKNOWN));

        roles.add(new Role(str.PRECOCE,
                "player est le précoce. Toujours en avance, il Cul-sec un verre avant de commencer le jeu.",
                0, str.UNKNOWN));

        roles.add(new Role(str.CHANCEUX,
                "player est le chanceux. Il commence avec 1 points supplémentaires, LA CHANCE.", 1, str.UNKNOWN));

        roles.add(new Role(str.ATTARDE,
                "player est l'attardé il dispose d'une vie pour annuler sa défaite lors d'un jeu, c'est pas de sa faute il n'avait pas compris",
                0, str.UNKNOWN));

        roles.add(new Role(str.VOLEUR,
                "player est le voleur",
                0, str.UNKNOWN));

        roles.add(new Role(str.PARIEUR,
                "player est le parieur , il choisit 2 joueurs et déisgne lequel sera devant au classement ",
                0, str.UNKNOWN));

        //toujours avoir un meneur ---> il est placé à l'indice 0 après le mélange
        Collections.shuffle(roles);
        roles.add(new Role(str.CHEF,
                "player est le Meneur, son charisme naturel fait que tout le monde l'écoute, c'est à lui de trancher en cas d'égalité ",
                0, str.UNKNOWN));

    }



    public ArrayList<Rule> getRoles()
    {
        return roles;
    }

    //_______Voleur___________

    public ArrayList<Rule> getRadinRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new RoleRule("Trésor",
            "Le voleur va cacher son butin, les autres joueurs vont tenter de le retrouver, attention il y a aussi un piège ! L'action se déroule sur l'écran suivant",
            0, str.PIRATE,player));

        Rules.add(new RoleRule("prit en flag",
                "Les autres joueurs votent pour te punir : soit il boivent 3 gorgées chacun mais le voleur perd 2 points, soit tout le monde trinque avec le voleur ",
                -2, str.PERSO,player));
        return Rules;
    }

    public ArrayList<Rule> getChefRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        //Rules.add(new WritingRule("Nouvelle règle",
          //      "écrit la règle de son choix sur l'écran suivant, la sentance max est 1 cul sec ou 10 gorgées divisibles, pas de point en jeu ",
            //    0, str.UNKNOWN,0,1));

        Rules.add(new RoleRule("Mutinerie",
                "Une rebellion a été lancé par le charismatique player, si il réussi il deviendra le nouveau chef. Choisissez au bout de 3 de trahir le chef ou de rester fidèle, les minoritaires (y compris le chef) perdent 2 points et boivent 3 gorgées ",
                0, str.GIFT,player));


        return Rules;
    }

    public ArrayList<Rule> getParieurRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new RoleRule("Point sur le pari",
                "si le pronostique du parieur est bon il distribu 4 gorgée si non il les boit",
                4, str.GROUP,player));

        Rules.add(new RoleRule("Point sur le pari",
                "si le pronostique du parieur est bon il gagne 2 point",
                2, str.PERSO,player));
        Rules.add(new RoleRule("Point sur le pari",
                "si le pronostique du parieur est faux il perd 2 points",
                -2, str.PERSO,player));


        return Rules;
    }

    public ArrayList<Rule> getVictimeRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new RoleRule("vengeance",
                "ceux qui ont déjà distribué des gorgées à la victime boivent 4 gorgées",
                0, str.UNKNOWN,player));

        Rules.add(new RoleRule("plus grosse victime tu meurs",
                "Si la victime a bu à la règle précèdente elle gagne 4 points, Si non elle boit le nombre de gorgées max bu à la règle precédente",
                4, str.PERSO,player));


        return Rules;
    }
    public ArrayList<Rule> getHindouRules(Player player)
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new RoleRule("trop sympa",
                "offre 2 points à un joueur",
                -2, str.PERSO,player));

        Rules.add(new RoleRule("un vrai pote",
                "choisis un joueur don tu prendra les 5 prochaine gorgées qu'il reçoit ",
                2, str.PERSO,player));


        return Rules;
    }


    public ArrayList<Rule> getRoleRules()
    {

        Player p;
     ArrayList<Rule> roleRules = new ArrayList<Rule>();
        p=Player.findPlayerByRole(players,str.VOLEUR);
        if(Player.findPlayerByRole(players,str.VOLEUR)!=null)
            roleRules.addAll(getRadinRules(p));

        p=Player.findPlayerByRole(players,str.CHEF);
        if(Player.findPlayerByRole(players,str.CHEF)!=null)
            roleRules.addAll(getChefRules(p));

        p = Player.findPlayerByRole(players,str.PARIEUR);
        if(p!=null)
            roleRules.addAll(getParieurRules(p));

        p = Player.findPlayerByRole(players,str.VICTIME);
        if(p!=null)
            roleRules.addAll(getVictimeRules(p));

        p = Player.findPlayerByRole(players,str.CREME);
        if(p!=null)
            roleRules.addAll(getHindouRules(p));

        for(Rule rule : roleRules)
        {
            rule.texts.set(rule.getIndice(),rule.texts.get(rule.getIndice()).replace("player",Player.getRandomPlayer(players).getName()));

        }
        return roleRules;
    }







}



