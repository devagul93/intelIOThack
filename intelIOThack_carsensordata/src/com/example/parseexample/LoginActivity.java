package com.example.parseexample;

import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class LoginActivity extends Activity{

	ImageButton imagebutton_enter;
	IntentFilter filter = new IntentFilter();
	MyReciever reciever = new MyReciever();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/************initialization of parse components***************************************************/
		
		Parse.initialize(this, "PXZkHdC1Ush056QJ2aV5dw7kKjyJW5EZhPPE26VA", "MjL0vJ925zerB5LIJYAVrf4s78FqGzWad6m4sUf8");
		
		ParsePush.subscribeInBackground("", new SaveCallback() {
      	  @Override
      	  public void done(com.parse.ParseException e) {
      	    if (e == null) {
      	      Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
      	    } else {
      	      Log.e("com.parse.push", "failed to subscribe for push", e);
      	    }
      	  }

		
      	});
		
	
		/*****************************oncreate activity initializations************************************/
		setContentView(R.layout.activity_login);
		imagebutton_enter = (ImageButton) findViewById(R.id.imageButton1);
		imagebutton_enter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(), MainActivity.class));
			}
		});
		
		/*filter.addAction("com.parse.push.intent.RECEIVE");
		filter.addAction("com.parse.push.intent.DELETE");
		filter.addAction("com.parse.push.intent.OPEN");
		getApplicationContext().registerReceiver(reciever, filter);*/
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		filter.addAction("com.parse.push.intent.RECEIVE");
		filter.addAction("com.parse.push.intent.DELETE");
		filter.addAction("com.parse.push.intent.OPEN");
		this.registerReceiver(reciever, filter);
	}
}
