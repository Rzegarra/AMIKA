package com.example.cesar.vego;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Path;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.content.pm.PackageManager;

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
import com.google.android.gms.maps.model.BitmapDescriptor;

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
import com.google.code.gson;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnFindPath;
    private Button btnStart;
    private Button btnEnd;

    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>(); // marker de inico sera de color Verde
    private List<Marker> destinationMarkers = new ArrayList<>();// marker de llegada de color Negro
    private ProgressDialog progressDialog;
    private Marker markerOrigin;
    private Marker markerDestintion;



    private RadioGroup rg;
    private RadioButton rb;

//    private UserFunctions userFunction = new UserFunctions();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etDestination = (EditText) findViewById(R.id.etDestination);

        /**   btnFindPath.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        sendRequest();
        }
        });*/
        //Cuando se presione en btnStart, aparesca el icono marker verde
        //Cuando se presione en btnEnd, aparesca el icono marker negro
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        rg =(RadioGroup) findViewById(R.id.rgroup);
    }
/*
    private void sendRequest (){

        String destination = etDestination.getText().toString();

        if (destination.isEmpty()){
            Toast.makeText(this, "Holanda, pon tu destino!", Toast.LENGTH_SHORT).show();// aca pone la mierda que debe salir en el labelText
        }

        try{
            new Path.Direction()
        }
    }*/

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng hcmus = new LatLng(-16.3900397, -71.5356918);
        LatLng hcmus_2 = new LatLng(-16.3921694, -71.5357161);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 15));
        /*originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("You are Aqui")
                .position(hcmus)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
            */
        //hcmus = markerOrigin.getPosition();
        markerOrigin = mMap.addMarker(new MarkerOptions()
                .title("You are Aqui")
                .position(hcmus)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        markerDestintion = mMap.addMarker(new MarkerOptions()
                .title("You are here")
                .position(hcmus_2)
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cesar.vego/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cesar.vego/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }



    public void rbclick(View v)
    {
        int radionButtonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(radionButtonId);
        Toast.makeText(getBaseContext(),rb.getText(),Toast.LENGTH_LONG).show();
        /*    if(rb.getText()=="Start") {
                markerOrigin = mMap.addMarker(new MarkerOptions()
                        .title("You are Aqui")
                        .draggable(true)
                        .s
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            }
        markerDestintion.setVisible(false);
            if(rb.getText()=="End") {

                markerDestintion.setVisible(true);
            }
        markerOrigin.isVisible();*/
    }



    public void findPath(View v) {
        Gson gson = new Gson();

        int entero = 2;
        Log.d("Formato Json entero",gson.toJson(entero));

        String cadena ="Gson";
        Log.d("Formato Json cadena",gson.toJson(cadena));

        int arreglo[] ={1,2,3,4};
        Log.d("Formato Json arreglo",gson.toJson(arreglo));

        String mensage = "Ubicacion: " + markerOrigin.getPosition().longitude + "-" + markerOrigin.getPosition().latitude;
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage(mensage)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


}
