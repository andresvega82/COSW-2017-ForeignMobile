package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.Manifest;
import edu.eci.com.foreignmobile.R;

/**
 * Created by 2099340 on 4/19/17.
 */

public class LoginActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebase = FirebaseAuth.getInstance();
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }


    public void signUp(View v){
        Intent next = new Intent(this,RegistrerActivity.class);
        startActivity(next);
    }

    public void login(View v){
        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar2);
        pr.setVisibility(View.VISIBLE);
        email=(EditText) findViewById(R.id.editText8);
        EditText password = (EditText) findViewById(R.id.editText9);
        if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
            firebase.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        viewTutorial(task.getResult().getUser().getUid());
                    }
                }
            });
        }else{
            pr.setVisibility(View.GONE);
        }



    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    }

    public void viewTutorial(String userId){
        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar2);
        pr.setVisibility(View.GONE);
        Intent next = new Intent(this,NewTutorialActivity.class);
        next.putExtra("userId",userId);
        next.putExtra("email",email.getText());
        next.putExtra("view","1");
        startActivity(next);
    }
}
