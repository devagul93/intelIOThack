package com.example.parseexample;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PopupActivity extends Activity{

	List<ParseGeoPoint> geopoint = new ArrayList<ParseGeoPoint>();
	Button button_service;
	String make;
	MyReciever reciever;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		make = getIntent().getStringExtra("Make");
		setContentView(R.layout.activity_popup);
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ServiceCentres");
		query.whereEqualTo("Make", make	);
	/*	query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				geopoint.clear();
				for(ParseObject ob: arg0){
				ParseGeoPoint l =	ob.getParseGeoPoint("Location");
				
					Toast.makeText(getBaseContext(), ""+ ob.getParseGeoPoint("Location"), Toast.LENGTH_LONG).show();
				geopoint.add(l);
				}
				
				Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();
			}
		});
*/		
		button_service = (Button) findViewById(R.id.popupButton1);
		//this.registerReceiver(reciever, com.parse.push.intent.OPEN);
		button_service.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), MapsActivity.class);
				intent.putExtra("Make", make);
				//Bundle b = new Bundle();
				
				
			startActivity(intent);	
			}
		});
	}
	
	
	
	
}


