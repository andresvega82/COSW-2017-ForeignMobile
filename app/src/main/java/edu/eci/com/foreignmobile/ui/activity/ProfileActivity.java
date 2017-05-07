package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Arrays;

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
    private TextView nombre;
    private TextView age;
    private TextView country;
    private TextView mail;
    private TextView phone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        intent = getIntent();
        userId = intent.getStringExtra("userID");
        emailUser=intent.getStringExtra("email");
        nombre= (TextView) findViewById(R.id.nombre);
        mail=(TextView) findViewById(R.id.correo);
        age= (TextView) findViewById(R.id.edad);
        country=(TextView) findViewById(R.id.vive);
        phone=(TextView) findViewById(R.id.telefono);
        this.getClient();


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
        DoPost doPost = new DoPost();
        doPost.execute(userId);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private class DoPost extends AsyncTask<String, User, User> {
        @Override
        protected User doInBackground(String... params) {
            System.out.println("Params To Get --> "+params[0]);
            //Url to Post
            String url = "https://foreignest.herokuapp.com/clients/"+params[0]+"/";
            System.out.println("This is teh URL: "+url);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try{
                HttpResponse httpResponse = httpClient.execute(httpGet);
                ObjectMapper objectMapper = new ObjectMapper();
                User[] myObject = objectMapper.readValue(httpResponse.getEntity().getContent(), User[].class);
                this.publishProgress(myObject[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(User... user) {
            nombre.setText("Nombre: "+user[0].getName()+" "+user[0].getLastName());
            mail.setText("Correo electrónico: "+user[0].getEmail());
            age.setText("Edad: "+user[0].getAge());
            country.setText("País: "+user[0].getCountry());
            phone.setText("Teléfono: "+user[0].getPhone());

        }
    }
}
