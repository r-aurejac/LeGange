package com.example.legange.GameData;

import com.example.legange.Rand;
import com.example.legange.Rule.BlackJackRule;
import com.example.legange.Rule.RandomPlayesrRule;
import com.example.legange.Rule.Rule;
import com.example.legange.Rule.WritingRule;
import com.example.legange.str;
import java.util.ArrayList;
import java.util.Collections;



public class GroupRuleData {



    public ArrayList<Rule> rules,gifts;
   public GroupRuleData()
   {



        rules = new ArrayList<>();
        setRules();
       Collections.shuffle(rules);
       gifts = new ArrayList<>();
       setGifts();

   }






   private void setRules()
   {

       rules.add(new RandomPlayesrRule("COVID 19",
               "player1 a attrapé le coronavirus. Pas de chance, il perd 1 point et bois la moitié de son verre. Il choisit deux joueurs à qui il transmet le virus ainsi que la sentence",
               -1,1));


       rules.add(new RandomPlayesrRule("cul-sec",
               "Si player1 cul-sec son verre il gagne 1 points, si c'est le précoce pas besoin il avait prit de l'avance",
               1,1));



       rules.add(new RandomPlayesrRule("le chieur",
               "si aucun joueur ne cul-sec son verre player1 gagne 2 point",
               2,1));

       rules.add(new WritingRule("écrivain",
               " player1 est le juge, les autres joueurs écrivent une phrase ou un mot sous sa consigne chacun leur tour, celui qui plaira le moins au juge perd 1 points et boit 3 gorgées",
               -1,0,0));

       rules.add(new RandomPlayesrRule("categorie",
               " player1 choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,1));

       rules.add(new RandomPlayesrRule("rime",
               " player1 choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               -1,1));

       rules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 2 point. l'alcool est choisis par le chef",
               2));

       rules.add(new RandomPlayesrRule("mise",
               "2 points et 3 gorgées en jeu en jeu. player1 choisit une catégorie, les autres joueurs proposent chacun leur tour un nombre d'éléments qu'ils peuvent citer, celui qui donne le nombre le plus élevé doit tous les citer, en cas d'echec il boit, en cas de réussite il distribue",
               2,1));

       rules.add(new Rule("chasse aux sorcières",
               "votez tous pour un joueur qui va perdre 1 point et boire 3 gorgées, en cas d'égalité la sanction est appliquée à toutes les SCOREnnes  les plus désignés",
               -1));

       rules.add(new RandomPlayesrRule("vrai ou faux",
               "player1 donne une affirmation réelle ou fictive sur lui. Les autres joueur votent chacun leur tour : vrai ou faux. 1 point en moins et 2 gorgée pour les perdants",
               -1,1));

       rules.add(new Rule("couleur",
               "tous le joueurs qui portent un vetement "+ Rand.randColorString() + " boivent 2 gorgées et perde 1 point",
               -1));
       rules.add(new Rule("Poker de rognés",
               "La mise de départ est une gorgé, chacun leur tour les joueurs peuvent soit ajouter une gorgé, soit doubler soit se coucher. Pour se coucher il faut boire la moitié de la mise en cour. Le dernier joueur present gagne 3 points et boit sa mise",
               -1));

       rules.add(new BlackJackRule("BlackJack",
               "Chaque joueur va pouvoir additioner des valeurs aléatoires entre 1 et 13 . Obtenir 21 donne 4 point, 20 : 2 points, 19 : 1 point, dépasser 21 : -1 point ",
               3));

       rules.add(new RandomPlayesrRule("cadeau",
               "player1 offre un cadeau à un joueur, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0,1));

       rules.add(new RandomPlayesrRule("cadeau",
               "player1 gagne un cadeau, il peut choisir de l'ouvrir ou non (La récompense s'affiche à l'écran suivant)",
               0,1));




       //REGLE A PLUSIEURS ECRANS
       /*
       Rule rule = new Rule("test 1r",
               "test 1",
               -1);
       rule.addText("test 2");

       Rules.add(rule);
        */

   }


   public ArrayList<Rule> getRules()
   {
       return rules;
   }

   private void setGifts()
   {
       gifts.add(new Rule("4 gorgées",
               "distribue 4 gorgée et gagne 1 point",
               1));
       gifts.add(new Rule("gros lot",
               "boit un cul sec",
               0));
       gifts.add(new Rule("cul sec",
           "distribue un cul sec",
           0));
      gifts.add(new Rule("2 point",
               "gagne 2 points",
               2));
       gifts.add(new Rule("2 points en moins",
           "choisis un joueur qui perd deux point",
           -2));
       gifts.add(new Rule("1 point",
               "choisis 2 joueurs qui perdent un point, et boivent 2 gorgées",
               -1));
       gifts.add(new Rule("cadeau piègé",
               "perd 4 points",
               -4));
   }
   public Rule getGift()
   {
       int rand = 0 + (int)(Math.random() * (( gifts.size()-1 - 0) + 1));
       return gifts.get(rand);
   }




}
