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

public class MyTutorials2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String userId="";
    String view = "";
    Intent intent;


    ArrayList<Tutoria> tutoriasArrayList = new ArrayList<Tutoria>();
    ArrayList<Integer> tutorIdArrayList = new ArrayList<Integer>();
    ArrayList<User> usersArrayList = new ArrayList<User>();
    ArrayList<TutoriaItem> tutorArrayList = new ArrayList<TutoriaItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tutorial);
        intent = getIntent();
        userId = intent.getStringExtra("userId");
        view = intent.getStringExtra("view");




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
        findViewById(R.id.activity_my_tutorials).setVisibility(View.VISIBLE);


        getTutorials();
        getTutoresById();

    }


    private void listTutorials() {

        //ListView listView = (ListView) findViewById(R.id.listTutorials);
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,tutorias);
        //listView.setAdapter(adapter);



        ListView lv = (ListView) findViewById(R.id.listTutorials3);

        /* Drawable photo = getResources().getDrawable( R.drawable.profesor1);
        tutorArrayList.add(new Tutor("English", "Seth Rowan", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 20000 , photo));
        photo = getResources().getDrawable( R.drawable.profesor2);
        tutorArrayList.add(new Tutor("English", "Stephanie Hourly", "Lorem ipsum dolor sit amet consectetur et sed adipiscing elit. Curabitur vel sem sit dolor neque semper magna lorem ipsum.", 23000 ,photo));
        photo = getResources().getDrawable( R.drawable.profesor3);
        tutorArrayList.add(new Tutor("English", "John Stephen Thomas", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 25000 , photo));
        */

        AdapterItemMyTutorials adapter = new AdapterItemMyTutorials(this, tutorArrayList);
        lv.setAdapter(adapter);



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



    //get

    public void getTutorials() {
        DoGetTutorial doGetTutorial = new DoGetTutorial();
        doGetTutorial.execute();

    }

    public void getTutoresById() {

        DoGetTutores doPost = new DoGetTutores();
        doPost.execute();

    }

    private class DoGetTutorial extends AsyncTask<Void, String , ArrayList<Tutoria>> {
        @Override
        protected ArrayList<Tutoria> doInBackground(Void... params) {
            ArrayList<Tutoria> resp = new ArrayList<>();

            try {
                URL url = new URL("https://foreignest.herokuapp.com/tutorial/tutoresMobile/");
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                int rc = urlConnection.getResponseCode();

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("RESPONSE ------> "+response.toString());

                JSONArray jsonArray = new JSONArray(response.toString());

                JSONObject jsonObject;
                String s;

                tutorArrayList = new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++) {
                    //System.out.println(jsonArray.getJSONObject(i).toString());
                    jsonObject=jsonArray.getJSONObject(i);


                    // creacion de objetos tutoria
                    Date date = new Date();
                    long date1 = Long.parseLong(jsonObject.getString("date"));
                    date.setTime(date1);

                    String state = jsonObject.getString("state");
                    int duration = Integer.parseInt(jsonObject.getString("duration"));

                    JSONObject jsonObjectTutor = jsonObject.getJSONObject("idTutor");
                    int id = Integer.parseInt(jsonObjectTutor.getString("id"));
                    int lenguajeId = Integer.parseInt(jsonObjectTutor.getString("lenguajeId"));
                    int teachersId = Integer.parseInt(jsonObjectTutor.getString("teachersId"));
                    IdTutor idTutor = new IdTutor(id, lenguajeId, teachersId);

                    int payment =  Integer.parseInt(jsonObject.getString("payment"));
                    int cost = Integer.parseInt(jsonObject.getString("cost"));

                    Tutoria tutoria = new Tutoria(date, state, duration, idTutor, payment, cost);

                    if(!tutorIdArrayList.contains(idTutor.getTearchersId())){
                        tutorIdArrayList.add(idTutor.getTearchersId());
                    }

                    resp.add(tutoria);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return resp;


        }


        @Override
        protected void onPostExecute(ArrayList<Tutoria> p) {
            super.onPostExecute(p);
            System.out.println("Response To onPostExecute --> "+p.toString());
            if(p!=null){
                for (int i=0; i < p.size(); i++){
                    if (p.get(i).getState().equals("Programada"))
                        tutoriasArrayList.add(p.get(i));
                }
            }
        }
    }

    private class DoGetTutores extends AsyncTask<Void, String , ArrayList<TutoriaItem>> {
        @Override
        protected ArrayList<TutoriaItem> doInBackground(Void... params) {
            ArrayList<TutoriaItem> resp = new ArrayList<>();
            usersArrayList = new ArrayList<>();
            tutorArrayList = new ArrayList<>();
            try {

                for (int i=0;i<tutoriasArrayList.size();i++) {
                    System.out.println("Tutorias ----->"+tutoriasArrayList.get(i).getIdTutor().getTearchersId());
                    URL url = new URL("https://foreignest.herokuapp.com/tutores/tutor/" + tutoriasArrayList.get(i).getIdTutor().getTearchersId());
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                    int rc = urlConnection.getResponseCode();

                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    System.out.println("RESPONSE ------> " + response.toString());

                    JSONObject jsonObject = new JSONObject(response.toString());


                    String s;

                    //System.out.println(jsonArray.getJSONObject(i).toString());
                    //jsonObject = jsonArray.getJSONObject(i);


                    // creacion de objetos tutoria

                    String user_id = jsonObject.getString("user_id");
                    String name = jsonObject.getString("name");
                    String lastName = jsonObject.getString("lastName");
                    String email = jsonObject.getString("email");
                    String phone = jsonObject.getString("phone");
                    String country = jsonObject.getString("country");
                    Integer age = Integer.parseInt(jsonObject.getString("age"));
                    String languaje = jsonObject.getString("languajes");
                    Integer creditCard_payment_id = Integer.parseInt(jsonObject.getString("creditCard_payment_id"));
                    User user = new User(user_id, name, lastName, email, phone, country, age, creditCard_payment_id);

                    usersArrayList.add(user);

                    TutoriaItem tutor = new TutoriaItem(tutoriasArrayList.get(i).getState(), user.getFullName(), tutoriasArrayList.get(i).getDate(), tutoriasArrayList.get(i).getDuration(), "Lenguaje" , tutoriasArrayList.get(i).getCost(), null);
                    resp.add(tutor);

                    System.out.println("tutoria My Tutorials ------------->"+ tutor.getName_profesor());
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return resp;


        }


        @Override
        protected void onPostExecute(ArrayList<TutoriaItem> p) {
            super.onPostExecute(p);
            System.out.println("Response To onPostExecute historial --> "+p.toString());
            if(p!=null){
                tutorArrayList = p;
            }
            listTutorials();
        }
    }


}