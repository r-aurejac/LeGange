package com.example.legange.GameData;

import com.example.legange.Class.Player;
import com.example.legange.Class.Rule;
import com.example.legange.str;
import java.util.ArrayList;


public class AnnouncementData {


    public ArrayList<Rule>  announcements,endAnnouncements;
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
                "Bienvenu au Gange, le Fleuve le plus Sacré du Monde, avant de débuter cette folle aventure chaque joueur va se voir attribuer un role, une série de règle va suivre permettant de gagner ou perdre des points." +
                        " Le joueur avec le moins de points aura le privilège de boire la Sainte eau du Gange ",
                0, str.ANNOUNCEMENT));
        announcements.add(new Rule("Cérémonie du Gange",
                "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. L'Hindou du groupe peut ajouter ce qu'il désire",0
                , str.ANNOUNCEMENT));
    }

    public Rule getAnnouncement(int index)
    {

        Rule announcement = announcements.get(index);
        return announcement;
    }

    private void setEndAnnouncement() {
        endAnnouncements.add(new Rule("Purification par le Gange",
                "player a été désigné par les dieux Hindous, il remporte la Sainte Eau du Gange",0,
                str.END_ANNOUNCEMENT));
        endAnnouncements.add(new Rule("Enrichissement par le gange",
                "player est l'élu, il a le score le plus faible, mais étant très généreux il peut partager son brevage avec player2",
                0, str.END_ANNOUNCEMENT));
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
