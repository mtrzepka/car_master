package com.example.carmaster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CarValueActivity extends Activity {
	Button calculate;
	EditText make, model, year, condition;
	TextView value;
	HttpHandler httpHandler = new HttpHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_value);
		
		make = (EditText)findViewById(R.id.editText1);
		model = (EditText)findViewById(R.id.editText2);
		year = (EditText)findViewById(R.id.editText3);
		condition = (EditText)findViewById(R.id.editText4);
		value = (TextView)findViewById(R.id.textView1);
		calculate = (Button) findViewById(R.id.button6);
		calculate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String makeText = make.getText().toString().trim();
				String modelText = model.getText().toString().trim();
				String yearText = year.getText().toString().trim();
				String condText = condition.getText().toString().trim();
				
			    if (!makeText.isEmpty() && !modelText.isEmpty() &&
			    	!yearText.isEmpty() && !condText.isEmpty())
			    {
			    	String styleUrl = "https://api.edmunds.com/api/vehicle/v2/" + makeText + 
			    			"/" + modelText + "/" + yearText + "/styles?condition=" + condText + 
			    			"fmt=json&api_key=c2d4ywj4wry92ucvrspspm8g";
			    	
			    	String styleJsonStr = httpHandler.makeHttpCall(styleUrl, HttpHandler.GET);
			    	String styleId = null;
			    	
			    	if (styleJsonStr != null) {
			    		try {
							JSONObject styleJson = new JSONObject(styleJsonStr);
							JSONArray jsonStyles = styleJson.getJSONArray("styles");
							if (jsonStyles.length() > 0) {
								JSONObject jsonStyle = jsonStyles.getJSONObject(0);
								styleId = jsonStyle.getString("id");
								System.out.println(styleId);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
			    	
			    	if (styleId != null) {
			    		// TODO: Use GPS to find zipcode
				    	String priceUrl = "https://api.edmunds.com/v1/api/tmv/tmvservice/" + 
				    			"calculatetypicallyequippedusedtmv?styleid=" + styleId + 
				    			"&zip=" + "64050" + "&fmt=json&api_key=c2d4ywj4wry92ucvrspspm8g";
				    	String price = null;
				    	
				    	String priceJsonStr = httpHandler.makeHttpCall(priceUrl, HttpHandler.GET);
				    	
				    	if (priceJsonStr != null) {
				    		try {
								JSONObject priceJson = new JSONObject(priceJsonStr);
								JSONObject totalMarketValueJson = priceJson.getJSONObject("tmv");
								JSONObject totalWithOptionsJson = totalMarketValueJson.getJSONObject("totalWithOptions");
								price = totalWithOptionsJson.getString("usedTmvRetail");
								System.out.println(price);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
				    	
				    	if (price != null)
				    		value.setText(price);
			    	}
			    }
			}
		});
	}
}
