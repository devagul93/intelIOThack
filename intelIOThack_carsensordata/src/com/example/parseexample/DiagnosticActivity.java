package com.example.parseexample;

import java.util.List;

import com.example.parseexample.CurrentDataActivity.MyAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DiagnosticActivity extends Activity{

	TextView health, weakest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currentdata_diagnosticactivity);
		health = (TextView) findViewById(R.id.textCarhealthvalue);
		weakest = (TextView) findViewById(R.id.textweakvalue);
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("SensorValues");
		
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
				ParseObject o = arg0.get(0);
				int h =  o.getInt("Rating");
				int per = h*10;
				String weak = o.getString("WeakestSegment");
				health.setText(per+""+"%");
				weakest.setText(weak);
				}
				else Toast.makeText(getBaseContext(), "error in connecting to the cloud", Toast.LENGTH_LONG).show();
			}
		});
	}
}
