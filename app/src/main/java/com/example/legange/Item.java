package com.example.legange;

import java.io.Serializable;

public class Item implements Serializable {

    int durability = 1;
    private String name;
    private String description;
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Item(String name, String description, int durability) {
        this.name = name;
        this.description = description;
        this.durability = durability;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
