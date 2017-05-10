package edu.eci.com.foreignmobile.entities;

/**
 * Created by tata on 9/05/17.
 */

public class IdTutor {

    Integer id;
    Integer lenguajeId;
    Integer tearchersId;

    public IdTutor(){
    }

    public IdTutor(int id, int lenguajeId, int tearchersId){
        this.id = id;
        this.lenguajeId = lenguajeId;
        this.tearchersId = tearchersId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(int lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public Integer getTearchersId() {
        return tearchersId;
    }

    public void setTearchersId(int tearchersId) {
        this.tearchersId = tearchersId;
    }
}
