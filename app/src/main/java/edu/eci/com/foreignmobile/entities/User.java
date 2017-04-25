package edu.eci.com.foreignmobile.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicolasguzmanp on 25/04/17.
 */

public class User implements Serializable {
    private String user_id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String country;
    private Blob photo;
    private Integer age;
    private int CreditCard_payment_id;
    private Set<Languaje> languajes = new HashSet<>(0);

    public User(){

    }

    public User(String id, String name, String lastName, String email, String phone, String country, Integer Age,int creditCard) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.user_id = id;
        this.age = Age;
        this.CreditCard_payment_id = creditCard;
    }

    public User(String id, String name, String lastName, String email, String phone, String country, Integer Age, Set<Languaje> languajes) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.user_id = id;
        this.age = Age;
        this.languajes = languajes;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getCreditCard_payment_id() {
        return CreditCard_payment_id;
    }

    public void setCreditCard_payment_id(int creditCard_payment_id) {
        CreditCard_payment_id = creditCard_payment_id;
    }

    public Set<Languaje> getLanguajes() {
        return languajes;
    }

    public void setLanguajes(Set<Languaje> languajes) {
        this.languajes = languajes;
    }
}
