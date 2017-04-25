package edu.eci.com.foreignmobile.services;

import java.util.List;

import edu.eci.com.foreignmobile.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nicolasguzmanp on 25/04/17.
 */

/*
    @return Datos del usuario para mostrarlos en el perfil
 */
public interface UsersService {
        @GET("/clients/")
        Call<List<User>> usuario();
}


