package bpm.togol;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import bpm.model.Event;
import bpm.model.Sport;
import bpm.model.Team;
import bpm.service.EventService;
import bpm.service.ServiceFactory;
import bpm.togol.enuns.SportIconEnum;
import bpm.util.DateUtil;
import bpm.util.JsonConverter;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static bpm.util.Constants.EVENT_KEY;

public class MainActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback, View.OnClickListener {

    private static final int MY_LOCATION_REQUEST_CODE = 99;
    private GoogleMap mMap;
    protected LocationManager locationManager;
    private Location myLocation;

    private ServiceFactory serviceFactory = new ServiceFactory();
    private EventService eventService = serviceFactory.build();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    List<Address> geocodeMatches;

    Button button;

    TextView place;

    Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: esse trecho deve ser retirado daqui *************************
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //********************************************************************

        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getPermision();
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        button = (Button) findViewById(R.id.city);
        button.setOnClickListener(this);

        place = (TextView) findViewById(R.id.place);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.API)
                .build();
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        loadLocation();
    }

    private void loadLocation() {

        LatLng location = getLocation();

        mMap.addMarker(new MarkerOptions().position(location).visible(true));
        getMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
        place.setText("");
    }

    protected synchronized void getPermision() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_LOCATION_REQUEST_CODE);
    }

    private LatLng getLocation() {

        geocodeMatches = getGoogleLocation();
        if (geocodeMatches != null && !geocodeMatches.isEmpty()) {
            return new LatLng(geocodeMatches.get(0).getLatitude(), geocodeMatches.get(0).getLongitude());
        }
        //pegar localização da pessoa

        return new LatLng(-18.9146078, -48.2753801);
    }

    private List<Address> getGoogleLocation() {
        try {
            String location = (place == null || place.getText() == null ? "" : place.getText().toString());
            geocodeMatches =
                    new Geocoder(getBaseContext()).getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geocodeMatches;
    }

    private void getMarkers() {

        List<Event> events = eventService.findEventsByLocation(0, 0);

        for (Event event : events) {
            LatLng point = new LatLng(event.getEventLocation().getLatitude(), event.getEventLocation().getLongitude());
            MarkerOptions marker = new MarkerOptions()
                    .position(point)
                    .snippet(JsonConverter.toJson(event))
                    .icon(BitmapDescriptorFactory.fromResource(getIcon(event.getCompetition().getSport())));

            mMap.addMarker(marker);
            setMarkerInfo(mMap);
        }

    }

    private int getIcon(Sport sport) {
        SportIconEnum icon = SportIconEnum.getValueOf(sport.getIcon().name());
        return icon != null ? icon.getResource() : 0;
    }

    private String buildSnippets(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(builTeams(event));
        stringBuilder.append(buildPlace(event));
        return stringBuilder.toString();
    }

    private String buildPlace(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(event.getEventLocation().getPlace());
        if (DateUtil.hasHour(event.getDate())) {
            stringBuilder.append(" - ");
            stringBuilder.append(DateUtil.getFormatedHour(event.getDate()));
        }
        return stringBuilder.toString();
    }

    private String builTeams(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        if (event.getGames() != null && event.getGames().size() == 1) {
            for (Team team : event.getGames().get(0).getTeams()) {
                stringBuilder.append(team.getName());
                stringBuilder.append(" X ");
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length() - 1);
            }
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    private void loadDialogFragment() {
        FragmentManager fm = getFragmentManager();
        DialogFragmentInitial dialogFragment = new DialogFragmentInitial(getBaseContext());
        dialogFragment.show(fm, "Sample Fragment");
    }

    public void setMarkerInfo(GoogleMap markerInfo) {

        markerInfo.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent eventScreen = new Intent(getBaseContext(), EventScreen.class);
                eventScreen.putExtra(EVENT_KEY, marker.getSnippet());
                startActivity(eventScreen);
            }
        });

        markerInfo.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }


            @Override
            public View getInfoContents(Marker marker) {

                final Event event = JsonConverter.fromJson(marker.getSnippet(), Event.class);

                LinearLayout info = new LinearLayout(getBaseContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getBaseContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(event.getCompetition().getName());

                TextView snippet = new TextView(getBaseContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(buildSnippets(event));
                snippet.setGravity(Gravity.CENTER);

                loadRout(event);

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    public void loadRout(Event event) {
        LatLng eventLocation = new LatLng(event.getEventLocation().getLatitude(),
                event.getEventLocation().getLongitude());

        Location location = getMyLocation();
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());

        createRoute(eventLocation, myLocation);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://bpm.togol/http/host/path")
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://bpm.togol/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        loadLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } else {
              // A permissão foi negada
            }
        }
    }

    public static ArrayList getDirections(double lat1, double lon1, double lat2, double lon2) {
        String url = "http://maps.googleapis.com/maps/api/directions/xml?origin=" +lat1 + "," + lon1  + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
        String tag[] = { "lat", "lng"};
        ArrayList list_of_geopoints = new ArrayList();
        HttpResponse response = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost, localContext);
            InputStream in = response.getEntity().getContent();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);
            if (doc != null) {
                NodeList nl1, nl2, nl3;
                nl1 = doc.getElementsByTagName(tag[0]);
                nl2 = doc.getElementsByTagName(tag[1]);
                nl3 = doc.getElementsByTagName("leg");
                if (nl1.getLength() > 0) {
                    list_of_geopoints = new ArrayList();
                    for (int i = 0; i < nl1.getLength() - 4; i++) {
                        Node node1 = nl1.item(i);
                        Node node2 = nl2.item(i);
                        double lat = Double.parseDouble(node1.getTextContent());
                        double lng = Double.parseDouble(node2.getTextContent());
                        LatLng point = new LatLng(lat, lng);
                        if (!list_of_geopoints.contains(point)) {
                            list_of_geopoints.add(point);
                        }
                    }
                } else {
                    // No points found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_of_geopoints;
    }

    public void createRoute(LatLng position1, LatLng position2){

        ArrayList points = getDirections(position1.latitude, position1.longitude, position2.latitude, position2.longitude);

        PolylineOptions polygonOptions = new PolylineOptions().addAll(points);

        cleanPolylineIfNeeded();
        polyline = mMap.addPolyline(polygonOptions);
        polyline.setColor(RED);
    }

    private void cleanPolylineIfNeeded() {
        if (polyline != null) {
            polyline.remove();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    public Location getMyLocation() {
        getLocalPermission();
        return myLocation;
    }

    private void getLocalPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getPermision();
        }
        // Espera a permissao ser concedida - Melhorar esse código
        while (true) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                // getting GPS status
                boolean isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // getting network status
                boolean isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // no network provider is enabled
                } else {
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                0, 0, this);
                        Log.d("Network", "Network Enabled");
                        if (locationManager != null) {
                            myLocation = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            mMap.setMyLocationEnabled(true);
                        }
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        if (myLocation == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    0, 0, this);
                            Log.d("GPS", "GPS Enabled");
                            if (locationManager != null) {
                                myLocation = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                mMap.setMyLocationEnabled(true);
                            }
                        }
                    }
                }

                break;
            }
        }
    }
}
