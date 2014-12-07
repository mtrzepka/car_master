package com.example.carmaster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	ImageButton recallB,infoB,priceB,ServicesB,GSationB;
	GPSTracker GPS;
	String Latitude="";
	String Longtitude="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.rl);
		rl.setBackgroundColor(Color.WHITE);
		addListenerOnButton();
	}
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		recallB = (ImageButton) findViewById(R.id.imageButton4);
		GSationB = (ImageButton) findViewById(R.id.imageButton5);

		recallB.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, CarRecallActivity.class);
                            startActivity(intent);   
 
			}
 
		});
		GSationB.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				GPS = new GPSTracker(MainActivity.this);
				 
                // check if GPS enabled     
                if(GPS.canGetLocation()){
                     
                    double latitude = GPS.getLatitude();
                    double longitude = GPS.getLongitude();
                    Intent intent = new Intent(context, GetStationWithPrices.class);
                    Bundle a= new Bundle();
    				a.putDouble("lat", latitude);
    				intent.putExtras(a);
    				Bundle b= new Bundle();
    				b.putDouble("longt", longitude);
    				intent.putExtras(b);
    				System.out.println("lat: "+latitude+"long: "+longitude);
                    startActivity(intent); 
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    GPS.showSettingsAlert();
                }
  
 
			}
 
		});
		priceB = (ImageButton) findViewById(R.id.imageButton1);
		priceB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, CarValueActivity.class);
                startActivity(intent);   
			}
		});
		infoB = (ImageButton) findViewById(R.id.imageButton2);
		infoB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, CarDetailActivity.class);
                startActivity(intent);   
			}
		});

	}
 
}