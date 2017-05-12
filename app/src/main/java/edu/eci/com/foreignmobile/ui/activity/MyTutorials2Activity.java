package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.AdapterItemMyTutorials;
import edu.eci.com.foreignmobile.entities.IdTutor;
import edu.eci.com.foreignmobile.entities.Tutoria;
import edu.eci.com.foreignmobile.entities.TutoriaItem;
import edu.eci.com.foreignmobile.entities.User;

import static edu.eci.com.foreignmobile.R.id.language;

public class MyTutorials2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String userId="";
    String view = "";
    Intent intent;

    ArrayList<TutoriaItem> tutorArrayList = new ArrayList<TutoriaItem>();

    String languaje = "";
    String date = null;
    Integer duration;
    Integer cost;
    String nameTeacher = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tutorial);
        intent = getIntent();
        userId = intent.getStringExtra("userId");
        view = intent.getStringExtra("view");

        languaje = intent.getStringExtra("language");
        date = intent.getStringExtra("date");
        duration = Integer.parseInt(intent.getStringExtra("duration"));
        cost = Integer.parseInt(intent.getStringExtra("cost"));
        nameTeacher = intent.getStringExtra("name_teacher");

        viewDetailsTutorial();


        ///
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mis Tutorias");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ///

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ///


        findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
        findViewById(R.id.activity_my_tutorials2).setVisibility(View.VISIBLE);

    }

    private void viewDetailsTutorial() {

        TextView txtCambiado = (TextView)findViewById(R.id.language);
        txtCambiado.setText("Idioma : " + languaje);

        txtCambiado = (TextView)findViewById(R.id.date);
        txtCambiado.setText("Fecha : " + date);

        txtCambiado = (TextView)findViewById(R.id.duration);
        txtCambiado.setText("Duración : " + duration + " minutos");

        txtCambiado = (TextView)findViewById(R.id.language);
        txtCambiado.setText("Lenguaje : "+languaje);

        txtCambiado = (TextView)findViewById(R.id.cost);
        txtCambiado.setText("Costo : " + cost);

        txtCambiado = (TextView)findViewById(R.id.name_tutor);
        txtCambiado.setText("Nombre : " + nameTeacher);
    }

    public void aceptarMiTutoria(View view){

        intent = new Intent(this, MyTutorialsActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("view","1");
        startActivity(intent);
    }

    public void cancelarMiTutoria(View view){
        //modificar la base de datos

        intent = new Intent(this, MyTutorialsActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("view","1");
        startActivity(intent);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    intent = new Intent(MyTutorials2Activity.this, NewTutorialActivity.class);
                    intent.putExtra("userID",userId);
                    intent.putExtra("view", "1");
                    startActivity(intent);
                    return true;

                case R.id.navigation_historial:
                    intent = new Intent(MyTutorials2Activity.this, HistorialActivity.class);
                    startActivity(intent);
                    return true;

                case R.id.navigation_myTutorial:
                    return true;

            }
            return false;
        }


    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void viewProfile(MenuItem item) {
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra("userID",userId);
        startActivity(intent);
    }
}