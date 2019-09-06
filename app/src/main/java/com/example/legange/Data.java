package com.example.legange;

import java.util.ArrayList;
import java.util.Collections;



public class Data {
    private static final int ANNOUNCEMENT = 0;
    private static final int ROLE = 1;
    private static final int GROUP = 2;
    private static final int PERSO = 3;
    private static final int WRITE = 4;
    private static final int END_ANNOUNCEMENT = 5;

    public ArrayList<Rule> roles, announcements,endAnnouncements,persoRules, groupRules;
   public Data()
   {
       roles = new ArrayList<>();
        setRoles();
        Collections.shuffle(roles);
        announcements = new ArrayList<>();
        setAnnouncement();
        Collections.shuffle(announcements);
        endAnnouncements= new ArrayList<>();
        setEndAnnouncement();
        Collections.shuffle(endAnnouncements);
        persoRules = new ArrayList<>();
        setPersoRules();
       Collections.shuffle(persoRules);
       groupRules = new ArrayList<>();
       setGroupRules();
       Collections.shuffle(groupRules);
   }

   private void setRoles()
   {
       roles.add(new Rule("victime",
               "player est la victime. Les gorgées qui lui sont distribuées sont doublées.",
               0,ROLE));
       roles.add(new Rule("précoce",
               "player est le précoce. Toujours en avance,il Cul-sec un verre plein avant de commencer le jeu.",
               0,ROLE));
       roles.add(new Rule("chanceux",
               "player est le chanceux. Il commence avec 2 points supplémentaires, LA CHANCE.", 2,ROLE));

       roles.add(new Rule("guerrier indien",
               "player est le guerrier Indien. Habitué du Gange, si il finit dernier il le partage avec l'avant dernier.",
               0,ROLE));
       roles.add(new Rule("pirate",
               "player est le pirate. ARRRRRRRRRRRh",
               0,ROLE));
       roles.add(new Rule("toxique",
               "player est le toxique, il pourra personnaliser le gange. ",
               0,ROLE));
       Collections.shuffle(roles);
   }
   public Rule getRole(Player player, int index)
   {

       Rule role = roles.get(index);
       role.getRulePlayers().add(player);
       player.setRole(role.getDescription());
       String string = role.getDescription().replace("player",player.getName());
       role.setDescription(string);
       return role;
   }

   private void setAnnouncement()
   {
       announcements.add(new Rule("intro",
           "Bienvenue dans le Gange, chaque joueur va se voir attribuer un rôle puis va débuter une série de règles permettant de gagner ou de perdre des points." +
                   "à la fin de la partie le joueur avec le moins de points sera purifié par le Gange",
           0,ANNOUNCEMENT));
       announcements.add(new Rule("gange",
               "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Si un toxique a été désigné il peu verser autre chose que son verre",0
               ,ANNOUNCEMENT));
   }

    public Rule getAnnouncement(int index)
    {

        Rule announcement = announcements.get(index);
        return announcement;
    }

    private void setEndAnnouncement() {
        endAnnouncements.add(new Rule("fin",
                "player est l'élu,il a le score le plus faible, il doit être purifié par le Gange",0,
                END_ANNOUNCEMENT));
        endAnnouncements.add(new Rule("fin2",
                "player est l'élu, il a le score le plus faible, mais étant le guerrier Indien il peut enrichir le joueur player2 de sa culture en partageant sa purification avec lui",
                0,END_ANNOUNCEMENT));
    }


    public Rule getEndAnnouncement(int index, Player player)
    {
        Rule rule = endAnnouncements.get(index);
        rule.getRulePlayers().add(player);
        String string = rule.getDescription().replace("player",player.getName());
        rule.setDescription(string);
        return rule;
    }
    public Rule getEndAnnouncement(int index, Player player,Player player2)
    {
        Rule rule = endAnnouncements.get(index);
        rule.getRulePlayers().add(player);
        rule.getRulePlayers().add(player2);
        String string = rule.getDescription().replace("player",player.getName());
        string = string.replace("player2",player2.getName());
        rule.setDescription(string);
        return rule;
    }

   private void setPersoRules()
   {
       persoRules.add(new Rule("shifumi",
           "player défit le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
           2,PERSO));
       persoRules.add(new Rule("pile ou face",
               "player mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribu si non il boit",
               2,PERSO));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2,PERSO));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2,PERSO));
       persoRules.add(new Rule("shifumi",
               "player défit le joueur de son choix au shi fu mi",
               2,PERSO));
   }
    public Rule getPersoRule(Player player,int index)
    {

        Rule rule = persoRules.get(index);
        rule.getRulePlayers().add(player);
        String string = rule.getDescription().replace("player",player.getName());
        rule.setDescription(string);
        return rule;
    }

   private void setGroupRules()
   {
       groupRules.add(new Rule("categorie",
               " player choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,GROUP));
       groupRules.add(new Rule("rime",
               " player choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,GROUP));
       groupRules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 1 point. l'alcool est choisis par",
               1,GROUP));
       groupRules.add(new Rule("mise",
               "player choisit une catégorie, chacun donne le nombre d'élément de la catégorie qu'il peut citer, celui qui propose le nombre le plus élevé doit les citer ",
               1,GROUP));
       groupRules.add(new Rule("scribe",
               " player est le juge, les autres joueurs écrivent une phrase chacun leur tour, celui qui écrit la phrase qui plaira le moins au juge perd",
               -1,GROUP));

   }
   public Rule getGroupRule(Player player,int index)
   {
       Rule rule = groupRules.get(index);
       rule.getRulePlayers().add(player);
       String string = rule.getDescription().replace("player",player.getName());
       rule.setDescription(string);
       return rule;
   }
}
