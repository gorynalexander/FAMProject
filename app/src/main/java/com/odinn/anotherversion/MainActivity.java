package com.odinn.anotherversion;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
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
import com.odinn.anotherversion.adapter.SightListAdapter;
import com.odinn.anotherversion.adapter.TabsPagerFragmentAdapter;
import com.odinn.anotherversion.helper.Constants;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "GoxzL2XXIsGgdU2WMzlkG7U0O";
    private static final String TWITTER_SECRET = "v4ovPo3sDpkDITDP6b7K38pohx38S4HBl2CP19hTJs8evd4lyJ";


    private static final int LAYOUT = R.layout.activity_main;

    public static final int TAB_ONE = 0;
    public static final int RC_LOCATION_PERM = 0;


    private SharedPreferences mySharedPr;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private ViewPager viewPager;

    private Location myLocation;
    private LocationRequest mLocationRequest;
    private GoogleApiClient apiClient;
    private LatLng myLatLng;
    private TabsPagerFragmentAdapter tpAdapter;
    private SightListAdapter slAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();
        googleApiConnect();
    }

    private void googleApiConnect() {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) != ConnectionResult.SERVICE_MISSING) {
            apiClient = new GoogleApiClient.Builder(this, this, this)
                    .enableAutoManage(this, this)
                    .addApi(LocationServices.API)
                    .build();
            apiClient.connect();

        } else Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onPause() {
        if (apiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
        }
        super.onPause();
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

    public TabsPagerFragmentAdapter getTpAdapter() {
        return tpAdapter;
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tpAdapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tpAdapter);
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
                        makeAlert("Test", "Let's try!");
                        Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                        startActivity(intent);
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
                        makeAlert("Find&Mark", "Developer : Alexander Goryn \n e-mail : goryn.alexander@gmail.com");
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit()
                .putString(Constants.PREF_LATITUDE, "" + lat)
                .putString(Constants.PREF_LONGITUDE, "" + lng)
                .apply();
    }

    @Override
    public void onConnected(Bundle bundle) { // gps connect
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            if (myLocation != null) {
                handleNewLocation(myLocation);
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, mLocationRequest, this);
        } else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION_PERM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RC_LOCATION_PERM) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) onConnected(null);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(myLocation); // updating new location when it changed
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
