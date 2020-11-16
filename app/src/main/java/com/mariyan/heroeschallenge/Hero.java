package com.mariyan.heroeschallenge;

import java.util.ArrayList;

public class Hero extends Villain{
    private Integer unspentPoints;
    private Integer status;

    public static ArrayList<Hero> list = new ArrayList<>();

    public Hero(int id, String name, Integer attack, Integer hitPoints,Integer unspentPoints, Integer status){
        super(id,name,attack,hitPoints);
        this.unspentPoints = unspentPoints;
        this.status = status;
    }

    public Integer getUnspentPoints() {
        return unspentPoints;
    }

    public void setUnspentPoints(Integer unspentPoints) {
        this.unspentPoints = unspentPoints;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
