package edu.eci.com.foreignmobile.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import edu.eci.com.foreignmobile.R;

/**
 * Created by 2099340 on 4/19/17.
 */

public class Registrer2Activity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebase = FirebaseAuth.getInstance();
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String cellphone;
    private String country;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registrer2);
        Intent intent = getIntent();
        nombre = intent.getStringExtra(RegistrerActivity.nombre);
        apellido = intent.getStringExtra(RegistrerActivity.apellido);
        email = intent.getStringExtra(RegistrerActivity.email);
        password = intent.getStringExtra(RegistrerActivity.password);
        cellphone = intent.getStringExtra(RegistrerActivity.cellphone);
        country = intent.getStringExtra(RegistrerActivity.country);
        choice = intent.getStringExtra(RegistrerActivity.choice);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }



    public void singUp(View v){
        final EditText numeroTarjeta = (EditText) findViewById(R.id.editText10);
        final EditText postal = (EditText) findViewById(R.id.editText12);
        final EditText cvv = (EditText) findViewById(R.id.editText13);


        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar3);
        pr.setVisibility(View.VISIBLE);
        if(!email.equals("") && !password.equals("")) {
            firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        new DoPost().execute(task.getResult().getUser().getUid()+","+numeroTarjeta.getText().toString()+","+new Date()+","+
                                postal.getText().toString()+","+cvv.getText().toString()+
                                ","+nombre+","+apellido+","+email+","+cellphone+","+country+","+choice);
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


    private class DoPost extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String [] parametros = params[0].split(",");
            System.out.println(Arrays.toString(parametros));
            //Url to Post
            String url = "http://10.2.67.68:8080/app/addUser";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            JSONObject jsonObject = new JSONObject();
            try{

                jsonObject.put("paymentId",Integer.parseInt(parametros[1]));
                jsonObject.put("cardNumber",Integer.parseInt(parametros[1]));
                jsonObject.put("expirationDate","2017-04-02");
                jsonObject.put("postalCode",Integer.parseInt(parametros[3]));
                jsonObject.put("cvv",Integer.parseInt(parametros[4]));
                jsonObject.put("user_id",parametros[0]);
                jsonObject.put("name",parametros[5]);
                jsonObject.put("lastName",parametros[6]);
                jsonObject.put("email",parametros[7]);
                jsonObject.put("phone",parametros[8]);
                jsonObject.put("country",parametros[9]);
                jsonObject.put("age",20);
                jsonObject.put("isStudent",parametros[10]);
                System.out.println("JSON"+ jsonObject.toString());
            }catch (Exception e){
                e.printStackTrace();
            }




            try {

                // Do Post and create JSON Object
                StringEntity stringEntity = new StringEntity(jsonObject.toString());
                stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
                httpPost.setEntity(stringEntity);
                HttpResponse httpResponse = httpClient.execute(httpPost);

                System.out.println(EntityUtils.toString(httpResponse.getEntity()));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}


