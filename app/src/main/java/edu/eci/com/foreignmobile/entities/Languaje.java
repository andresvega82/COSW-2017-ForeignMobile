package edu.eci.com.foreignmobile.entities;

/**
 * Created by nicolasguzmanp on 25/04/17.
 */

class Languaje {
    private int lenguajeId;
    private String desciption;

    public Languaje(){

    }
    public Languaje(int lenguajeId, String desciption){
        this.lenguajeId = lenguajeId;
        this.desciption = desciption;
    }

    public int getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(int lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
