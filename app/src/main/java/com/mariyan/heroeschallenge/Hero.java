package com.mariyan.heroeschallenge;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hero implements Parcelable {
    private Integer id;
    private String name;
    private Integer attack;
    private Integer hitPoints;
    private Integer unspentPoints;
    private Integer status;
    public static ArrayList<Hero> list = new ArrayList<Hero>();

    public Hero(int id, String name, Integer attack, Integer hitPoints,Integer unspentPoints, Integer status){
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.hitPoints = hitPoints;
        this.unspentPoints = unspentPoints;
        this.status = status;
    }

    protected Hero(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            attack = null;
        } else {
            attack = in.readInt();
        }
        if (in.readByte() == 0) {
            hitPoints = null;
        } else {
            hitPoints = in.readInt();
        }
        if (in.readByte() == 0) {
            unspentPoints = null;
        } else {
            unspentPoints = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (attack == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(attack);
        }
        if (hitPoints == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hitPoints);
        }
        if (unspentPoints == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(unspentPoints);
        }
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
    }
}
