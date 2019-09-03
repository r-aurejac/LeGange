package com.example.legange;

import java.util.ArrayList;
import java.util.Collections;

public class Data {

    public ArrayList<Rule> roles, announcements,persoRules, groupRules;
   public Data()
   {
       roles = new ArrayList<>();
        setRoles();
        announcements = new ArrayList<>();
        setAnnouncement();
        persoRules = new ArrayList<>();
        setPersoRules();
       groupRules = new ArrayList<>();
       setGroupRules();
   }

   private void setRoles()
   {
       roles.add(new Rule("victime",
               "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
               0));
       roles.add(new Rule("précoce",
               "player est le précoce. Toujours en avance,il Cul-sec un verre plein avant de commencer le jeu.",
               0));
       roles.add(new Rule("chanceux",
               "player est le chanceux. Il commence avec 2 points supplémentaires, LA CHANCE.", 2));

       roles.add(new Rule("guerrier indien",
               "player est le guerrier Indien. Habitué du Gange, si il finit dernier il le partage avec l'avant dernier.",
               0));
       roles.add(new Rule("pirate",
               "player est le pirate. ARRRRRRRRRRRh",
               0));
       roles.add(new Rule("toxique",
               "player est le toxique, il pourra personnaliser le gange. ",
               0));
       Collections.shuffle(roles);
   }
   public Rule getRole(String player, int index)
   {

       Rule role = roles.get(index);
       String string = role.getDescription().replace("player",player);
       role.setDescription(string);
       return role;
   }

   private void setAnnouncement()
   {
       announcements.add(new Rule("intro",
           "Bienvenue dans le Gange, chaque joueur va se voir attribuer un rôle puis va débuter une série de règles permettant de gagner ou de perdre des points." +
                   "à la fin de la partie le joueur avec le moins de points sera purifié par le Gange",
           0));
       announcements.add(new Rule("gange",
               "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Si un toxique a été désigné il peu verser autre chose que son verre" +
                       "",
               0));
       announcements.add(new Rule("fin",
               "player à perdu ce looseur sera purifié par le gange",
               0));
       announcements.add(new Rule("fin2",
               "player à perdu mais étant le guerrier Indien il peut enrichir le joueur player2 de sa culture en partageant son verre de Gange",
               0));
   }

    public Rule getAnnouncement(int index)
    {

        Rule announcement = announcements.get(index);
        return announcement;
    }
    public Rule getAnnouncement(int index, String player)
    {
        Rule role = roles.get(index);
        String string = role.getDescription().replace("player",player);
        Rule announcement = announcements.get(index);
        return announcement;
    }

   private void setPersoRules()
   {
       persoRules.add(new Rule("shifumi",
           "player défit le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
           2));
       persoRules.add(new Rule("pile ou face",
               "player mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribu si non il boit",
               2));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2));
   }
    public Rule getPersoRule(int index,String player)
    {
        Rule rule = persoRules.get(index);
        String string = rule.getDescription().replace("player",player);
        rule.setDescription(string);
        return rule;
    }

   private void setGroupRules()
   {
       groupRules.add(new Rule("categorie",
               " Choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               1));
       groupRules.add(new Rule("rime",
               " Choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               1));
       groupRules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 1 point. l'alcool est choisis par",
               1));
       groupRules.add(new Rule("mise",
               "player choisit une catégorie, chacun donne le nombre d'élément de la catégorie qu'il peut citer, celui qui propose le nombre le plus élevé doit les citer ",
               1));
       groupRules.add(new Rule("scribe",
               " player est le juge, les autres joueurs écrivent une phrase chacun leur tour, celui qui écrit la phrase qui plaira le moins au juge perd",
               1));

   }
   public Rule getGroupRule(int index,String player)
   {
       Rule groupRule = groupRules.get(index);
       String string = groupRule.getDescription().replace("player",player);
       groupRule.setDescription(string);
       return groupRule;
   }
}
