package com.example.carmaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GetStationWithPrices extends ListActivity{
	String URL = "";
	GPSTracker GPS;
	static final String station ="stations";
	static final String rprice ="reg_price";
	static final String midprice ="mid_price";
	static final String preprice ="pre_price";
	static final String regDate ="reg_date";
	static final String Address="address";
	static final String StationN="station";
	static final String State ="region";
	static final String City="city";
	static final String Distance="distance";
	static final String Latitude="lat";
	static final String Longtitude="lng";
	static final String mLatitude="lat";
	static final String mLongtitude="lng";
	ArrayList<HashMap<String, String>> StationList;
	ArrayList<HashMap<String, Double>> priceList;
	private ProgressDialog pD;
	AlertDialog.Builder alt_bld;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list_view);
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	StrictMode.setThreadPolicy(policy);
	StationList = new ArrayList<HashMap<String, String>>();
	ListView rlv=getListView();
	 
   
    rlv.setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            // getting values from selected ListItem
        	String lati = (String) ((TextView) view.findViewById(R.id.lat)).getText();
        	String longti = (String) ((TextView) view.findViewById(R.id.lng)).getText();
        	String sN = (String) ((TextView) view.findViewById(R.id.sName)).getText();
            // Starting new intent
            Intent in = new Intent(getApplicationContext(), MapActivityMain.class);
            in.putExtra(Latitude, lati);
            in.putExtra(Longtitude, longti);
            in.putExtra(StationN, sN);

//			 
//            // check if GPS enabled     
//            if(GPS.canGetLocation()){
//                 
//                double latitude = GPS.getLatitude();
//                double longitude = GPS.getLongitude();
//                Bundle a= new Bundle();
//				a.putDouble("lat", latitude);
//				in.putExtras(a);
//				Bundle b= new Bundle();
//				b.putDouble("longt", longitude);
//				in.putExtras(b);
//				System.out.println("lat: "+latitude+"long: "+longitude);
//            }else{
//                // can't get location
//                // GPS or Network is not enabled
//                // Ask user to enable GPS/network in settings
//                GPS.showSettingsAlert();
//            }
            
            startActivity(in);

        }
    });
    new GetStations().execute();
}
private class GetStations extends AsyncTask<Void, Void, Void> {
	 
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pD = new ProgressDialog(GetStationWithPrices.this);
        pD.setMessage("Loading...");
        pD.setCancelable(false);
        pD.show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        HttpHandler sh = new HttpHandler();
		Bundle b=getIntent().getExtras();
		Double latitude1 =b.getDouble("lat");
		Double longtitude1 = b.getDouble("longt");
        
		System.out.println("1. "+latitude1+"2. "+longtitude1);
			URL = "http://api.mygasfeed.com/stations/radius/"+latitude1+"/"+longtitude1+"/50/reg/distance/ldwg5thsao.json?";
		System.out.println(URL);
        // Making a request to url and getting response
        String js = sh.makeHttpCall(URL, HttpHandler.GET);

        Log.d("Response: ", "> " + js);

        if (js != null) {
            try {
                JSONObject jsonObj = new JSONObject(js);
                JSONObject jsonO = jsonObj.getJSONObject("geoLocation");
                String latit = jsonO.getString(mLatitude);
                String longtit = jsonO.getString(mLongtitude);
                System.out.println(latit+">>>>>>"+longtit);
                JSONArray jo = jsonObj.getJSONArray(station); 
                // looping through All Contacts
                for (int i = 0; i < jo.length(); i++) {
                    JSONObject jo1 = jo.getJSONObject(i);
                     
                    String rp1 = jo1.getString(rprice);
                    String mp = jo1.getString(midprice);
                    String pp = jo1.getString(preprice);
                    String rd = jo1.getString(regDate);
                    String add = jo1.getString(Address);
                    String sn = jo1.getString(StationN);
                    String s = jo1.getString(State);
                    String c = jo1.getString(City);
                    String dist = jo1.getString(Distance);
                    String lt = jo1.getString(Latitude);
                    String lg = jo1.getString(Longtitude);
                    // tmp hashmap for single contact
                   // System.out.println(lt+">>>station location>>>"+lg);
                    HashMap<String, String> station = new HashMap<String, String>();

                    // adding each child node to HashMap key => value

                    station.put(rprice, rp1);
                    station.put(midprice, mp);
                    station.put(preprice, pp);
                    station.put(regDate, rd);
                    station.put(Address, add);
                    station.put(StationN, sn);
                    station.put(State, s);
                    station.put(City, c);
                    station.put(Distance, dist);
                    station.put(Latitude, lt);
                    station.put(Longtitude, lg);
                    // adding contact to contact list
                    StationList.add(station);
                    //priceList.add(price);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "cannot get data from the API");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pD.isShowing())
            pD.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */

                	ListAdapter adapter = new SimpleAdapter(
                GetStationWithPrices.this, StationList,
                R.layout.station_list_view, new String[] { rprice,midprice,preprice,regDate,Address,StationN,State,City,Distance,Latitude,Longtitude }, new int[] {
                        R.id.regP, R.id.midP, R.id.premP,R.id.regD,R.id.address,R.id.Station,R.id.state,R.id.city,R.id.distance,R.id.lat,R.id.lng });

        setListAdapter(adapter);
    }


        }
}



