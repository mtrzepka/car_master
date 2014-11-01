package com.example.carinformation;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.carinformation.R;
import com.example.carinformation.car_detail;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	Spinner sp1,sp2,sp3;
	ArrayAdapter<String> adp1,adp2,adp3,adp4,adp5,adp6,adp7,adp8,adp9,adp10,adp11,adp12,adp13,adp14,adp15,adp16,
	adp17,adp18,adp19,adp20,adp21,adp22,adp23,adp24,adp25,adp26,adp27,adp28,adp29,adp30,adp31,adp32,adp33,adp34,
	adp35,adp36,adp37,adp38;
	List<String> state,category,view;
	Button b1;
	
	String URL = "";
	String Make = "make";
	String Model = "model";
	String Engine = "engine";
	String makename = "name";
	String modelname="niceName";
	String engine_compressionRation = "compressionRtio";
	String engine_cylinder ="cylinder";
	String engine_size ="size";
	String engine_displacement="displacement";
	String engine_fuelType="fuelType";
	String engine_horsepower="horsepower";
	String engine_torque="torque";
	String engine_totalValves="totalValves";
	String Drivewheels="driveWheels";
	JSONArray details=null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sp1 = (Spinner) findViewById(R.id.spinner1);
		/*sp1.setOnItemSelectedListener(this);*/
        state = new ArrayList<String>();
        state.add("");
        state.add("new");state.add("used");state.add("future");
        adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);
        
        sp2 = (Spinner) findViewById(R.id.spinner2);
		/*sp1.setOnItemSelectedListener(this);*/
        category = new ArrayList<String>();
        category.add("");
        category.add("4dr Hatchback");category.add("Coupe");category.add("Convertible");
        category.add("Sedan");category.add("2dr Hatchback");category.add("Wagon");
        category.add("Regular Cab Pickup");category.add("Extended Cab Pickup");category.add("Crew Cab Pickup");
        category.add("2dr SUV");category.add("4dr SUV");category.add("Convertible SUV");
        category.add("Cargo Van");category.add("Passenger Van");category.add("Cargo Minivan");
        category.add("Passenger Minivan");
        adp4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        adp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adp4);
        
        sp3 = (Spinner) findViewById(R.id.spinner3);
	/*	sp3.setOnItemSelectedListener(this);*/
        view = new ArrayList<String>();
        view.add("");
        view.add("full");view.add("basic");
        adp21 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, view);
        adp21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adp21);
	
        
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		addListenerOnButton();
	}



	
	
	public void addListenerOnButton(){
		
		final Context context = this;
		b1 = (Button) findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				EditText make = (EditText) findViewById(R.id.editText1);
				EditText model = (EditText) findViewById(R.id.editText2);
				EditText year = (EditText) findViewById(R.id.editText3);
				Spinner state =(Spinner) findViewById(R.id.spinner1);
				Spinner category =(Spinner) findViewById(R.id.spinner2);
				Spinner view =(Spinner) findViewById(R.id.spinner3);
				
				
			    String maText = make.getText().toString();
			    String moText = model.getText().toString();
			    String yeText = year.getText().toString();
			    
			    CharSequence st = (CharSequence) state.getSelectedItem();
			    CharSequence ca = (CharSequence) category.getSelectedItem();
			    CharSequence vi = (CharSequence) view.getSelectedItem();
			    String stText="";
 				String caText="";
 				String viText="";
 				stText = st.toString();//make
 				caText = ca.toString();//model
 				viText = vi.toString();//year
			    
 				
				
 				System.out.println("1. "+make+"2. "+model+"3. "+year+"4. "+state+"5. "+category+"6. "+view);
 				HttpHandler sh = new HttpHandler();
 				
 				String js = sh.makeHttpCall("https://api.edmunds.com/api/vehicle/v2/"+maText+"/"+moText+"/"+yeText+"/styles?state="+stText+"&category="+caText+"&view="+viText+"&fmt=json&api_key=4njg2m9m9qrsqemw5snm9pg3", HttpHandler.GET);
			    
 				Log.d("Response: ",">" +js);
 				
 				if(js != null){
 					try
 					{
 						JSONObject jsonObj = new JSONObject(js);
 						JSONArray jo = jsonObj.getJSONArray("style");
 						
 						Intent intent = new Intent(context, car_detail.class);
 						
 						for(int i = 0; i < jo.length(); i++)
 						{
 							JSONObject jo1 = jo.getJSONObject(i);
 							
 							String ma1 = jo1.getString(Make);
 							String mo1 = jo1.getString(Model);
 							String en1 = jo1.getString(Engine);
 							String Dr1 = jo1.getString(Drivewheels);
 							
 							    Bundle K= new Bundle();
		        				K.putString("DR", Drivewheels);
		        				intent.putExtras(K);
 							
 							JSONObject jsonObj1 = new JSONObject(js);
 							JSONArray ma2 = jsonObj1.getJSONArray("make");
 							for(int j = 0; j< ma1.length(); j++)
 							{
 								JSONObject jo2 = ma2.getJSONObject(j);
 								
 								
 			                    String makename1 = jo2.getString(makename);
 								
 			                    Bundle a= new Bundle();
 		        				a.putString("MA", makename1);
 		        				intent.putExtras(a);
 							}
 							
 							JSONObject jsonObj2 = new JSONObject(js);
 							JSONArray mo2 = jsonObj2.getJSONArray("model");
 							for(int j = 0; j< mo2.length(); j++)
 							{
 								JSONObject jo3 = mo2.getJSONObject(j);
 								
 								String modelname1 = jo3.getString(modelname);
 								
 								Bundle B= new Bundle();
 		        				B.putString("MO", modelname1);
 		        				intent.putExtras(B);
 							}
 							
 							JSONObject jsonObj3 = new JSONObject(js);
 							JSONArray en2 = jsonObj3.getJSONArray("engine");
 							for(int j = 0; j< en2.length(); j++)
 							{
 								JSONObject jo4 = en2.getJSONObject(j);
 								
 								String compress = jo4.getString(engine_compressionRation);
 								String cylinder = jo4.getString(engine_cylinder);
 								String size = jo4.getString(engine_size);
 								String displacement = jo4.getString(engine_displacement);
 								String fuelType = jo4.getString(engine_fuelType);
 								String horsepower = jo4.getString(engine_horsepower);
 								String torque = jo4.getString(engine_torque);
 								String totalValves = jo4.getString(engine_totalValves);
 								
 								Bundle C= new Bundle();
 		        				C.putString("CO", compress);
 		        				intent.putExtras(C);
 		        				
 		        				Bundle D= new Bundle();
 		        				D.putString("CY", cylinder);
 		        				intent.putExtras(D);
 		        				
 		        				Bundle E= new Bundle();
 		        				E.putString("SI", size);
 		        				intent.putExtras(E);
 		        				
 		        				Bundle F= new Bundle();
 		        				F.putString("DI", displacement);
 		        				intent.putExtras(F);
 		        				
 		        				Bundle G= new Bundle();
 		        				G.putString("FU", fuelType);
 		        				intent.putExtras(G);
 		        				
 		        				Bundle H= new Bundle();
 		        				H.putString("HO", horsepower);
 		        				intent.putExtras(H);
 		        				
 		        				Bundle I= new Bundle();
 		        				I.putString("TOR", torque);
 		        				intent.putExtras(I);
 		        				
 		        				Bundle J= new Bundle();
 		        				J.putString("TOT", totalValves);
 		        				intent.putExtras(J);
 								
 							}
 								
 						}
 						
 						
 						startActivity(intent);
 					
 					}catch(JSONException e){
 						e.printStackTrace();
 					}
 				
 					
 				}else{
 					Log.e("ServiceHandler", "Couldn't get any data from the API");
 				}
 				return;
			}
		});
	}
			
 				
}				
				
					
				


                
 
			
 
	

