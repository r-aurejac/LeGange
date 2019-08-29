package com.example.legange;

import java.util.ArrayList;
import java.util.Collections;

public class Data {

    public ArrayList<Rule> roles, announcements,persoRules,generalRules;
   public Data()
   {
       roles = new ArrayList<>();
        setRoles();
        announcements = new ArrayList<>();
        setAnnouncement();
        persoRules = new ArrayList<>();
        setPersoRules();
       generalRules = new ArrayList<>();
       setGeneralRules();
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
       roles.add(new Rule("guerrier Indien",
               "player est le guerrier Indien. Habitué du Gange, si il finit dernieril le partage avec l'avant dernier.",
               0));
       roles.add(new Rule("pirate",
               "player est le pirate. ARRRRRRRRRRRh",
               0));
       roles.add(new Rule("toxique",
               "player est le toxique, tu pourra personnaliser le gange à ta sauce. ",
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
           "Bienvenu dans le Gange, chaque joueur va se voir attribuer un rôle puis va débuter une série de règles permettant de gagner ou de perdre des points." +
                   "à la fin de la partie le juoeur avec le moins de points sera purifié par le Gange",
           0));
       announcements.add(new Rule("gange",
               "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Si un toxique a été désigné il peu verser autre chose que son verre" +
                       "",
               0));
       announcements.add(new Rule("fin",
               "player à perdu ce looseur sera purifié par le gange",
               0));
       announcements.add(new Rule("fin",
               "player à perdu ce looseur sera purifié par le gange mais étant un guerrier indien deter il patagera son gange avec ",
               0));
   }

    public Rule getAnnouncement(int index)
    {
        Rule announcement = announcements.get(index);
        return announcement;
    }

   private void setPersoRules()
   {

   }

   private void setGeneralRules()
   {
       generalRules.add(new Rule("categorie",
               " Choisit une catégorie et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               0));
       generalRules.add(new Rule("rime",
               " Choisit une rime et commence. Celui qui répète ou n'a plus d'idées perd 1 point",
               0));
       generalRules.add(new Rule("shot",
               " tournée de shot, chaque joueur qui prend un shot de pur gagne 1 point. l'alcool est choisis par",
               0));

   }
}
