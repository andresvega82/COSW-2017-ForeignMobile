package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import edu.eci.com.foreignmobile.R;

/**
 * Created by tata on 26/04/17.
 */

public class HistorialActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent fragen = null;
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    fragen = new Intent(HistorialActivity.this, NewTutorialActivity.class);
                    startActivity(fragen);
                    return true;
                case R.id.navigation_historial:
                    return true;
                case R.id.navigation_myTutorial:
                    fragen = new Intent(HistorialActivity.this, MyTutorialsActivity.class);
                    startActivity(fragen);
                    return true;

            }
            return false;
        }


    };
}
