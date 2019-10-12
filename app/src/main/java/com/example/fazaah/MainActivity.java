package com.example.fazaah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gmap;
    FirebaseDatabase firebaseDatabase;
    public final static String MAP_VIEW_BUNDLE_KEY ="100";
    MapView mapView;
    double data ,data2,data3;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("DevFest");
       // button= findViewById(R.id.button);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(MainActivity.this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    private void putMarker( double lat , double lang,Boolean b,String title,String snippet ) {

        LatLng ny = new LatLng(lat, lang);
        MarkerOptions markerOptions3   = new MarkerOptions();
        Marker marker;
        markerOptions3.position(ny).title(title).snippet(snippet).visible(true);


        if (b == true) {


            marker = gmap.addMarker(markerOptions3);

            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.redmarker));
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny, 5));

            marker.setVisible(true);


        }
        else
        {
            Toast.makeText(this, "تمت المساعدة ", Toast.LENGTH_SHORT).show();

        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

            gmap = googleMap;

            gmap.setMinZoomPreference(5);
            gmap.setIndoorEnabled(true);
            // settings to zoom and direct to google map
            UiSettings uiSettings = gmap.getUiSettings();
    uiSettings.setIndoorLevelPickerEnabled(true);
       // uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

            databaseReference.child("Button1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data1 : dataSnapshot.getChildren()) {

                        if (data1.exists()) {
                            data = data1.getValue(Integer.class);
                            LatLng ny = new LatLng(21.6309537, 39.1109132);
                            MarkerOptions markerOptions3   = new MarkerOptions();
                            Marker marker;

                            if (data !=0) {
                           //     putMarker(21.6309537, 39.1109132, true, "فرعة صحية", " \\t العمر: 33 \\t الإعاقة: جسدية\"");


                                markerOptions3.position(ny).title("فرعة صحية").snippet("الاعاقة : جسدية العمر : 33").visible(true);



                                    marker = gmap.addMarker(markerOptions3);

                                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.redmarker));
                                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny, 5));

                            } else {
                               // putMarker(21.6309537, 39.1109132, false, "", null);
                                Toast.makeText(MainActivity.this, "تمت المساعدة", Toast.LENGTH_SHORT).show();
                               // gmap.setM.clear();

                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });
            databaseReference.child("Button2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data1 : dataSnapshot.getChildren()) {

                        if (data1.exists()) {

                            data2 = data1.getValue(Integer.class);
//
                            LatLng ny = new LatLng(21.6317058, 39.1118466);
                            MarkerOptions markerOptions3   = new MarkerOptions();
                            Marker marker;
                            if (data2 != 0) {

                                markerOptions3.position(ny).title("فرعة صحية").snippet("الاعاقة : سمعية العمر : 33").visible(true);



                                marker = gmap.addMarker(markerOptions3);

                                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.redmarker));
                                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny,5));
                             //   putMarker(21.6317058, 39.1118466, true, "فزعة مرافقة", "   \\t العمر :  45 \\t الإعاقة: بصرية\" ");

                            } else {
                                // Toast.makeText(Map.this, ""+data2, Toast.LENGTH_SHORT).show();
                               // putMarker(21.6317058, 39.1118466, false, "", null);
                            }
                            Toast.makeText(MainActivity.this, "" + data2, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Error=" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });


            databaseReference.child("Button3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot data1 : dataSnapshot.getChildren()) {


                        String title = "مساعدة حرجة ";
                        String snippt = "   \tالعمر:45 \t الإعاقة: سمعية";
                        if (data1.exists()) {
                            data3 = data1.getValue(Integer.class);
                            LatLng ny = new LatLng(21.6294443, 39.1072870);
                            MarkerOptions markerOptions3   = new MarkerOptions();
                            Marker marker;
                            if (data3 != 0) {

                               // putMarker(21.6294443, 39.1072870, true, "فزعة معاملة", snippt);
                                markerOptions3.position(ny).title("فرعة صحية").snippet("الاعاقة : سمعية العمر : 33").visible(true);



                                marker = gmap.addMarker(markerOptions3);

                                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.redmarker));
                                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny, 5));

                            } else {
                             //   putMarker(21.6294443, 39.1072870, false, "", null);
                                markerOptions3.position(ny).title("فرعة صحية").snippet("الاعاقة : سمعية العمر : 33").visible(true);

                                marker = gmap.addMarker(markerOptions3);

                                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bluemarker));
                                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(ny, 5));
                            }
                            Toast.makeText(MainActivity.this, "" + data3, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(MainActivity.this, "Error=" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }
}
