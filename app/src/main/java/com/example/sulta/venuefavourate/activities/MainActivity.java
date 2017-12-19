package com.example.sulta.venuefavourate.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sulta.venuefavourate.R;
import com.example.sulta.venuefavourate.Utils.Utils;
import com.example.sulta.venuefavourate.adapters.VenuewInfoAdapter;
import com.example.sulta.venuefavourate.interfaces.APIRequestHandler;
import com.example.sulta.venuefavourate.interfaces.ComonInterface;
import com.example.sulta.venuefavourate.interfaces.ItemClik;
import com.example.sulta.venuefavourate.models.VenuewEntity;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity  implements ComonInterface,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener,ItemClik{
    RecyclerView rlVenueInfo;
    VenuewInfoAdapter venueAdapter;
    ArrayList<VenuewEntity> allVenuewInfo;

    /*** Integrating Fusion API in This One ****/
    GoogleApiClient mGooAPIClient;
    LocationRequest mLocationRequest;
    public boolean mLocConnected = false;
    public Location mCurLocation;

    //public double curLat=41.013;
    public double curLat;
    //public double curLong=-73.4976034;
    public double curLong;
    public static final int REQ_PLAY_SERVICES=12;
    public static final int REQ_LOC_PERMISSION=13;
    public String url="https://movesync-qa.dcsg.com/dsglabs/mobile/api/venue/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initilizeViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (isPlayServiceAvailable()) {
            GoogleClientBuild();

        }
        createLocRequest();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mGooAPIClient !=null){
            mGooAPIClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getMyVenuewInfo();

    }

    private boolean isPlayServiceAvailable() {
        int verInfo = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        if(verInfo == ConnectionResult.SUCCESS){
            if(GoogleApiAvailability.getInstance().isUserResolvableError(verInfo)){
                GoogleApiAvailability.getInstance().showErrorDialogFragment(this,1,verInfo);
            }else{
                //Toast.makeText(getApplicationContext(),"Device not support Play service",Toast.LENGTH_SHORT).show();
                return  true;
            }
        }else{
            Toast.makeText(getApplicationContext(),"Device not support Play service",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private void getMyVenuewInfo() {
        APIRequestHandler.getInstance().getMyVenuewInfo(this);
    }

    private void initilizeViews() {
        allVenuewInfo = new ArrayList<>();
        rlVenueInfo = (RecyclerView) findViewById(R.id.rlVenuewInfo);

        venueAdapter = new VenuewInfoAdapter(getApplicationContext(),R.layout.custom_venuew_info,allVenuewInfo,this);
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        rlVenueInfo.setLayoutManager(lManager);
        rlVenueInfo.setHasFixedSize(true);
        rlVenueInfo.setAdapter(venueAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestSuccess(Object responseObj) {

        if(responseObj != null){
            if(responseObj instanceof VenuewEntity){
                VenuewEntity allVenues = (VenuewEntity) responseObj;

                allVenuewInfo.clear();
                allVenuewInfo.addAll(allVenues.getVenues());

               calcDistance();
            }
        }
    }

    private void calcDistance() {

        for(VenuewEntity eachVenuew : allVenuewInfo){
            if(eachVenuew.getLocation() !=null ){
                double venueLat = eachVenuew.getLocation().getLatitude();
                double venueLong = eachVenuew.getLocation().getLongitude();


                   float currDist=  Utils.GetDistancee(curLat,curLong,venueLat,venueLong);
                    eachVenuew.setDistance(currDist);


            }
        }
        Collections.sort(allVenuewInfo, new Comparator<VenuewEntity>() {
            @Override
            public int compare(VenuewEntity o1, VenuewEntity o2) {

                return o1.getDistance() < o2.getDistance() ? -1
                        : o1.getDistance() > o2.getDistance() ? 1
                        : 0;
            }
        });

        venueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailure(RetrofitError errorCode) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(mCurLocation ==null){
            getMyLocation();
        }

    }

    private void getMyLocation() {

            int checkSelf= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            if(checkSelf!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                    Toast.makeText(getApplicationContext(),"Make Sure to give Permission to Access This page",Toast.LENGTH_LONG).show();
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE},REQ_LOC_PERMISSION);
                }
            }else{

                mCurLocation = LocationServices.FusedLocationApi.getLastLocation(mGooAPIClient);

              if(mCurLocation != null) {
                  curLat = mCurLocation.getLatitude();
                  curLong = mCurLocation.getLongitude();
                  getMyVenuewInfo();

              }

            }


    }

    @Override
    public void onConnectionSuspended(int i) {
        mGooAPIClient.connect();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location !=null){
            mCurLocation = location;
            getMyLocation();

        }

    }

    @Override
    public void onItemLongClick(View view, Object object) {

    }

    @Override
    public void onItemClick(View view, int position) {
        VenuewEntity currVenue= allVenuewInfo.get(position);
     switch (view.getId()){
         case R.id.rlTotView:
             //navigate to another page.
             Intent mselceted = new Intent(getApplicationContext(),SelVenueActivity.class);
             Bundle venueBundle = new Bundle();
             venueBundle.putSerializable("VenuewInfo", currVenue);
             mselceted.putExtras(venueBundle);
             startActivity(mselceted);
             break;
         case R.id.imgFavuorate:
               /* Collections.sort(allVenuewInfo, new Comparator<VenuewEntity>() {
                    @Override
                    public int compare(VenuewEntity o1, VenuewEntity o2) {

                        return Boolean.compare(o1.isFavourate(),o2.isFavourate());
                    }
                });*/

               boolean myValue  = currVenue.isFavourate();
                    int currIndex = getIndexOf(allVenuewInfo,myValue);
                if(currIndex !=-1){
                    allVenuewInfo.remove(allVenuewInfo.indexOf(currVenue));
                    allVenuewInfo.add(0,currVenue);
                    venueAdapter.sel_item_pos =0;
                }


             venueAdapter.notifyDataSetChanged();
             break;
     }
    }

    private int getIndexOf(ArrayList<VenuewEntity> allVenuewInfo, boolean myValue) {
        for(VenuewEntity currVenue : allVenuewInfo){
            if(currVenue.isFavourate() == myValue){
                return allVenuewInfo.indexOf(currVenue);
            }else
                continue;
        }
        return -1;
    }


    private synchronized void GoogleClientBuild() {
        mGooAPIClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addApi(AppIndex.API).
                addOnConnectionFailedListener(this).build();


    }
    public void createLocRequest() {
        mLocationRequest = new LocationRequest().setInterval(10000).
                setFastestInterval(5000).
                setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY).
                setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).
                setSmallestDisplacement(500);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //if(requestCode == REQ_LOC_PERMISSION && )
        switch (requestCode){
            case REQ_LOC_PERMISSION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   getMyLocation();
                }else{
                    Toast.makeText(getApplicationContext(),"Give Permission to Access Location",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
