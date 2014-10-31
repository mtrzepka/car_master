package com.example.carmaster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button button,button1,button2,button3,button4;
	GPSTracker GPS;
	String Latitude="";
	String Longtitude="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		button = (Button) findViewById(R.id.button2);
		button1 = (Button) findViewById(R.id.button5);

		button.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, CarRecallActivity.class);
                            startActivity(intent);   
 
			}
 
		});
		button1.setOnClickListener(new OnClickListener() {
			 
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
	}
 
}