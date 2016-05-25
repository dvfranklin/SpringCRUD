package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Footballer {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String position;
    private String club;
    private int goals;

    @ManyToOne
    User user;

    public Footballer() {
    }

    public Footballer(String name, String position, String club, int goals) {
        this.name = name;
        this.position = position;
        this.club = club;
        this.goals = goals;
    }

    public Footballer(String name, String position, String club, int goals, User user) {
        this.name = name;
        this.position = position;
        this.club = club;
        this.goals = goals;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public User getOwner() {
        return user;
    }

    public void setOwner(User owner) {
        this.user = owner;
    }
}
