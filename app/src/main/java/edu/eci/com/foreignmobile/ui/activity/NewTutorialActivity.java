package edu.eci.com.foreignmobile.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.eci.com.foreignmobile.R;

public class NewTutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_tutorial);
    }
}
