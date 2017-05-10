package edu.eci.com.foreignmobile.entities;

import java.util.Date;

/**
 * Created by tata on 8/05/17.
 */

public class Tutoria {

    Date date;
    String state;
    int duration;
    IdTutor idTutor;
    int payment;
    int cost;
    public Tutoria() {
        super();
    }

    public Tutoria(Date date, String state, int duration, IdTutor idTutor, int payment, int cost){
        this.date = date;
        this.state = state;
        this.duration = duration;
        this.idTutor = idTutor;
        this.payment = payment;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public IdTutor getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(IdTutor idTutor) {
        this.idTutor = idTutor;
    }
}
