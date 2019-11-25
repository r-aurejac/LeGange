package com.example.legange.GameData;

import com.example.legange.Class.Player;
import com.example.legange.Class.Rule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;



public class RuleData {



    public ArrayList<Rule> persoRules, groupRules,gifts;
   public RuleData()
   {



        persoRules = new ArrayList<>();
        setPersoRules();
       Collections.shuffle(persoRules);
       groupRules = new ArrayList<>();
       setGroupRules();
       Collections.shuffle(groupRules);
       gifts = new ArrayList<>();
       setGifts();

   }


   private void setPersoRules()
   {
       persoRules.add(new Rule("chifumi",
           "player tente de gagné 1 points en défiant le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
           1,str.PERSO));
       persoRules.add(new Rule("pile ou face",
               "player mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribue si non il boit. 1 points à gagné",
               1,str.PERSO));
       persoRules.add(new Rule("cul-sec",
               "Si player cul-sec son verre il gagne 1 points, si c'est le précoce pas besoin il avait prit de l'avance",
               1,str.PERSO));
       persoRules.add(new Rule("musique",
               "player tente de gagner 2 points en donnant le titre ou l'auteur de la musique en cours",
               2,str.PERSO));
       persoRules.add(new Rule("charisme",
               "player convainc le joueur à ta droite de te laisser gagner 1 point si il est généreux il pourra distibuer 4 gorgées ",
               1,str.PERSO));
       persoRules.add(new Rule("classement",
               "si player peut réciter le classement (avec les nombres de point de chaque joueur si ils ont été donnés recemment) sans se tromper il gagne 2 points",
               2,str.PERSO));
       persoRules.add(new Rule("faignant",
               "bravo player tu remporte 1 point sans rien faire, si tu es le chanceux tu peux distibuer 3 gorgées",
               1,str.PERSO));
       persoRules.add(new Rule("le chieur",
               "si aucun joueur ne cul-sec son verre player gagne 2 point",
               2,str.PERSO));
   }
    public Rule getPersoRule(Player player, int index)
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
               -1,str.WRITE));
       groupRules.add(new Rule("categorie",
               " player choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,str.GROUP));
       groupRules.add(new Rule("rime",
               " player choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,str.GROUP));
       groupRules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 2 point. l'alcool est choisis par le toxique",
               2,str.GROUP));
       groupRules.add(new Rule("mise",
               "2 points et 3 gorgées en jeu en jeu. player choisit une catégorie, les autres joueurs proposent chacun leur tour un nombre d'éléments qu'ils peuvent citer, celui qui donne le nombre le plus élevé doit tous les citer, en cas d'echec il boit, en cas de réussite il distribue",
               2,str.GROUP));
       groupRules.add(new Rule("chasse aux sorcières",
               "votez tous pour un joueur qui va perdre 1 point et boire 3 gorgées, en cas d'égalité la sanction est appliquée à toutes les personnes  les plus désignés",
               -1,str.GROUP));
       groupRules.add(new Rule("vrai ou faux",
               "player donne une affirmation réelle ou fictive sur lui. Les autres joueur votent chacun leur tour : vrai ou faux. 1 point en moins et 2 gorgée pour les perdants",
               -1,str.GROUP));
       groupRules.add(new Rule("couleur",
               "tous le juoeurs qui portent un vetement "+ randomColor() + " boivent 2 gorgées et perde 1 point",
               -1,str.GROUP));
       groupRules.add(new Rule("BlackJack",
               "Chaque joueur va pouvoir additioner des valeurs aléatoires entre 1 et 13 . Obtenir 21 donne 4 point, 20 : 2 points, 19 : 1 point, dépasser 21 : -1 point ",
               3,str.BLACKJACK));
       groupRules.add(new Rule("cadeau",
               "player offre un cadeau à un joueur, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0,str.GIFT));
       groupRules.add(new Rule("cadeau",
               "player gagne un cadeau, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0,str.GIFT));

   }
   public Rule getGroupRule(Player player,int index)
   {
       Rule rule = groupRules.get(index);
       rule.getRulePlayers().add(player);
       String string = rule.getDescription().replace("player",player.getName());
       rule.setDescription(string);
       return rule;
   }

   private void setGifts()
   {
       gifts.add(new Rule("4 gorgées",
               "distribue 4 gorgée et gagne 1 point",
               1,str.GROUP));
       gifts.add(new Rule("gros lot",
               "boit un cul sec",
               0,str.GROUP));
       gifts.add(new Rule("cul sec",
           "distribue un cul sec",
           0,str.GROUP));
      gifts.add(new Rule("2 point",
               "gagne 2 points",
               2,str.GROUP));
       gifts.add(new Rule("2 points en moins",
           "choisis un joueur qui perd deux point",
           -2,str.GROUP));
       gifts.add(new Rule("1 point",
               "choisis 2 joueurs qui perdent un point, et boivent 2 gorgées",
               -1,str.GROUP));
       gifts.add(new Rule("cadeau piègé",
               "perd 4 points",
               -4,str.GROUP));
   }
   public Rule getGift()
   {
       int rand = 0 + (int)(Math.random() * (( gifts.size()-1 - 0) + 1));
       return gifts.get(rand);
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
