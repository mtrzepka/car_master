package com.example.carmaster;

import java.util.ArrayList;
import java.util.HashMap;

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
	ArrayList<HashMap<String, String>> StationList;
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
            String title = ((TextView) view.findViewById(R.id.title)).getText().toString();
            String ddescription = ((TextView) view.findViewById(R.id.dDescription)).getText().toString();
            String cdescription = ((TextView) view.findViewById(R.id.cDescription)).getText().toString();
            String note = ((TextView) view.findViewById(R.id.notes)).getText().toString();
            String pubDate = ((TextView) view.findViewById(R.id.pubDate)).getText().toString();
            String link = ((TextView) view.findViewById(R.id.link)).getText().toString();
            String mf = ((TextView) view.findViewById(R.id.manufacturer)).getText().toString();
            // Starting new intent
            Intent in = new Intent(getApplicationContext(), SingleRecallDetailActivity.class);
//            in.putExtra(Title, title);
//            in.putExtra(dSummary, ddescription);
//            in.putExtra(Date, pubDate);
//            in.putExtra(cSummary, cdescription);
//            in.putExtra(Notes, note);
//            in.putExtra(Link, link);
//            in.putExtra(Manufacturer,mf);
            startActivity(in);

        }
    });
    new GetRecalls().execute();
}
private class GetRecalls extends AsyncTask<Void, Void, Void> {
	 
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
		Double latitude =b.getDouble("lat");
		Double longtitude = b.getDouble("longt");

		System.out.println("1. "+latitude+"2. "+longtitude);
			URL = "http://api.mygasfeed.com/stations/radius/"+latitude+"/"+longtitude+"/50/reg/distance/ldwg5thsao.json?";
		System.out.println(URL);
        // Making a request to url and getting response
        String js = sh.makeHttpCall(URL, HttpHandler.GET);

        Log.d("Response: ", "> " + js);

        if (js != null) {
            try {
                JSONObject jsonObj = new JSONObject(js);
                JSONArray jo = jsonObj.getJSONArray(station); 
                // looping through All Contacts
                for (int i = 0; i < jo.length(); i++) {
                    JSONObject jo1 = jo.getJSONObject(i);
                     
                    String rp = jo1.getString(rprice);
                    String mp = jo1.getString(midprice);
                    String pp = jo1.getString(preprice);
                    String rd = jo1.getString(regDate);
                    String add = jo1.getString(Address);
                    String sn = jo1.getString(StationN);
                    String s = jo1.getString(State);
                    String c = jo1.getString(City);
                    String dist = jo1.getString(Distance);
                    
                    
                    // tmp hashmap for single contact
                    HashMap<String, String> station = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    station.put(rprice, rp);
                    station.put(midprice, mp);
                    station.put(preprice, pp);
                    station.put(regDate, rd);
                    station.put(Address, add);
                    station.put(StationN, sn);
                    station.put(State, s);
                    station.put(City, c);
                    station.put(Distance, dist);
                    // adding contact to contact list
                    StationList.add(station);
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
                R.layout.station_list_view, new String[] { rprice,midprice,preprice,regDate,Address,StationN,State,City,Distance }, new int[] {
                        R.id.regP, R.id.midP, R.id.premP,R.id.regD,R.id.address,R.id.Station,R.id.state,R.id.city,R.id.distance });

        setListAdapter(adapter);
    }


        }
}



