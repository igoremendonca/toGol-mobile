package bpm.togol;

import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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


        //ex
        if (getIntent().getExtras() != null){
            String key = getIntent().getExtras().getString("key");
            Log.i("MainActivity", key);
        }
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
        LatLng point1 = new LatLng(-18.914221, -48.296323);
        LatLng point2 = new LatLng(-18.917991, -48.259115);
        LatLng point3 = new LatLng(-18.933047, -48.271303);
        LatLng point4 = new LatLng(-18.895410, -48.284324);

        mMap.addMarker(new MarkerOptions().position(point1).title("Amistoso Internacional").snippet("Buffalo Bills X New England Patriots\n22:00").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_footbal)));
        mMap.addMarker(new MarkerOptions().position(point2).title("Copa Futel de Futsal").snippet("JuntoMisturados X Roosevelt\n19:00").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_soccer)));
        mMap.addMarker(new MarkerOptions().position(point3).title("Campeonato Mineiro de Futebol").snippet("Boa Esporte X Uberlandia\n20:00").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_soccer)));
        mMap.addMarker(new MarkerOptions().position(point4).title("Copa Uberlandense de Volei").snippet("Poderosas X MiniSaia\n18:00").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_volei)));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

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


    private void loadDialogFragment() {
        FragmentManager fm = getFragmentManager();
        DialogFragmentInicial dialogFragment = new DialogFragmentInicial();
        dialogFragment.show(fm, "Sample Fragment");
    }
}
