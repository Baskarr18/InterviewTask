package com.example.root.jsonparsingwithrealm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;


import com.example.root.jsonparsingwithrealm.model.CustomerDetailsPojo;
import com.example.root.jsonparsingwithrealm.utilities.CustomerAlertDialog;
import com.example.root.jsonparsingwithrealm.utilities.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;


/**
 * This cass displays the content of the Customer details through intent and
 * a google map with makers in it.
 */

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnInfoWindowClickListener, CustomerAlertDialog.AlertDialogListener {

    private TextView jsonDataTextView;
    private GoogleMap googleMap;
    private MapView mapView;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    public static final int PERMISSION_ALERT_REQUEST = 1;
    String intentData = "";
    String latString = "";
    String lanString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map_layout);
        jsonDataTextView = (TextView) findViewById(R.id.json_data_textView);
        mapView = (MapView) findViewById(R.id.map);
        Dexter.initialize(this);

        checkGPSPermission();

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {

            intentData = bundle.getString("array_list");
            latString = bundle.getString("lat");
            lanString = bundle.getString("lan");
            jsonDataTextView.setText(intentData);

        }

    }

    /**
     * This method is to check the GPS permission provided for your app
     */
    private void checkGPSPermission() {
        if (!Dexter.isRequestOngoing())
            Dexter.checkPermission(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {
                    intializeMap();
                }


                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                    CustomerAlertDialog.getInstance().showMessage(GoogleMapActivity.this, getString(R.string.app_name),
                            getString(R.string.permission_message_gps), getString(R.string.open_setting),
                            getString(R.string.cancel), PERMISSION_ALERT_REQUEST, GoogleMapActivity.this);

                }

                @Override
                public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {

                }

            }, android.Manifest.permission.ACCESS_FINE_LOCATION);
    }


    /**
     * Get the list of markers form th Array List values
     */
    private void markerOptions() {
        createMarker(Double.parseDouble(latString), Double.parseDouble(lanString));
    }


    /**
     * @param lat Lat variable is of doubl values and it specifies the latitude
     * @param lng lng variable os of double valus an it specifies the longitude
     */

    private void createMarker(double lat, double lng) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.a_current_location_home)));
    }


    private void intializeMap() {
        if (googleMap == null) {
            mapView.onCreate(null);
            mapView.onResume();// needed to get the map to display immediately
            try {
                MapsInitializer.initialize(this.getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mapView.getMapAsync(this);
        }
    }


    /**
     * @param googleMap is used to customise the map view
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this
                , android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMapToolbarEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        this.googleMap.setOnInfoWindowClickListener(this);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        if (GoogleMapActivity.this != null)
                            if (ActivityCompat.checkSelfPermission(GoogleMapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                        locationRequest = LocationRequest.create();
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        locationRequest.setInterval(10000);
                        locationRequest.setFastestInterval(10000 / 2);
                        //checkGps();

                        Location location = LocationServices.FusedLocationApi
                                .getLastLocation(mGoogleApiClient);
                        locationUpdate(location);
                        markerOptions();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                    }
                })
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();


    }

    private void locationUpdate(Location location) {
        if (location != null) {
            LatLng loc = new LatLng(Double.parseDouble(latString), Double.parseDouble(lanString));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 5.0f));
        }
    }


    /**
     * @param bundle this values
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    /**
     * @param request_code
     */
    @Override
    public void onPositiveClicked(int request_code) {
        if (request_code == PERMISSION_ALERT_REQUEST) {
            Utility.getInstance().startInstalledAppDetailsActivity(GoogleMapActivity.this);
        }

    }

    @Override
    public void onNegativeClicked(int request_code) {

    }
}
