package edu.eci.com.foreignmobile.entities;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by tatiana higuera
 * on 25/04/17.
 * tutor
 */

public class Tutor {

    private String title;
    private String categoryId;
    private String description;
    private Drawable imagen;

    public Tutor() {
        super();
    }

    public Tutor(String categoryId, String title, String description, Drawable imagen) {
        super();
        this.title = title;
        this.description = description;
        this.imagen = imagen;
        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId = categoryId;}


    public String getParcelable() {
        return ""+categoryId+" "+title+" "+description;
    }
}
