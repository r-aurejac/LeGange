package com.example.legange.GameData;

import com.example.legange.RuleClasses.BlackJackRule;
import com.example.legange.RuleClasses.RandomPlayerRule;
import com.example.legange.RuleClasses.Rule;
import com.example.legange.RuleClasses.WritingRule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;



public class RuleData {



    public ArrayList<Rule> Rules,gifts;
   public RuleData()
   {



        Rules = new ArrayList<>();
        setRules();
       Collections.shuffle(Rules);
       gifts = new ArrayList<>();
       setGifts();

   }






   private void setRules()
   {

       Rules.add(new RandomPlayerRule("chifumi",
               "player1 tente de gagné 1 points en défiant le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
               1, str.PERSO,1));

       Rules.add(new RandomPlayerRule("pile ou face",
               "player1 mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribue si non il boit. 1 points à gagné",
               1, str.PERSO,1));

       Rules.add(new RandomPlayerRule("cul-sec",
               "Si player1 cul-sec son verre il gagne 1 points, si c'est le précoce pas besoin il avait prit de l'avance",
               1, str.PERSO,1));

       Rules.add(new RandomPlayerRule("musique",
               "player1 tente de gagner 2 points en donnant le titre ou l'auteur de la musique en cours",
               2, str.PERSO,1));

       Rules.add(new RandomPlayerRule("charisme",
               "player1 convainc le joueur à ta droite de te laisser gagner 1 point si il est généreux il pourra distibuer 4 gorgées ",
               1, str.PERSO,1));

       Rules.add(new RandomPlayerRule("classement",
               "si player1 peut réciter le classement (avec les nombres de point de chaque joueur si ils ont été donnés recemment) sans se tromper il gagne 2 points",
               2, str.PERSO,1));

       Rules.add(new RandomPlayerRule("faignant",
               "bravo player1 tu remporte 1 point sans rien faire, si tu es le chanceux tu peux distibuer 3 gorgées",
               1, str.PERSO,1));

       Rules.add(new RandomPlayerRule("le chieur",
               "si aucun joueur ne cul-sec son verre player1 gagne 2 point",
               2, str.PERSO,1));

       Rules.add(new WritingRule("écrivain",
               " player1 est le juge, les autres joueurs écrivent une phrase ou un mot sous sa consigne chacun leur tour, celui qui plaira le moins au juge perd 1 points et boit 3 gorgées",
               -1, str.GROUP,1,0));

       Rules.add(new RandomPlayerRule("categorie",
               " player1 choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1, str.GROUP,1));

       Rules.add(new RandomPlayerRule("rime",
               " player1 choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1, str.GROUP,1));

       Rules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 2 point. l'alcool est choisis par le chef",
               2, str.GROUP));

       Rules.add(new RandomPlayerRule("mise",
               "2 points et 3 gorgées en jeu en jeu. player1 choisit une catégorie, les autres joueurs proposent chacun leur tour un nombre d'éléments qu'ils peuvent citer, celui qui donne le nombre le plus élevé doit tous les citer, en cas d'echec il boit, en cas de réussite il distribue",
               2, str.GROUP,1));

       Rules.add(new Rule("chasse aux sorcières",
               "votez tous pour un joueur qui va perdre 1 point et boire 3 gorgées, en cas d'égalité la sanction est appliquée à toutes les personnes  les plus désignés",
               -1, str.GROUP));

       Rules.add(new RandomPlayerRule("vrai ou faux",
               "player1 donne une affirmation réelle ou fictive sur lui. Les autres joueur votent chacun leur tour : vrai ou faux. 1 point en moins et 2 gorgée pour les perdants",
               -1, str.GROUP,1));

       Rules.add(new Rule("couleur",
               "tous le joueurs qui portent un vetement "+ randomColor() + " boivent 2 gorgées et perde 1 point",
               -1, str.GROUP));

       Rules.add(new BlackJackRule("BlackJack",
               "Chaque joueur va pouvoir additioner des valeurs aléatoires entre 1 et 13 . Obtenir 21 donne 4 point, 20 : 2 points, 19 : 1 point, dépasser 21 : -1 point ",
               3, str.UNKNOWN));

       Rules.add(new RandomPlayerRule("cadeau",
               "player1 offre un cadeau à un joueur, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0, str.GIFT,1));

       Rules.add(new RandomPlayerRule("cadeau",
               "player1 gagne un cadeau, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0, str.GIFT,1));

       //REGLE A PLUSIEURS ECRANS
       /*
       Rule rule = new Rule("test 1r",
               "test 1",
               -1, str.GROUP);
       rule.addText("test 2");

       Rules.add(rule);
        */

   }
   public ArrayList<Rule> getRules()
   {
       return Rules;
   }

   private void setGifts()
   {
       gifts.add(new Rule("4 gorgées",
               "distribue 4 gorgée et gagne 1 point",
               1, str.GROUP));
       gifts.add(new Rule("gros lot",
               "boit un cul sec",
               0, str.GROUP));
       gifts.add(new Rule("cul sec",
           "distribue un cul sec",
           0, str.GROUP));
      gifts.add(new Rule("2 point",
               "gagne 2 points",
               2, str.GROUP));
       gifts.add(new Rule("2 points en moins",
           "choisis un joueur qui perd deux point",
           -2, str.GROUP));
       gifts.add(new Rule("1 point",
               "choisis 2 joueurs qui perdent un point, et boivent 2 gorgées",
               -1, str.GROUP));
       gifts.add(new Rule("cadeau piègé",
               "perd 4 points",
               -4, str.GROUP));
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
