package com.example.carmaster;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RecallListActivity extends ListActivity{
		String URL = "";
		// 5 vaules we will parsing to our app
		static final String Recall ="success";
		static final String Title ="recall_subject";
		static final String dSummary ="defect_summary";
		static final String cSummary ="consequence_summary";
		static final String Notes ="notes";
		static final String Link="recall_url";
		static final String Date="recall_date";
		static final String Manufacturer ="manufacturer";
		static final String num="total";
		JSONArray recalls=null;
		ArrayList<HashMap<String, String>> recallRList;
		private ProgressDialog pD;
		AlertDialog.Builder alt_bld;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		recallRList = new ArrayList<HashMap<String, String>>();
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
                in.putExtra(Title, title);
                in.putExtra(dSummary, ddescription);
                in.putExtra(Date, pubDate);
                in.putExtra(cSummary, cdescription);
                in.putExtra(Notes, note);
                in.putExtra(Link, link);
                in.putExtra(Manufacturer,mf);
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
            pD = new ProgressDialog(RecallListActivity.this);
            pD.setMessage("Loading...");
            pD.setCancelable(false);
            pD.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            HttpHandler sh = new HttpHandler();
    		Bundle b=getIntent().getExtras();
    		String make =b.getString("mk");
    		String model = b.getString("mod");
    		String year = b.getString("yr");
    		System.out.println("1. "+make+"2. "+model+"3. "+year);
    		if(model.equals("")&&year.equals("")){
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&start_date=1995-01-01&per_page=50&sort=date";
    		}else if(year.equals("")){
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&model="+model+"&start_date=1995-01-01&per_page=50&sort=date";
    		
    		}else {
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&model="+model+"&year="+year+"&start_date=1995-01-01&per_page=50&sort=date";
    		}
    		System.out.println(URL);
            // Making a request to url and getting response
            String js = sh.makeHttpCall(URL, HttpHandler.GET);
 
            Log.d("Response: ", "> " + js);
 
            if (js != null) {
                try {
                    JSONObject jsonObj = new JSONObject(js);
                    JSONObject jo = jsonObj.getJSONObject("success"); 
                    // Getting JSON Array node
                    recalls = jo.getJSONArray("results");
                    String numr = jo.getString(num);
                    System.out.println(numr);
                    // looping through All Contacts
                    for (int i = 0; i < recalls.length(); i++) {
                        JSONObject jo1 = recalls.getJSONObject(i);
                         
                        String title = jo1.getString(Title);
                        String dd = jo1.getString(dSummary);
                        String cd = jo1.getString(cSummary);
                        String nt = jo1.getString(Notes);
                        String link = jo1.getString(Link);
                        String date = jo1.getString(Date);
                        String mf = jo1.getString(Manufacturer);
                        
 
                        // tmp hashmap for single contact
                        HashMap<String, String> recall = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        recall.put(Title, title);
                        recall.put(dSummary, dd);
                        recall.put(cSummary, cd);
                        recall.put(Notes, nt);
                        recall.put(Link, link);
                        recall.put(Date, date);
                        recall.put(Manufacturer, mf);
                        // adding contact to contact list
                        recallRList.add(recall);
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
            HttpHandler sh = new HttpHandler();
    		Bundle b=getIntent().getExtras();
    		String make =b.getString("mk");
    		String model = b.getString("mod");
    		String year = b.getString("yr");
    		System.out.println("1. "+make+"2. "+model+"3. "+year);
    		if(model.equals("")&&year.equals("")){
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&start_date=1995-01-01&per_page=50&sort=date";
    		}else if(year.equals("")){
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&model="+model+"&start_date=1995-01-01&per_page=50&sort=date";
    		}else if(model.equals("")){
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&year="+year+"&start_date=1995-01-01&per_page=50&sort=date";
    		}else {
    			URL = "http://api.usa.gov/recalls/search.json?organization=nhtsa&make="+make+"&model="+model+"&year="+year+"&start_date=1995-01-01&per_page=50&sort=date";
    		}
    		// Making a request to url and getting response
            String js = sh.makeHttpCall(URL, HttpHandler.GET);
            System.out.println(URL);

            if (js != null) {
            	try{
                    JSONObject jsonObj = new JSONObject(js);
                    JSONObject jo = jsonObj.getJSONObject("success"); 
                    // Getting JSON Array node
                    recalls = jo.getJSONArray("results");
                    String numr = jo.getString(num);
                    System.out.println(numr);
                    if (numr.equals("0")){
                    	alt_bld = new AlertDialog.Builder(RecallListActivity.this);
            			alt_bld.setMessage("Dont have the record you are looking for");
            			alt_bld.setCancelable(false);
            			alt_bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    		public void onClick(DialogInterface dialog, int id) {
                    		dialog.cancel();
                    		}
                    		});
                    	AlertDialog alert = alt_bld.create();
                    	
            			// Title for AlertDialog
            			alert.setTitle("Sorry");
                    	alert.show();
                    	
                    }else{
                    	Toast.makeText(getApplicationContext(), numr+" results", Toast.LENGTH_SHORT).show();
                    	ListAdapter adapter = new SimpleAdapter(
                    RecallListActivity.this, recallRList,
                    R.layout.recall_list_view, new String[] { Title,Date,dSummary,cSummary,Notes,Link,Manufacturer }, new int[] {
                            R.id.title, R.id.pubDate, R.id.dDescription,R.id.cDescription,R.id.notes,R.id.link,R.id.manufacturer });
 
            setListAdapter(adapter);
        }
 
    }catch (JSONException e) {
        e.printStackTrace();
    	
    }
            }}
}}

