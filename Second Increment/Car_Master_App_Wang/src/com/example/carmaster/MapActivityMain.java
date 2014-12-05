package com.example.carmaster;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MapActivityMain extends FragmentActivity {
	static final String Latitude ="lat";
	static final String Longtitude="lng";
	static final String mLatitude ="lat";
	static final String mLongtitude="lng";
	static final String StationN="station";
	GoogleMap mMap;
    GMapV2Direction md;
    GPSTracker GPS;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GPS = new GPSTracker(MapActivityMain.this);
		 
       // check if GPS enabled     
       
            
           double latitude = GPS.getLatitude();
           double longitude = GPS.getLongitude();
			System.out.println("lat: "+latitude+"long: "+longitude);
       
           // can't get location
           // GPS or Network is not enabled
           // Ask user to enable GPS/network in settings
           
       
    	
    	
    	Intent in = getIntent();
    	String lati = in.getStringExtra(Latitude);
    	String longt = in.getStringExtra(Longtitude);
    	String sName = in.getStringExtra(StationN);
    	double la = Double.parseDouble(lati);
    	double longti = Double.parseDouble(longt);
    	LatLng fromPosition = new LatLng(latitude, longitude);
    	LatLng toPosition = new LatLng(la, longti);
        System.out.println(latitude+">>>>>"+longitude+",,,,,,"+la+"......."+longti);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        md = new GMapV2Direction();
		mMap = ((SupportMapFragment)getSupportFragmentManager()
						.findFragmentById(R.id.map)).getMap();

		LatLng coordinates = new LatLng(latitude, longitude);		
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14));
		
		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Your Location"));
		mMap.addMarker(new MarkerOptions().position(toPosition).title("Station"));
		
		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
//		int duration = md.getDurationValue(doc);
//		String distance = md.getDistanceText(doc);
//		String start_address = md.getStartAddress(doc);
//		String dist = md.getEndAddress(doc);
//		String copy_right = md.getCopyRights(doc);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+start_address+" to "+dist);

		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.RED);
		
		for(int i = 0 ; i < directionPoint.size() ; i++) {			
			rectLine.add(directionPoint.get(i));
		}
		
		mMap.addPolyline(rectLine);
    }
}


