package edu.eci.com.foreignmobile.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.AdapterItem;
import edu.eci.com.foreignmobile.entities.Tutor;

public class NewTutorialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner listLanguages;
    String[] languages = {"¿Qué idioma quieres practicar?","English", "Español", "Portugues", "Deutsch"};
    String[] tutorias = { "tutor 1", "tutor 2", "tutor 3", "tutor 4"};
    CalendarView calendarView;
    TextView dateDisplay;
    ArrayList<Tutor> tutorArrayList = new ArrayList<Tutor>();
    Tutor tutor = null;
    String language = "";
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tutorial);

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
        ///

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ///
        selectLanguage();
        selectDate();
        //selectTutorial();

    }

    private void selectDate() {

        calendarView = (CalendarView) findViewById(R.id.calendar);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Día seleccionado: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Día seleccionado: " + i2 + " / " + i1 + " / " + i);

                Toast.makeText(getApplicationContext(), "Día seleccionado:\n" + "Día = " + i2 + "\n" + "Mes = " + i1 + "\n" + "Año = " + i, Toast.LENGTH_LONG).show();
                date = "Día = " + i2 + "Mes = " + i1 + "Año = " + i;
            }
        });

    }


    public void searchTutorial(View view){

        findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
        findViewById(R.id.content_new_tutorial3).setVisibility(View.INVISIBLE);
        findViewById(R.id.content_new_tutorial2).setVisibility(View.VISIBLE);
        selectListTutorials();
    }

    public void cancelarTutoria(View view){
        findViewById(R.id.content_new_tutorial).setVisibility(View.VISIBLE);
        findViewById(R.id.content_new_tutorial3).setVisibility(View.INVISIBLE);
        findViewById(R.id.content_new_tutorial2).setVisibility(View.INVISIBLE);

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

        AdapterItem adapter = new AdapterItem(this, tutorArrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Toast toast;
                switch (position) {
                    case 0:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_LONG);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 1:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_LONG);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 2:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_LONG);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;
                    case 3:
                        toast = Toast.makeText(getApplicationContext(), tutorArrayList.get(position).getTitle(), Toast.LENGTH_LONG);
                        tutor = tutorArrayList.get(position);
                        toast.show();
                        selectTutorial();
                        break;

                }
            }
        });

    }

    private void selectTutorial() {

        findViewById(R.id.content_new_tutorial).setVisibility(View.INVISIBLE);
        findViewById(R.id.content_new_tutorial2).setVisibility(View.INVISIBLE);
        findViewById(R.id.content_new_tutorial3).setVisibility(View.VISIBLE);

        TextView txtCambiado = (TextView)findViewById(R.id.idioma);
        txtCambiado.setText("Idioma : "+language);

        txtCambiado = (TextView)findViewById(R.id.fecha);
        txtCambiado.setText("Fecha : "+date);

        txtCambiado = (TextView)findViewById(R.id.name_tutor);
        txtCambiado.setText("Nombre : "+tutor.getTitle());

    }


    private void selectLanguage() {
        listLanguages = (Spinner) findViewById(R.id.list_languages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        listLanguages.setAdapter(adapter);

        listLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast toast;
                language = languages[position];
                switch (position){
                    case 1:
                        toast = Toast.makeText(getApplicationContext(), languages[position], Toast.LENGTH_LONG);
                        toast.show();

                        break;
                    case 2:
                        toast = Toast.makeText(getApplicationContext(), languages[position], Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    case 3:
                        toast = Toast.makeText(getApplicationContext(), languages[position], Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    case 4:
                        toast = Toast.makeText(getApplicationContext(), languages[position], Toast.LENGTH_LONG);
                        toast.show();
                        break;


                }
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
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    //mTextMessage.setText(R.string.title_new_tutorial);
                    return true;
                case R.id.navigation_historial:
                    //mTextMessage.setText(R.string.title_historial);
                    return true;
                case R.id.navigation_myTutorial:
                    //mTextMessage.setText(R.string.title_myTutorial);
                    return true;

            }
            return false;
        }

    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
