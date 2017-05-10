package edu.eci.com.foreignmobile.entities;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by tata on 10/05/17.
 */

public class TutoriaItem {
    String name_profesor = "";
    Date date = null;
    Integer duration;
    String language;
    String state;
    private Drawable imagen;
    Integer cost;

    public TutoriaItem(){    }

    public TutoriaItem(String state, String name_profesor, Date date, Integer duration, String language, Integer cost, Drawable imagen){
        this.state = state;
        this.name_profesor = name_profesor;
        this.date = date;
        this.duration = duration;
        this.language = language;
        this.cost = cost;
        this.imagen = imagen;
    }


    public String getName_profesor() {
        return name_profesor;
    }

    public void setName_profesor(String name_profesor) {
        this.name_profesor = name_profesor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
