package com.example.legange.GameData;

import com.example.legange.Player.Player;
import com.example.legange.Rule.Rule;
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
                "Bienvenue au tournois du Gange, tous les joueurs ici présents vont s'affronter pour tenter de gagner le droit de boire l'eau du fleuve le plus sacré du monde",
                0));
        announcements.add(new Rule("Cérémonie du Gange",
                "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. L'Hindou du groupe peut ajouter ce qu'il désire",0
                ));
    }

    public ArrayList<Rule> getAnnouncement()
    {
        return announcements;
    }

    private void setEndAnnouncement() {
        endAnnouncements.add(new Rule("Purification par le Gange",
                "player a été désigné par les dieux Hindous, il remporte la Sainte Eau du Gange",0));
        endAnnouncements.add(new Rule("Enrichissement par le gange",
                "player est l'élu, il a le score le plus faible, mais étant très généreux il peut partager son brevage avec player2",
                0));
    }


    public Rule getEndAnnouncement(int index, Player player)
    {
        Rule rule = endAnnouncements.get(index);
        rule.getRulePlayers().add(player);
        String string = rule.texts.get(rule.getIndice()).replace("player",player.getName());
        rule.texts.set(rule.getIndice(),string);
        return rule;
    }
    public Rule getEndAnnouncement(int index, Player player,Player player2)
    {
        Rule rule = endAnnouncements.get(index);
        rule.getRulePlayers().add(player);
        rule.getRulePlayers().add(player2);
        String string = rule.texts.get(rule.getIndice()).replace("player2",player2.getName());
        string = string.replace("player",player.getName());
        rule.texts.set(rule.getIndice(),string);
        return rule;
    }


}
