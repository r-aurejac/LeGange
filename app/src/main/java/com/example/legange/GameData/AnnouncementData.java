package com.example.legange.GameData;

import com.example.legange.Class.Player;
import com.example.legange.Class.Rule;

import java.util.ArrayList;


public class AnnouncementData {
    private static final int ANNOUNCEMENT = 0;
    private static final int ROLE = 1;
    private static final int GROUP = 2;
    private static final int PERSO = 3;
    private static final int WRITE = 4;
    private static final int END_ANNOUNCEMENT = 5;
    private  static  final int GIFT = 6;
    private  static  final int BLACKJACK= 7;

    public ArrayList<Rule> roles, announcements,endAnnouncements,persoRules, groupRules,gifts;
    public AnnouncementData()
    {
        announcements = new ArrayList<>();
        setAnnouncement();

        endAnnouncements= new ArrayList<>();
        setEndAnnouncement();

    }





    private void setAnnouncement()
    {
        announcements.add(new Rule("introduction",
                "Bienvenue dans le Gange, chaque joueur va se voir attribuer un rôle puis va débuter une série de règles permettant de gagner ou de perdre des points." +
                        "à la fin de la partie le joueur avec le moins de points sera purifié par le Gange",
                0,ANNOUNCEMENT));
        announcements.add(new Rule("Cérémonie du Gange",
                "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Si un ou des toxiques ont été désignés il peuvent verser autre chose que leur verre",0
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


}
