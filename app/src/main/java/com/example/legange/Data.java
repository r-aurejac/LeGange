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

        endAnnouncements= new ArrayList<>();
        setEndAnnouncement();

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
               "player est le chanceux. Il commence avec 1 points supplémentaires, LA CHANCE.", 1,ROLE));

       roles.add(new Rule("guerrier indien",
               "player est le guerrier Indien. Habitué du Gange, si il finit dernier il le partage avec l'avant dernier.",
               0,ROLE));
       roles.add(new Rule("pirate",
               "player est le pirate. ARRRRRRRRRRRh",
               0,ROLE));
       roles.add(new Rule("toxique",
               "player est le toxique, il pourra personnaliser le gange. ",
               0,ROLE));
       roles.add(new Rule("cheval le moins coté",
               "player est le cheval le plus coté, si il finit premier il distribue un cul sec. ",
               0,ROLE));
       roles.add(new Rule("la pute des questions",
               "player est la pute des questions la personne qui répond à une de ses questions boit une gorgée",
               0,ROLE));
       Collections.shuffle(roles);
   }
   public Rule getRole(Player player, int index)
   {

       Rule role = roles.get(index);
       role.getRulePlayers().add(player);
       player.setRole(role.getName());
       String string = role.getDescription().replace("player",player.getName());
       role.setDescription(string);
       return role;
   }

   private void setAnnouncement()
   {
       announcements.add(new Rule("introduction",
           "Bienvenue dans le Gange, chaque joueur va se voir attribuer un rôle puis va débuter une série de règles permettant de gagner ou de perdre des points." +
                   "à la fin de la partie le joueur avec le moins de points sera purifié par le Gange",
           0,ANNOUNCEMENT));
       announcements.add(new Rule("Cérémonie du Gange",
               "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Si un toxique a été désigné il peu verser autre chose que son verre",0
               ,ANNOUNCEMENT));
   }

    public Rule getAnnouncement(int index)
    {

        Rule announcement = announcements.get(index);
        return announcement;
    }

    private void setEndAnnouncement() {
        endAnnouncements.add(new Rule("Purification par le Gange",
                "player est l'élu,il a le score le plus faible, il doit être purifié par le Gange",0,
                END_ANNOUNCEMENT));
        endAnnouncements.add(new Rule("Enrichissement par le gange",
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
        String string = rule.getDescription().replace("player2",player2.getName());
        string = string.replace("player",player.getName());
        rule.setDescription(string);
        return rule;
    }

   private void setPersoRules()
   {
       persoRules.add(new Rule("chifumi",
           "player tente de gagné 1 points en défiant le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
           1,PERSO));
       persoRules.add(new Rule("pile ou face",
               "player mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribue si non il boit. 1 points à gagné",
               1,PERSO));
       persoRules.add(new Rule("cul-sec",
               "Si player cul-sec son verre il gagne 1 points, si c'est le précoce pas besoin il avait prit de l'avance",
               1,PERSO));
       persoRules.add(new Rule("musique",
               "player tente de gagner 1 points en donnant le titre ou l'auteur de la musique en cours",
               1,PERSO));
       persoRules.add(new Rule("shifumi",
               "player convainc le joueur à ta droite de te laisser gagner 1 point si il est généreux il pourra distibuer 4 gorgées ",
               1,PERSO));
       persoRules.add(new Rule("classement",
               "si player peut réciter le classement (avec les nombres de point de chaque joueur si ils ont été donnés recemment) sans se tromper il gagne 1 points",
               1,PERSO));
       persoRules.add(new Rule("faignant",
               "bravo player tu remporte 1 point sans rien faire, si tu es le chanceux tu peux distibuer 3 gorgées",
               1,PERSO));
       persoRules.add(new Rule("le chieur",
               "si joueur ne cul-sec son verre player gagne 1 point",
               1,PERSO));
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
       groupRules.add(new Rule("écrivain",
               " player est le juge, les autres joueurs écrivent une phrase ou un mot sous sa consigne chacun leur tour, celui qui plaira le moins au juge perd 1 points et boit 3 gorgées",
               -1,WRITE));
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
               "1 point et 3 gorgées en jeu en jeu. player choisit une catégorie, les autres joueurs proposent chacun leur tour un nombre d'éléments qu'ils peuvent citer, celui qui donne le nombre le plus élevé doit tous les citer, en cas d'echec il boit, en cas de réussite celui qui à misé le plus petit nombre boit",
               1,GROUP));
       groupRules.add(new Rule("pas de chance",
               "votez tous pour un joueur qui va perdre 1 point et boire 3 gorgées, en cas d'égalité la sanction est appliquée à tous les perdants",
               -1,GROUP));
       groupRules.add(new Rule("vrai ou faux",
               "player donne une affirmation réelle ou fictive sur lui. Les autres joueur votent chacun leur tour : vrai ou faux. 1 point en moins et 2 gorgée pour les perdants",
               -1,GROUP));

       groupRules.add(new Rule("couleur",
               "tous le sjoeuurs qui portent un vetement "+ randomColor() + "boivent 2 gorgées et perde 1 point",
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

   private String randomColor()
   {
       int rand = 0 + (int)(Math.random() * ((3 - 0) + 1));

       switch(rand) {
           case 0: return "rouge";

           case 1 : return "bleu";

           case 2 : return  "noir";

           case 3 : return "blanc";

       }
       return "blanc";
   }
}
