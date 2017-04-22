package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.eci.com.foreignmobile.R;

/**
 * Created by 2099340 on 4/19/17.
 */

public class RegistrerActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebase = FirebaseAuth.getInstance();
    public final static String nombre = "nombre";
    public final static String apellido = "apellido";
    public final static String email = "email";
    public final static String password = "password";
    public final static String cellphone = "cellphone";
    public final static String country = "country";
    public final static String choice = "choice";
    private String desicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.registrer);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }

    public void continuarSingUp(View v){
        Intent next = new Intent(this,Registrer2Activity.class);
        EditText nombre = (EditText) findViewById(R.id.editText);
        EditText apellido = (EditText) findViewById(R.id.editText2);
        EditText cellphone = (EditText) findViewById(R.id.editText4);
        EditText country = (EditText) findViewById(R.id.editText5);
        EditText email = (EditText) findViewById(R.id.editText3);
        EditText password = (EditText) findViewById(R.id.editText6);
        RadioButton estudiante = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton profesor = (RadioButton) findViewById(R.id.radioButton2);

        next.putExtra("nombre",nombre.getText().toString());
        next.putExtra("apellido",apellido.getText().toString());
        next.putExtra("cellphone",cellphone.getText().toString());
        next.putExtra("country",country.getText().toString());
        next.putExtra("email",email.getText().toString());
        next.putExtra("password",password.getText().toString());
        next.putExtra("choice",desicion);

        startActivity(next);
    }

    public void singUp(View v){
        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar3);
        pr.setVisibility(View.VISIBLE);
        EditText email = (EditText) findViewById(R.id.editText3);
        EditText password = (EditText) findViewById(R.id.editText6);
        if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
            firebase.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        viewLogin();
                    }
                }
            });
        }else{
            pr.setVisibility(View.GONE);
        }
    }


    public void viewLogin(){
        Intent next = new Intent(this,LoginActivity.class);
        startActivity(next);
    }

    public void radioButtonProfesor(View view) {
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton3);
        radioButton.setChecked(false);
        desicion = "Profesor";
    }

    public void radioButtonEstudiante(View view) {
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton2);
        radioButton.setChecked(false);
        desicion = "Estudiante";
    }
}
