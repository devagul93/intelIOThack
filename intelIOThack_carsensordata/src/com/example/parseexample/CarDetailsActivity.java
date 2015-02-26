package com.example.parseexample;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CarDetailsActivity extends Activity{

	Button currentdata, log, diagnostic, care, help;
	String make;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardetails);
		currentdata = (Button) findViewById(R.id.organiserButtons);
		log = (Button) findViewById(R.id.logButtons);
		diagnostic = (Button) findViewById(R.id.creditsButton);
		help = (Button) findViewById(R.id.HelpButton);
		help.setText("About");
	//	generalinfo.setVisibility(View.GONE);
		log.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(getBaseContext(), LogActivity.class));	
			}
		});
		
		help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(getBaseContext(), AboutActivity.class));	
			}
		});
		
		currentdata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), CurrentDataActivity.class);
			startActivity(intent);	
			}
		});
		diagnostic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), DiagnosticActivity.class);
				startActivity(intent);	
			}
		});
		
		
		
		care = (Button) findViewById(R.id.careButton);
		care.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), PopupActivity.class);
				intent.putExtra("Make", make);
				startActivity(intent);	
			}
		});
		
		
				
		
		
		
		int sensorid =getIntent().getIntExtra("SensorId", 101);
		ParseQuery<ParseObject> query= new ParseQuery<ParseObject>("VehicleDetails");
		query.whereEqualTo("SensorId", sensorid);
		query.findInBackground(new FindCallback<ParseObject>() {

			
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null)
				{
					ParseObject tesst = arg0.get(0);
					 make = (String) tesst.get("Make");
					Toast.makeText(getBaseContext(), "success"+ make, Toast.LENGTH_SHORT).show();
					
				}else {Toast.makeText(getBaseContext(), "fail", Toast.LENGTH_LONG).show();}
			}
		});
		
		
		Toast.makeText(this, ""+sensorid, Toast.LENGTH_LONG).show();
		
		 
	}
	
}
