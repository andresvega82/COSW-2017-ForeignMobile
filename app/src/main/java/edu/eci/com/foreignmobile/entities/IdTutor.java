package edu.eci.com.foreignmobile.entities;

/**
 * Created by tata on 9/05/17.
 */

public class IdTutor {

    int id;
    int lenguajeId;
    int tearchersId;

    public IdTutor(){
    }

    public IdTutor(int id, int lenguajeId, int tearchersId){
        this.id = id;
        this.lenguajeId = lenguajeId;
        this.tearchersId = tearchersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(int lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public int getTearchersId() {
        return tearchersId;
    }

    public void setTearchersId(int tearchersId) {
        this.tearchersId = tearchersId;
    }
}
