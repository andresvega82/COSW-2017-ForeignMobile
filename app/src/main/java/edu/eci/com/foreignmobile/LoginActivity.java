package edu.eci.com.foreignmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 2099340 on 4/19/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.login);
    }


    public void signUp(View v){
        Intent next = new Intent(this,RegistrerActivity.class);
        startActivity(next);
    }

    public void login(View v){
        Intent next = new Intent(this,NewTutorialActivity.class);
        startActivity(next);
    }
}
