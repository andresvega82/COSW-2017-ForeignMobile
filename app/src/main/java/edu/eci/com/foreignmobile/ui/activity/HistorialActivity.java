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
    Intent intent;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        intent = getIntent();
        userId = intent.getStringExtra("userId");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_newTutorial:
                    intent = new Intent(HistorialActivity.this, NewTutorialActivity.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("view","1");
                    startActivity(intent);
                    return true;
                case R.id.navigation_historial:
                    return true;
                case R.id.navigation_myTutorial:
                    intent = new Intent(HistorialActivity.this, MyTutorialsActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                    return true;

            }
            return false;
        }


    };
}
