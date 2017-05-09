package edu.eci.com.foreignmobile.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

import edu.eci.com.foreignmobile.BuildConfig;
import edu.eci.com.foreignmobile.R;

import static edu.eci.com.foreignmobile.R.id.imageButton;
import static edu.eci.com.foreignmobile.R.id.imageView;

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
    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY+"media";
    private String TEMPORAL_PICTURE_NAME = "photo.jpg";
    private String location ="";
    private Bitmap bitmap = null;

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
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        //bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        //byte[] bArray = bos.toByteArray();
                        String encodedImage = "";
                        try {
                            //encodedImage = new String(bArray, Charset.forName("UTF-8"));
                        } catch (Exception   e) {
                            e.printStackTrace();
                        }
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

    public void addPhoto(View view) {
        final CharSequence[] options = {"Camera", "Galeria","Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Registrer2Activity.this);
        builder.setTitle("Choose an option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(options[which]=="Camera"){
                    openCamera();
                }else if(options[which]=="Galeria"){
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //i.setType("image/*");
                    startActivityForResult(i, 200);

                    //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //intent.setType("imagen/*");
                    //startActivityForResult(intent.createChooser(intent,"Choose an app"),200);
                }else if (options[which]=="Cancel"){
                    dialog.dismiss();
                }
            }


        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if(resultCode== RESULT_OK){
                    String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator+MEDIA_DIRECTORY+File.separator+TEMPORAL_PICTURE_NAME;
                    location = dir+"";
                    System.out.println(location+" Con camara queda aqui.");
                    decodeBitmap(dir);
                }
                break;
            case 200:
                if(resultCode==RESULT_OK){
                    Uri path = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(path,filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    location = cursor.getString(columnIndex)+"";
                    cursor.close();
                    Bitmap bmp = null;
                    try {
                        bmp = getBitmapFromUri(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bitmap = bmp;
                    bitmap = Bitmap.createScaledBitmap(bitmap,300,400,true);
                    ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
                    ib.setImageBitmap(bitmap);
                }
                break;
        }
    }



    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    private void decodeBitmap(String dir) {
        bitmap = BitmapFactory.decodeFile(dir);
        bitmap = Bitmap.createScaledBitmap(bitmap,300,400,true);
        ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        ib.setImageBitmap(bitmap);

    }

    private void openCamera() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),MEDIA_DIRECTORY);
        file.mkdirs();
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator+MEDIA_DIRECTORY+File.separator+TEMPORAL_PICTURE_NAME;
        File newFile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider",newFile));
        startActivityForResult(intent,100);

    }


    private class DoPost extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String [] parametros = params[0].split(",");
            System.out.println(Arrays.toString(parametros));
            //Url to Post

            String url = "https://foreignest.herokuapp.com/app/addUser";
            //String url = "https://192.168.193.1:8080/app/addUser";
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
                //jsonObject.put("photo",parametros[11]);
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

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}


