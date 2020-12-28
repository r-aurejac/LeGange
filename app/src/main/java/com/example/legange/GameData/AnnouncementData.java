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
                "Bienvenue au Gange, les joeurs ici present vont devoir prouver leur valeur en gagnant des points pendant une série de règle. Le perdant devra boire l'eau du fleuve le plus sacré du Monde ppur purifier son âme",
                0));
        announcements.add(new Rule("Cérémonie du Gange",
                "Chaque joueur verse la quantité qu'il désire de son verre dans le Gange. Le chef du groupe peut ajouter ce qu'il désire",0
                ));
    }

    public ArrayList<Rule> getAnnouncement()
    {
        return announcements;
    }

    private void setEndAnnouncement() {
        endAnnouncements.add(new Rule("Purification par le Gange",
                "player a déçu les dieux Hindoux, son âme doit être purifié",0));
        endAnnouncements.add(new Rule("Enrichissement par le gange",
                "player a déçu les dieux Hindoux, son âme doit être purifié mais player2",
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
