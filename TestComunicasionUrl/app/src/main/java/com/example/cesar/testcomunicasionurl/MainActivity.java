package com.example.cesar.testcomunicasionurl;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Path;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.content.pm.PackageManager;
/*
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import java.net.URLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.Reader;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.cesar.testcomunicasionurl.Animal;
import com.google.gson.stream.JsonReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGet = (Button)findViewById(R.id.btnGet);
        tvData = (TextView)findViewById(R.id.tvJsonItem);
        Button btnPost = (Button)findViewById(R.id.btnPots);

        btnPost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new JSONTask().execute("https://nodeamica-demo.herokuapp.com/post");
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new JSONTask().execute("https://nodeamica-demo.herokuapp.com/get");
            }
        });
    }

    public class JSONTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params){
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line="";
                while ((line = reader.readLine())!=null){
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();
                try {
                    if(reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            tvData.setText(result);
        }
    }
    /*
    *Creando clase animal
     */




    /*
    *Primero se implementa un metodo que cordine el retorno de los
    * objetos de tipo animal(tipo que desees)
    * Y debe crearse una instancia JsonReader
     */


}
