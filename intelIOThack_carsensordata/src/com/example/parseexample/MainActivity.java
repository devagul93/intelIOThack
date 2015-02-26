package com.example.parseexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	MyAdapter adapter;
	TextView waitmessage;
	List<ParseObject> vehicle_details = new ArrayList<ParseObject>();
	ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Parse.initialize(arg0, arg1, arg2)
        //Parse.initialize(this, "PXZkHdC1Ush056QJ2aV5dw7kKjyJW5EZhPPE26VA", "MjL0vJ925zerB5LIJYAVrf4s78FqGzWad6m4sUf8");
        setContentView(R.layout.activity_main);
        waitmessage = (TextView) findViewById(R.id.textview_waitmessage);
        lv = (ListView) findViewById(R.id.main_listview);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("VehicleDetails");
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
				vehicle_details = arg0;
				adapter = new MyAdapter(getBaseContext(), R.layout.activitymain_row, vehicle_details);
				lv.setAdapter(adapter);
				waitmessage.setVisibility(View.INVISIBLE);
				}
				else Toast.makeText(getBaseContext(), "error in connecting to the cloud", Toast.LENGTH_LONG).show();
			}
		});
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
			v = inflater.inflate(R.layout.activitymain_row, parent, false);
			
			ParseObject obj = vehicle_details.get(position);
			String m =obj.getString("Make");
			Date d = (Date) obj.get("MfgDate");
			String dat = d.toString();
			String car_model = (String) obj.get("Model");
			TextView model = (TextView) v.findViewById(R.id.text_settings_item);
			TextView make = (TextView) v.findViewById(R.id.textView1);
			TextView year = (TextView) v.findViewById(R.id.textView2);
			model.setText(car_model);
			year.setText(dat);
			make.setText(m);
			Toast.makeText(getBaseContext(), car_model, Toast.LENGTH_SHORT).show();
			return v;
		}
    	
    }
    
}
