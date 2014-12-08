package com.example.carmaster;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
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
    GPSTracker GPS;
    Geocoder geoCoder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_value);
        GPS = new GPSTracker(CarValueActivity.this);
        geoCoder = new Geocoder(CarValueActivity.this);

        make = (EditText) findViewById(R.id.editText1);
        model = (EditText) findViewById(R.id.editText2);
        year = (EditText) findViewById(R.id.editText3);
        condition = (EditText) findViewById(R.id.editText4);
        value = (TextView) findViewById(R.id.textView1);
        calculate = (Button) findViewById(R.id.button6);
        calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                final String makeText = make.getText().toString().trim();
                final String modelText = model.getText().toString().trim();
                final String yearText = year.getText().toString().trim();
                final String condText = condition.getText().toString().trim();

                if (!makeText.isEmpty() && !modelText.isEmpty() &&
                        !yearText.isEmpty() && !condText.isEmpty())
                {
                    final String styleUrl = "https://api.edmunds.com/api/vehicle/v2/" + makeText +
                            "/" + modelText + "/" + yearText + "/styles?condition=" + condText +
                            "fmt=json&api_key=c2d4ywj4wry92ucvrspspm8g";

                    System.out.println(styleUrl);

                    final String styleJsonStr = httpHandler.makeHttpCall(styleUrl, HttpHandler.GET);
                    String styleId = null;

                    if (styleJsonStr != null) {
                        try {
                            final JSONObject styleJson = new JSONObject(styleJsonStr);
                            final JSONArray jsonStyles = styleJson.getJSONArray("styles");
                            if (jsonStyles.length() > 0) {
                                final JSONObject jsonStyle = jsonStyles.getJSONObject(0);
                                styleId = jsonStyle.getString("id");
                                System.out.println(styleId);
                            }
                        } catch (final JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (styleId != null) {
                        String zipCode = "64050"; // set default if GPS is unavailable

                        // check if GPS enabled
                        if (GPS.canGetLocation()) {
                            try {
                                System.out.println(GPS.getLatitude());
                                System.out.println(GPS.getLongitude());
                                final List<Address> address = geoCoder.getFromLocation(GPS.getLatitude(), GPS.getLongitude(), 1);

                                if (!address.isEmpty()) {
                                    System.out.println(address.toString());
                                    zipCode = address.get(0).getPostalCode();
                                }
                            } catch (final IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        final String priceUrl = "https://api.edmunds.com/v1/api/tmv/tmvservice/" +
                                "calculatetypicallyequippedusedtmv?styleid=" + styleId +
                                "&zip=" + zipCode + "&fmt=json&api_key=c2d4ywj4wry92ucvrspspm8g";

                        System.out.println(priceUrl);

                        String price = null;

                        final String priceJsonStr = httpHandler.makeHttpCall(priceUrl, HttpHandler.GET);

                        if (priceJsonStr != null) {
                            try {
                                final JSONObject priceJson = new JSONObject(priceJsonStr);
                                final JSONObject totalMarketValueJson = priceJson.getJSONObject("tmv");
                                final JSONObject totalWithOptionsJson = totalMarketValueJson.getJSONObject("totalWithOptions");
                                price = totalWithOptionsJson.getString("usedTmvRetail");
                                System.out.println(price);
                            } catch (final JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        if (price != null) {
                            value.setText(price);
                        }
                    }
                }
            }
        });
    }
}
