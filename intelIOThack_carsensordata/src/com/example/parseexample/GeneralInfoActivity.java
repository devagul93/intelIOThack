package com.example.parseexample;

import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GeneralInfoActivity extends Activity{

	TextView tv1,tv2,tv3,tv4,tv5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "PXZkHdC1Ush056QJ2aV5dw7kKjyJW5EZhPPE26VA", "MjL0vJ925zerB5LIJYAVrf4s78FqGzWad6m4sUf8");
		setContentView(R.layout.activity_generalinfo);
		tv1 =(TextView) findViewById(R.id.generalinfovalue2);
		tv2 =(TextView) findViewById(R.id.generalinfovalue3);
		tv3 =(TextView) findViewById(R.id.generalinfovalue4);
		tv4 =(TextView) findViewById(R.id.generalinfovalue5);
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("VehicleDetails");
		query.whereEqualTo("Model", "City");
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
			ParseObject o = arg0.get(0);
			tv1.setText(o.getString("Make"));
			tv2.setText(o.getString("Model"));
			Date d = o.getCreatedAt();
			//tv3.setText(d.toString()+"");
			}
		});
	}
}
