package com.example.parseexample;

import com.parse.Parse;

import android.app.Application;

public class App extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.enableLocalDatastore(this);
		 
//		Parse.initialize(this, "8ktVHBCkTTaoyU0Jw3mq1PLejpCQY9jWrhFq0Jib", "rRs46muOMoYRF0J0FTGO4e7LGcvBiCc6dg2op1T5");
	}

	

	
}
