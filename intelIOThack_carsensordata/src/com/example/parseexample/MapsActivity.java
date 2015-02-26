package com.example.parseexample;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MapsActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, OnMapReadyCallback{

	List<ParseGeoPoint> geopoint = new ArrayList<ParseGeoPoint>();
	GoogleMap map;
	double lat;
	double lon;
	String make;
	Location mLastLocation;
	GoogleApiClient mGoogleApiClient;
	//protected TextView mLatitudeText;
	//protected TextView mLongitudeText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		make = getIntent().getStringExtra("Make");
	//	this.geopoint=new PopupActivity().geopoint;
		
		//mLatitudeText = (TextView) findViewById(R.id.latitude_text);
		//mLongitudeText = (TextView) findViewById(R.id.longitude_text);
		buildGoogleApiClient();
		//map.setMyLocationEnabled(true);
		MapFragment mapFragment = (MapFragment) getFragmentManager()
			    .findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);

	}
	
	protected synchronized void buildGoogleApiClient() {
	    mGoogleApiClient = new GoogleApiClient.Builder(this)
	        .addConnectionCallbacks(this)
	        .addOnConnectionFailedListener(this)
	        .addApi(LocationServices.API)
	        .build();
	}
	
	public void onDisconnected() {
		Log.i("tag", "Disconnected");
		}
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		System.out.print(""+mLastLocation.getLatitude()+"  "+mLastLocation.getLongitude());
       if (mLastLocation != null) {
    	   lat =  mLastLocation.getLatitude();
    	   lon =  mLastLocation.getLongitude();
    	   Toast.makeText(getBaseContext(), ""+lat +" "+lon, Toast.LENGTH_LONG).show();
          //  mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
           // mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            }
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		Log.i("tag", "Connection suspended");
		mGoogleApiClient.connect();
	}
	@Override
	protected void onStart() {
	super.onStart();
	if(!mGoogleApiClient.isConnected())
	mGoogleApiClient.connect();
	}
	@Override
	protected void onStop() {
	super.onStop();
	if (mGoogleApiClient.isConnected()) {
	mGoogleApiClient.disconnect();
	}
	}

	@Override
	public void onMapReady(GoogleMap map) {
		// TODO Auto-generated method stub
		//
		this.map =map;
		map.setMyLocationEnabled(true);
		/*for(ParseGeoPoint g:geopoint ){
			map.addMarker(new MarkerOptions().position(new LatLng(g.getLatitude(), g.getLongitude())));
		}*/
		map.addMarker(new MarkerOptions().position(new LatLng(13, 77.5)));
	}

}
