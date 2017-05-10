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

import edu.eci.com.foreignmobile.R;
import edu.eci.com.foreignmobile.entities.AdapterItem;
import edu.eci.com.foreignmobile.entities.Tutor;

public class MyTutorialsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String userId="";
    String view = "";
    Intent intent;
    ArrayList<Tutor> tutorArrayList = new ArrayList<Tutor>();


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



        listTutorials();

    }


    private void listTutorials() {

        //ListView listView = (ListView) findViewById(R.id.listTutorials);
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,tutorias);
        //listView.setAdapter(adapter);



        ListView lv = (ListView) findViewById(R.id.listTutorials3);

        Drawable photo = getResources().getDrawable( R.drawable.profesor1);
        tutorArrayList.add(new Tutor("English", "Seth Rowan", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 20000 , photo));
        photo = getResources().getDrawable( R.drawable.profesor2);
        tutorArrayList.add(new Tutor("English", "Stephanie Hourly", "Lorem ipsum dolor sit amet consectetur et sed adipiscing elit. Curabitur vel sem sit dolor neque semper magna lorem ipsum.", 23000 ,photo));
        photo = getResources().getDrawable( R.drawable.profesor3);
        tutorArrayList.add(new Tutor("English", "John Stephen Thomas", "Want to learn fast & have fun? I teach English/ French using music/film/ poetry/jornalism! Contact me to speed up your learning!.", 25000 , photo));


        AdapterItem adapter = new AdapterItem(this, tutorArrayList);
        lv.setAdapter(adapter);



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = null;
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    intent = new Intent(MyTutorialsActivity.this, NewTutorialActivity.class);
                    intent.putExtra("userID",userId);
                    intent.putExtra("view", "1");
                    startActivity(intent);
                    return true;

                case R.id.navigation_historial:
                    intent = new Intent(MyTutorialsActivity.this, HistorialActivity.class);
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
