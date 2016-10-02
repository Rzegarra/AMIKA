package Model;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by cesar on 21/09/2016.
 */
public class DirectionFinder {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "YOU_ARE_HERE";
    private DirectionFinderListener listener;
   // private String origin;
    private String destination;

    public DirectionFinder(DirectionFinderListener listener, String origin, String destination) {
        this.listener = listener;
       // this.origin = origin;
        this.destination = destination;
    }
}
