package com.example.hello.shooteralert;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    public GoogleMap mMap;
    public Button inDangerButton;
    public int location_permission;
    FusedLocationProviderClient mFusedLocationClient;
    double current_latitude;
    double current_longtitude;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String user_id;
    String user_name;
    Uri user_avatar;
    ArrayList<User> user_list;
    User current_user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        inDangerButton = findViewById(R.id.inDangerButton);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, location_permission);
        }

        //Access user's information
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user!= null) {
            user_id = user.getUid();
            user_name = user.getDisplayName();
            user_avatar = user.getPhotoUrl();

        }
        //Access database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user_list = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Log.e("current usera", "onDataChange: "+current_user );
                    user_list.add(ds.getValue(User.class));
                    Log.e("get out of here", "onDataChange: "+"duyyyyyyyyy" );
                }
                updateLocationUI();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("On cancelled", "Failed to read value.", error.toException());
            }
        });


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
        LatLng ll = new LatLng(current_latitude, current_longtitude);
        mMap.addMarker(new MarkerOptions().title(user_name).position(ll));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));

    }

    public User writeNewUser(String id, String name, Uri avatar_uri, double latitude, double longtitude){
        String avatar_uri_string = "";
        if (avatar_uri != null){
            avatar_uri_string = avatar_uri.toString();
        }
        User current_user = new User();
        current_user.setUser_name(name);
        current_user.setUser_avatar_URI(avatar_uri_string);
        current_user.setLatitude(latitude);
        current_user.setLongtitude(longtitude);
        myRef.child(id).setValue(current_user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference reference) {
                if (databaseError != null) {
                    Log.e("Error:", "Failed to write message", databaseError.toException());
                }
            }
        });
        return current_user;
    }
    public void inDangerButton(View inDangerButton){
        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  }, location_permission);
        };
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                            current_latitude = location.getLatitude();
                            current_longtitude = location.getAltitude();
                        }
                    }
                });
        current_user = writeNewUser(user_id, user_name, user_avatar, current_latitude, current_longtitude);
    };
    public void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        for (int i =0; i<user_list.size(); i++){
            Log.e("helloo", "updateLocationUI: "+user_list);
            LatLng ll = new LatLng(user_list.get(i).getLatitude(), user_list.get(i).getLongtitude());
            mMap.addMarker(new MarkerOptions().position(ll).title(user_list.get(i).getUser_name()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
        };
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
}
