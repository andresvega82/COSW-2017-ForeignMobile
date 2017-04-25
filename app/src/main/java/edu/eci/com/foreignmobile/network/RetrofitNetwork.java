package edu.eci.com.foreignmobile.network;

import java.io.IOException;
import java.util.List;

import edu.eci.com.foreignmobile.entities.User;
import edu.eci.com.foreignmobile.services.UsersService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicolasguzmanp on 25/04/17.
 */

public class RetrofitNetwork {
    private final UsersService service;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("url raiz proyecto").addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(UsersService.class);
    }

    public List<User> getUser() throws IOException {
        Call<List<User>> call=service.usuario();
        Response<List<User>> response = call.execute();
        return response.body();
    }
}
