package com.example.parseexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.parseexample.MainActivity.MyAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentDataActivity extends Activity{

	
	MyAdapter adapter;
	TextView waitmessage;
	List<ParseObject> vehicle_details = new ArrayList<ParseObject>();
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currentdata_detailsactivity);
		  waitmessage = (TextView) findViewById(R.id.textview_waitmessage);
	        lv = (ListView) findViewById(R.id.currentdata_list);
	        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("SensorValues");
	        query.findInBackground(new FindCallback<ParseObject>() {
				
				@Override
				public void done(List<ParseObject> arg0, ParseException arg1) {
					// TODO Auto-generated method stub
					if(arg1==null){
						ParseObject obj = arg0.get(0);
						TextView battvolt = (TextView) findViewById(R.id.textVoltagevalue);
						TextView enginerpm = (TextView) findViewById(R.id.textEnginerpmvalue);
						TextView date = (TextView) findViewById(R.id.dat);
						TextView vehiclespeed = (TextView) findViewById(R.id.textVehiclespeedvalue);
						int one =obj.getInt("BatteryVoltage");
						int two = obj.getInt("EngineRpm");
						int three =obj.getInt("Speed");
						Date dat = obj.getCreatedAt();
						String creatdat = dat.toString();
						battvolt.setText(""+one);
						enginerpm.setText(""+two);
						vehiclespeed.setText(""+three);
						date.setText(creatdat);
					}
					else Toast.makeText(getBaseContext(), "error in connecting to the cloud", Toast.LENGTH_LONG).show();
				}
			});
	        
	    /*    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
				public void onItemClick(AdapterView<?> arg0, View v, int position,
						long arg3) {
					// TODO Auto-generated method stub
					ParseObject temp = vehicle_details.get(position);
				int tempnumber =	(Integer) temp.get("SensorId");
					Intent intent = new Intent(getBaseContext(), CarDetailsActivity.class);
					intent.putExtra("SensorId",tempnumber);
					startActivity(intent);
				
				}
			});
	    */    
	        
	        
	        
	     /*   ParseObject po = new ParseObject("hello");
	        po.put("key1", "value1");
	        po.put("key2", "value2");
	        int val3 = 56;
	        po.put("key2", val3);
	        po.saveInBackground(new SaveCallback() {
				
				@Override
				public void done(ParseException arg0) {
					// TODO Auto-generated method stub
				if(arg0==null){
					Toast.makeText(getBaseContext(), "success", Toast.LENGTH_SHORT).show();
				}
				}
			});
	        
	*/
	       /* ParsePush.subscribeInBackground("", new SaveCallback() {
	        	  @Override
	        	  public void done(ParseException e) {
	        	    if (e == null) {
	        	      Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
	        	    } else {
	        	      Log.e("com.parse.push", "failed to subscribe for push", e);
	        	    }
	        	  }
	        	});*/
	        
	  /*      Button b = (Button)findViewById(R.id.button_maps);
	        b.setText("open maps");
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(getBaseContext(), MapsActivity.class));
				}
			});*/
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    /***********************************Adapter Class*********************************************************/
	    public class MyAdapter extends ArrayAdapter<ParseObject>{

	    	Context context;
			public MyAdapter(Context context, int resource,
					List<ParseObject> objects) {
				super(context, resource, objects);
				// TODO Auto-generated constructor stub
				this.context = context;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				Log.d("count", " "+super.getCount());
				return super.getCount();
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View v;
				LayoutInflater inflater = LayoutInflater.from(context);
				v = inflater.inflate(R.layout.currentdata_detailsactivity, parent, false);
				
				/*ParseObject obj = vehicle_details.get(position);
				TextView battvolt = (TextView) v.findViewById(R.id.textVoltagevalue);
				TextView enginerpm = (TextView) v.findViewById(R.id.textEnginerpmvalue);
				TextView date = (TextView) v.findViewById(R.id.dat);
				TextView vehiclespeed = (TextView) v.findViewById(R.id.textVehiclespeedvalue);
				int one =obj.getInt("BatteryVoltage");
				int two = obj.getInt("EngineRpm");
				int three =obj.getInt("Speed");
				Date dat = obj.getCreatedAt();
				String creatdat = dat.toString();
				battvolt.setText(""+one);
				enginerpm.setText(""+two);
				vehiclespeed.setText(""+three);
				date.setText(creatdat);*/
				/*String car_model = (String) obj.get("Model");
				TextView make = (TextView) v.findViewById(R.id.text_settings_item);
				make.setText(car_model);
				Toast.makeText(getBaseContext(), car_model, Toast.LENGTH_SHORT).show();*/
				return v;
			}
	    	
	    }

		
		
	
}
