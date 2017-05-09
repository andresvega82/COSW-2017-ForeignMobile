package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.AdapterItem;
import edu.eci.com.foreignmobile.entities.Tutor;

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
            selectListTutorials();

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
        tutorArrayList.add(new Tutor("English", "Seth Rowan", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", photo));
        photo = getResources().getDrawable( R.drawable.profesor2);
        tutorArrayList.add(new Tutor("English", "Stephanie Hourly", "Lorem ipsum dolor sit amet consectetur et sed adipiscing elit. Curabitur vel sem sit dolor neque semper magna lorem ipsum.", photo));
        photo = getResources().getDrawable( R.drawable.profesor3);
        tutorArrayList.add(new Tutor("English", "John Stephen Thomas", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", photo));

        //tutorArrayList = getTutorials();

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

    public ArrayList<Tutor> getTutorials() {
        ArrayList<Tutor> tutorials = null;

        return tutorials;
    }
}
