package com.example.legange.GameData;

import com.example.legange.Rule.Rule;

import java.util.ArrayList;
import java.util.Collections;



public class IndividualRuleData {



    public ArrayList<Rule> rules;
    public IndividualRuleData()
    {



        rules = new ArrayList<>();
        setRules();
        Collections.shuffle(rules);



    }


    private void setRules()
    {



        rules.add(new Rule("chifumi",
                "tente de gagner 1 points en défiant le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
                1));

        rules.add(new Rule("pile ou face",
                "mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribue si non il boit. 1 points à gagné",
                1));


        rules.add(new Rule("musique",
                "tente de gagner 2 points en donnant le titre ou l'auteur de la musique en cours",
                2));

        rules.add(new Rule("charisme",
                "si tu convaincs le joueur à ta droite de te laisser gagner 1 point si il est généreux il pourra distibuer 4 gorgées ",
                1));

        rules.add(new Rule("classement",
                "si tu peux réciter le classement (avec les nombres de point de chaque joueur si ils ont été donnés recemment) sans se tromper il gagne 2 points",
                2));

        rules.add(new Rule("faignant",
                "tu remporte 1 point sans rien faire",
                1));



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


}
