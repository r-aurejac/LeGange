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


        roles.add(new Rule("victime",
                "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
                0, str.UNKNOWN));
        roles.add(new Rule("précoce",
                "player est le précoce. Toujours en avance, il Cul-sec un verre plein avant de commencer le jeu.",
                0, str.UNKNOWN));
        roles.add(new Rule("chanceux",
                "player est le chanceux. Il commence avec 1 points supplémentaires, LA CHANCE.", 1, str.UNKNOWN));

        roles.add(new Rule("guerrier indien",
                "player est le guerrier Indien. Habitué du Gange, si il finit dernier il le partage avec l'avant dernier.",
                0, str.UNKNOWN));
        roles.add(new Rule("pirate",
                "player est le pirate. ARRRRRRRRRRRh",
                0, str.UNKNOWN));

        roles.add(new Rule("cheval le moins coté",
                "player est le cheval le plus coté, si il finit premier il distribue un cul sec. ",
                0, str.UNKNOWN));
        roles.add(new Rule("enfant",
                "player est l'enfant gaté ",
                0, str.UNKNOWN));
        Collections.shuffle(roles);
    }



    public Rule getRole(Player player, int index)
    {
        Rule role;
        if(index == 0)
            role = new Rule("toxique",
                    "player est le toxique, il pourra personnaliser le gange. ",
                    0, str.UNKNOWN);
        else
        role = roles.get(index);

        role.getRulePlayers().add(player);
        player.setRole(role.getName());
        String string = role.getDescription().replace("player",player.getName());
        role.setDescription(string);
        return role;
    }

    public ArrayList<Rule> getPirateRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("Trésor",
            "Le pirate va devoir cacher un trésor, et un piège su l'écran suivant",
            0,str.PIRATE));

        Rules.add(new Rule("Mutinerie",
                "Un groupe mené par le charismatique player se rebelle contre le pirate , choisissez de rejoindre les mutins ou restez fidèle au capitaine, à 3 désignez l'un de ses 2 joueurs" +
                        "2 joueurs les loosers perdent 2 points et boivent 2 gorgées ",
                -2,str.GROUP));
        return Rules;
    }

    public ArrayList<Rule> getEnfantRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("enfant gaté",
                "Le hasard fait bien les choses. L'enfant gaté gagne un cadeau. Veux tu l'ouvrir ? le contenu du cadeau s'affiche sur l'écran suivant",
                0,str.GIFT));

        Rules.add(new Rule("enfant gaté 2",
                "Le hasard fait bien les choses. L'enfant gaté gagne un cadeau. Veux tu l'ouvrir ? le contenu du cadeau s'affiche sur l'écran suivant",
                0,str.GIFT));


        return Rules;
    }

    public ArrayList<Rule> getChevalRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("dopage",
                "Si le cheval le moins coté est dernier il gagne 4 points, si non il boit 2 gorgées",
                4,str.PERSO));

        Rules.add(new Rule("paris truqués",
                "Si le cheval le moins coté est premier tous les autres perdent 1 point, si non il boit 2 gorgées",
                -1,str.GROUP));


        return Rules;
    }

    public ArrayList<Rule> getVictimeRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("vengeance",
                "ceux qui ont déjà distribué des gorgées à la victime boivent 2 gorgées",
                0,str.UNKNOWN));

        Rules.add(new Rule("plus grosse victime tu meurs",
                "Si la victime a bu à la règle précèdente elle gagne 4 points, Si non elle boit le nombre de gorgées max bu à la règle precédente par un joueur",
                4,str.PERSO));


        return Rules;
    }
    public ArrayList<Rule> getGuerrierRules()
    {
        ArrayList<Rule> Rules = new ArrayList<Rule>();

        Rules.add(new Rule("un vrai guerrier",
                "Le guerrier Indien boit une gorgée du gange ou perd 2 points",
                -2, str.PERSO));

        Rules.add(new Rule("Le vrai roi du gange",
                "Le guerrier indien boit 1 gorgée de 3 alcool differents pour gagner 2 point ",
                2,str.PERSO));


        return Rules;
    }


    public ArrayList<Rule> getRoleRules(ArrayList<Player> players)
    {
     ArrayList<Rule> roleRules = new ArrayList<Rule>();
        if(Player.findPlayerByRole(players,"pirate")!=null)
            roleRules.addAll(getPirateRules());

        if(Player.findPlayerByRole(players,"enfant")!=null)
            roleRules.addAll(getEnfantRules());

        if(Player.findPlayerByRole(players,"cheval le moins coté")!=null)
            roleRules.addAll(getChevalRules());

        if(Player.findPlayerByRole(players,"victime")!=null)
            roleRules.addAll(getVictimeRules());

        if(Player.findPlayerByRole(players,"guerrier indien")!=null)
            roleRules.addAll(getGuerrierRules());

        for(Rule rule : roleRules)
        {
            rule.setDescription(rule.getDescription().replace("player",Player.getRandomPlayer(players).getName()));

        }
        return roleRules;
    }







}



