package bpm.togol;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import bpm.model.EventLocation;
import bpm.model.Team;
import bpm.service.EventoService;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private EventoService eventoService = new EventoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.addMarker(new MarkerOptions().position(uberlandia).title("Estou em Uberlândia"));
        getMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uberlandia, 13));

        loadDialogFragment();
    }

    private void getMarkers() {

        List<EventLocation> eventLocations = eventoService.findEventsByLocation(0, 0);

        for (EventLocation v : eventLocations) {
            LatLng point = new LatLng(v.getLatitude(), v.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(point).title(v.getEvent().getCompetition().getName()).snippet(buildSnippets(v)).icon(BitmapDescriptorFactory.fromResource(v.getEvent().getCompetition().getSport().getIcon()));

            mMap.addMarker(marker);
            setMarkerInfo(mMap);
        }
    }

    private String buildSnippets(EventLocation v) {
        StringBuilder stringBuilder = new StringBuilder();
        //TODO: refazer essa parte
        return v.getNameTest();
//        return "\"Buffalo Bills X New England Patriots\\n22:00";
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
                //TODO: refazer
                eventScreen.putExtra("competitionName", marker.getTitle());
                String[] snippet = marker.getSnippet().split("\n");
                eventScreen.putExtra("times", snippet[0]);
                eventScreen.putExtra("competitionPlace", snippet[1]);

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

                LinearLayout info = new LinearLayout(getBaseContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getBaseContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getBaseContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());
                snippet.setGravity(Gravity.CENTER);

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }
}
