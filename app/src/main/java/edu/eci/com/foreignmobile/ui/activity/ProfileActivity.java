package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.User;


/**
 * Created by nicolasguzmanp on 26/04/17.
 */

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String userId="";
    private String emailUser="";
    private User u=new User();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        userId = intent.getStringExtra("UserId");
        emailUser=intent.getStringExtra("email");
        TextView nombre= (TextView) findViewById(R.id.nombre);
        TextView mail=(TextView) findViewById(R.id.correo);
        TextView age= (TextView) findViewById(R.id.edad);
        TextView country=(TextView) findViewById(R.id.vive);
        TextView phone=(TextView) findViewById(R.id.telefono);
        this.getClient();
        System.out.print(emailUser);
        nombre.setText("Nombre: "+u.getName()+" "+u.getLastName());
        mail.setText("Correo electrónico: "+u.getEmail());
        age.setText("Edad: "+u.getAge());
        country.setText("País: "+u.getCountry());
        phone.setText("Teléfono: "+u.getPhone());
/*
        ///
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Perfil");



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ///*/
    }

    public void getClient(){

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://foreignest.herokuapp.com/clients/"+emailUser+"/";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                u=new Gson().fromJson(response, User.class);
                System.out.print("Get");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
