package bpm.togol;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
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

import java.util.List;

import bpm.model.Event;
import bpm.model.Sport;
import bpm.model.Team;
import bpm.service.EventService;
import bpm.service.ServiceFactory;
import bpm.togol.enuns.SportIconEnum;
import bpm.util.DateUtil;
import bpm.util.JsonConverter;

import static bpm.util.Constants.EVENT_KEY;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ServiceFactory serviceFactory = new ServiceFactory();
    private EventService eventService = serviceFactory.build();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

        // Add a marker in Sydney and move the camera
        LatLng uberlandia = new LatLng(-18.9146078, -48.2753801);
        mMap.addMarker(new MarkerOptions().position(uberlandia).title("Estou em Uberlândia").visible(false));
        getMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uberlandia, 13));

        loadDialogFragment();
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

//    private String buildEventTeams(List<Event> events) {
//        StringBuilder stringBuilder = new StringBuilder();
//        if (events != null && events.size() == 1){
//            stringBuilder = builTeams(events.get(0));
//        }
//        return stringBuilder.toString();
//    }

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
        DialogFragmentInicial dialogFragment = new DialogFragmentInicial();
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

                Event event = JsonConverter.fromJson(marker.getSnippet(), Event.class);

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

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
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
}
