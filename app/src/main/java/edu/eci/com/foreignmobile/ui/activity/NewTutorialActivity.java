package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.AdapterItem;
import edu.eci.com.foreignmobile.entities.IdTutor;
import edu.eci.com.foreignmobile.entities.Tutor;
import edu.eci.com.foreignmobile.entities.Tutoria;
import edu.eci.com.foreignmobile.entities.User;

public class NewTutorialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String userId="";
    String view = "";
    Spinner listLanguages;
    String[] languages = {"Seleccionar","English", "Español", "Portugues", "Deutsch"};
    String[] tutorias = { "tutor 1", "tutor 2", "tutor 3", "tutor 4"};
    CalendarView calendarView;
    TextView dateDisplay;
    ArrayList<Tutor> tutorArrayList = new ArrayList<Tutor>();
    Tutor tutor = null;
    String language = "";
    String date = "";
    Intent intent;



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
        getSupportActionBar().setTitle("Nueva Tutoria");

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

        if(Integer.parseInt(view) == 1)
        {
            findViewById(R.id.content_new_tutorial).setVisibility(View.VISIBLE);
            findViewById(R.id.content_new_tutorial2).setVisibility(View.INVISIBLE);
            findViewById(R.id.content_new_tutorial3).setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Nueva Tutoria");
            selectLanguage();
            selectDate();

        }
        else if(Integer.parseInt(view) == 2){
            findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
            findViewById(R.id.content_new_tutorial2).setVisibility(View.VISIBLE);
            findViewById(R.id.content_new_tutorial3).setVisibility(View.INVISIBLE);
            date = intent.getStringExtra("date");
            language = intent.getStringExtra("language");
            getSupportActionBar().setTitle("Tutores");
            getTutorials();


        }
        else if(Integer.parseInt(view) == 3){
            findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
            findViewById(R.id.content_new_tutorial2).setVisibility(View.INVISIBLE);
            findViewById(R.id.content_new_tutorial3).setVisibility(View.VISIBLE);
            date = intent.getStringExtra("date");
            language = intent.getStringExtra("language");
            getSupportActionBar().setTitle("Detalles de Tutoria");
            viewDetails();

        }

    }

    private void viewDetails() {
        TextView txtCambiado = (TextView)findViewById(R.id.idioma);
        txtCambiado.setText("Idioma : "+intent.getStringExtra("language"));

        txtCambiado = (TextView)findViewById(R.id.fecha);
        txtCambiado.setText("Fecha : "+intent.getStringExtra("date"));

        txtCambiado = (TextView)findViewById(R.id.name_tutor);
        txtCambiado.setText("Nombre : "+intent.getStringExtra("tutorName"));
    }

    private void selectDate() {

        calendarView = (CalendarView) findViewById(R.id.calendar);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Día seleccionado: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Date date1 = new Date();
                if(date1.getDate()< i2 & date1.getMonth() <= i1 & date1.getYear() <= i) {
                    dateDisplay.setText("Día seleccionado: " + i2 + " / " + i1 + " / " + i);
                    date = "Día: " + i2 + ", Mes: " + i1 + ", Año: " + i;
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Seleccione una fecha correcta.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }


    public void searchTutorial(View view){

        if(language != "" ) {
            if(date != "") {
                intent = new Intent(this, NewTutorialActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("language", language);
                intent.putExtra("date", date);
                intent.putExtra("view", "2");
                startActivity(intent);

            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Seleccione una fecha", Toast.LENGTH_SHORT);
                toast.show();

            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Seleccione un lenguaje", Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void cancelTutorial(View view){

        intent = new Intent(this, NewTutorialActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("view","1");
        startActivity(intent);

    }

    private void selectTutorial() {

        intent = new Intent(this, NewTutorialActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("view","3");
        intent.putExtra("language", language);
        intent.putExtra("date", date);
        intent.putExtra("tutorName", tutor.getTitle());
        startActivity(intent);
    }


    private void selectListTutorials() {

        //ListView listView = (ListView) findViewById(R.id.listTutorials);
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,tutorias);
        //listView.setAdapter(adapter);



        ListView lv = (ListView) findViewById(R.id.listTutorials);

        Drawable photo = getResources().getDrawable( R.drawable.profesor1);
        tutorArrayList.add(new Tutor("English", "Seth Rowan", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 20000 , photo));
        photo = getResources().getDrawable( R.drawable.profesor2);
        tutorArrayList.add(new Tutor("English", "Stephanie Hourly", "Lorem ipsum dolor sit amet consectetur et sed adipiscing elit. Curabitur vel sem sit dolor neque semper magna lorem ipsum.", 23000 ,photo));
        photo = getResources().getDrawable( R.drawable.profesor3);
        tutorArrayList.add(new Tutor("English", "John Stephen Thomas", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 25000 , photo));

        AdapterItem adapter = new AdapterItem(this, tutorArrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Toast toast;
                switch (position) {
                    case 0:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_SHORT);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 1:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_SHORT);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 2:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_SHORT);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 3:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_SHORT);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;

                }
            }
        });

    }

    private void selectLanguage() {
        listLanguages = (Spinner) findViewById(R.id.list_languages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        listLanguages.setAdapter(adapter);

        listLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast toast;
                if(!languages[position].equals(languages[0]))
                    language = languages[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    return true;
                case R.id.navigation_historial:
                    intent = new Intent(NewTutorialActivity.this, HistorialActivity.class);

                    intent.putExtra("userID",userId);
                    intent.putExtra("view", "1");
                    startActivity(intent);
                    return true;
                case R.id.navigation_myTutorial:
                    intent = new Intent(NewTutorialActivity.this, MyTutorialsActivity.class);
                    startActivity(intent);
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
        System.out.println("This is userId in newTutorial: "+userId);
        intent.putExtra("userID",userId);
        startActivity(intent);
    }

    public void getTutorials() {

        DoPost doPost = new DoPost();
        doPost.execute();
    }


    private class DoPost extends AsyncTask<Void, String , ArrayList<Tutor>> {
        @Override
        protected ArrayList<Tutor> doInBackground(Void... params) {
            ArrayList<Tutor> resp = new ArrayList<>();

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



                    Tutor tutor = new Tutor();

                    //idUniversities.add(jsonObject.getString("username"));
                    resp.add(tutor);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return resp;


        }


        @Override
        protected void onPostExecute(ArrayList<Tutor> p) {
            super.onPostExecute(p);
            System.out.println("Response To onPostExecute --> "+p.toString());
            if(p!=null){
                tutorArrayList = p;
            }
            selectListTutorials();
        }
    }
}
