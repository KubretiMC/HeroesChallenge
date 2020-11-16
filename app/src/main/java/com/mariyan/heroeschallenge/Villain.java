package com.mariyan.heroeschallenge;


import java.util.ArrayList;

public class Villain {
    private Integer id;
    private String name;
    private Integer attack;
    private Integer hitPoints;

    public static ArrayList<Villain> list = new ArrayList<>();

    public Villain(int id, String name, Integer attack, Integer hitPoints) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.hitPoints = hitPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }
}
