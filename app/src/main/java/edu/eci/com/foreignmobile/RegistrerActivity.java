package edu.eci.com.foreignmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 2099340 on 4/19/17.
 */

public class RegistrerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.registrer);
    }
}
