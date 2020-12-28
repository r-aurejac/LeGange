package com.example.legange.GameData;

import android.util.Log;

import com.example.legange.Rule.BlackJackRule;
import com.example.legange.Rule.RandomPlayerRule;
import com.example.legange.Rule.RoleRule;
import com.example.legange.Rule.Rule;
import com.example.legange.Rule.WritingRule;
import com.example.legange.str;
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



        rules.add(new RandomPlayerRule("chifumi",
                "player1 tente de gagné 1 points en défiant le joueur de son choix au shi fu mi,il choisit combien de gorgées seront bu par le perdant",
                1,1));

        rules.add(new RandomPlayerRule("pile ou face",
                "player1 mise un nombre de gorgées et effectue un pile ou face, si il gagne il distribue si non il boit. 1 points à gagné",
                1,1));


        rules.add(new RandomPlayerRule("musique",
                "player1 tente de gagner 2 points en donnant le titre ou l'auteur de la musique en cours",
                2,1));

        rules.add(new RandomPlayerRule("charisme",
                "player1 convainc le joueur à ta droite de te laisser gagner 1 point si il est généreux il pourra distibuer 4 gorgées ",
                1,1));

        rules.add(new RandomPlayerRule("classement",
                "si player1 peut réciter le classement (avec les nombres de point de chaque joueur si ils ont été donnés recemment) sans se tromper il gagne 2 points",
                2,1));

        rules.add(new RandomPlayerRule("faignant",
                "bravo player1 tu remporte 1 point sans rien faire, si tu es le chanceux tu peux distibuer 3 gorgées",
                1,1));



        //REGLE A PLUSIEURS ECRANS
       /*
       Rule rule = new Rule("test 1r",
               "test 1",
               -1);
       rule.addText("test 2");

       Rules.add(rule);
        */
        checkRoleRules();
    }

    private void checkRoleRules()
    {
        Log.d("TAG", String.valueOf(rules.size()));
        for(Rule rule : new ArrayList<Rule>(rules)) {
            if (rule instanceof RoleRule) {
                Log.d("TAG", "c'est un rolerule");
                Log.d("TAG", rule.getName());
                if (rule.getRulePlayers().size() < 1) {
                    rules.remove(rule);
                    Log.d("TAG", "c'est null");
                }
                else Log.d("TAG", "c'est pas null");
            }
        }
        Log.d("TAG", String.valueOf(rules.size()));
    }

    public ArrayList<Rule> getRules()
    {
        return rules;
    }


}
