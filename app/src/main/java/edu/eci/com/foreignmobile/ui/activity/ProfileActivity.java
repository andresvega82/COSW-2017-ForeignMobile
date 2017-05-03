package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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

    Intent intent;
    private String userId="";
    private String emailUser="";
    private User u=new User();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        intent = getIntent();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ///

        findViewById(R.id.activity_profile).setVisibility(View.VISIBLE);
        findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
        findViewById(R.id.activity_my_tutorials).setVisibility(View.INVISIBLE);
        */
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    intent = new Intent(ProfileActivity.this,NewTutorialActivity.class);
                    intent.putExtra("userID",userId);
                    intent.putExtra("view", "1");
                    startActivity(intent);
                case R.id.navigation_historial:
                    intent = new Intent(ProfileActivity.this, HistorialActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_myTutorial:
                    intent = new Intent(ProfileActivity.this, MyTutorialsActivity.class);
                    startActivity(intent);
                    return true;

            }
            return false;
        }


    };

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
