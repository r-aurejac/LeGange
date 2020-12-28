package com.example.legange.GameData;

import com.example.legange.Item.ActiveItem;
import com.example.legange.Item.Item;
import com.example.legange.Item.ThiefItem;

import java.util.ArrayList;

public class ItemData {

   public ArrayList<Item> baseItems;


    public ItemData()
    {
        baseItems = new ArrayList<Item>();

        baseItems.add(new Item("Trèfle à 4 feuilles", "Permet à son porteur d'avoir une seconde chance aux jeux de hasard, et d'avoir 20% de réussite en plus sur les objets"));
        baseItems.add(new Item("Casquette de victime", "les gorgées distribuées à ce joueur sont doublées"));
        baseItems.add(new Item("Couronne du chef", "le chef prend des décisions importantes et tranche en cas d'égalité"));
        baseItems.add(new ActiveItem("Cul sec d'avance", "finis ton verre et tu pourras t'épargner un cul sec ou moins à la règle de ton choix ",1,100));
        baseItems.add(new ThiefItem("Canne à pêche du voleur", "une chance sur 2 de dérober un objet aléatoire",3,50));
        baseItems.add(new Item("Certificat du pote en or", "si tu finis avant dernier tu partagera le gange avec le perdant pour le soutenir dans cette épreuve "));

    }



}
