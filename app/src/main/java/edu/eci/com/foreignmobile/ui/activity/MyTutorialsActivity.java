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

public class MyTutorialsActivity extends AppCompatActivity{

    Intent intent = null;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tutorials);
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
                    intent = new Intent(MyTutorialsActivity.this, NewTutorialActivity.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("view","1");
                    startActivity(intent);
                    return true;
                case R.id.navigation_historial:
                    intent = new Intent(MyTutorialsActivity.this, HistorialActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                    return true;
                case R.id.navigation_myTutorial:
                    return true;

            }
            return false;
        }


    };

}
