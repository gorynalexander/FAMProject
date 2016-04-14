package com.odinn.anotherversion;

import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.odinn.anotherversion.adapter.TabsPagerFragmentAdapter;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private static final int LAYOUT = R.layout.activity_main;

    public static final int TAB_ONE = 0;


    private SharedPreferences mySharedPr;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private ViewPager viewPager;

    private Location myLocation;
    private LocationRequest mLocationRequest;
    private GoogleApiClient apiClient;
    private LatLng myLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();

        googleApiConnect();
    }

    private void googleApiConnect() {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            apiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            apiClient.connect();

        } else Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        apiClient.connect();
        //   handleNewLocation(myLocation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (apiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    apiClient, this);
            apiClient.disconnect();
        }
    }

    public LatLng handleNewLocation(Location myLocation) {
        try {
            myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
       //     Toast.makeText(this, "" + myLatLng.toString(), Toast.LENGTH_SHORT).show();

        } catch (NullPointerException e) {
            Toast.makeText(this, "Null" + myLatLng.toString(), Toast.LENGTH_SHORT).show();
        }
        return myLatLng;

    }


    private void initTabs() {


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_sights:
                        showNotificationTab();
                        break;
                    case R.id.nav_add_new:
                        makeAlert("SORRY", "It will be working soon");
                        break;
                    case R.id.nav_achieve:
                        makeAlert("SORRY", "It will be working soon");
                        break;
                    case R.id.nav_set:
                        makeAlert("SORRY", "It will be working soon");
                        break;
                    case R.id.nav_change_acc:
                        makeAlert("SORRY", "It will be working soon");
                        break;
                    case R.id.nav_about:
                        makeAlert("Find&Mark", "Developer : Alexander Goryn");
                        break;
                    case R.id.nav_share:
                        makeAlert("SORRY", "It will be working soon");
                        break;

                }

                return true;
            }

            private void makeAlert(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }

                                });
                AlertDialog alert = builder.create();
                alert.show();
            }


        });
    }


    private void showNotificationTab() {
        viewPager.setCurrentItem(TAB_ONE);
    }

    public void savePref(double lat, double lng) {
        //SightListAdapter sla = new SightListAdapter(null);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit()
                .putString(Constants.PREF_LATITUDE, "" + lat)
                .putString(Constants.PREF_LONGITUDE, "" + lng)
                .apply();
    }

    @Override
    public void onConnected(Bundle bundle) { // gps connect
        myLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        if (myLocation != null) {
            handleNewLocation(myLocation);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(myLocation); // updating new location when it changed
     //   Toast.makeText(this, myLatLng.toString(), Toast.LENGTH_SHORT).show();
         savePref(myLocation.getLatitude(), myLocation.getLongitude());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 90000);
            } catch (IntentSender.SendIntentException e) {
                Log.i("COORDINATE", "SendIntentException");
                e.printStackTrace();
            }
        } else {
            Log.i("COORDINATE", "Location services connection failed with code " + connectionResult.getErrorCode());
            Toast.makeText(this, "Connection failed with code " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
