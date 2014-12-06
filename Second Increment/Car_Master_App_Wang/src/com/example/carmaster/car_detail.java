package com.example.carmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class car_detail extends Activity{

		// 7 information from xml
	static final String Information ="style";
	static final String Make = "make";
	static final String Model = "model";
	static final String Engine = "engine";
	static final String makename = "name";
	static final String modelname="niceName";
	static final String engine_compressionRation = "compressionRatio";
	static final String engine_cylinder ="cylinder";
	static final String engine_size ="size";
	static final String engine_displacement="displacement";
	static final String engine_fuelType="fuelType";
	static final String engine_horsepower="horsepower";
	static final String engine_torque="torque";
	static final String engine_totalValves="totalValves";
	static final String Drivewheels="driveWheels";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardetail);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get jason values from previous intent
       
        String maname = in.getStringExtra("MA");
        String moname = in.getStringExtra("MO");
        String compress = in.getStringExtra("CO");
        String cylinder =in.getStringExtra("CY");
        String size =in.getStringExtra("SI");
        String displacement =in.getStringExtra("DI");
        String fuelType =in.getStringExtra("FU");
        String horsepower =in.getStringExtra("HO");
        String torque =in.getStringExtra("TOR");
        String totalValves =in.getStringExtra("TOT");
        String drivewheels =in.getStringExtra("DR");
        System.out.println(maname+"2. "+moname+"3. "+compress+"4. "+cylinder+"5. "+size+"6. "+displacement+"7. "+fuelType+"8. "+
        		horsepower+"9. "+torque+"10. "+totalValves+"11. "+drivewheels);
        // Displaying all values on the screen
        TextView lblmaname = (TextView) findViewById(R.id.made_label);
        TextView lblmoname = (TextView) findViewById(R.id.model_label);
        TextView lblcompress = (TextView) findViewById(R.id.compression_label);
        TextView lblcylinder = (TextView) findViewById(R.id.cylinder_label);
        TextView lblsize = (TextView) findViewById(R.id.size_label);
        TextView lbldisplacement = (TextView) findViewById(R.id.displacement_label);
        TextView lblfuelType = (TextView) findViewById(R.id.fulType_label);
        TextView lblhorsepower = (TextView) findViewById(R.id.horsepower_label);
        TextView lbltorque = (TextView) findViewById(R.id.torque_label);
        TextView lbltotalValves = (TextView) findViewById(R.id.totalValves_label);
        TextView lbldrivewheels = (TextView) findViewById(R.id.drivewheels_label);
        
        
        lblmaname.setText(maname);
        lblmoname.setText(moname);
        lblcompress.setText(compress);
        lblcylinder.setText(cylinder);
        lblsize.setText(size);
        lbldisplacement.setText(displacement);
        lblfuelType.setText(fuelType);
        lblhorsepower.setText(horsepower);
        lbltorque.setText(torque);
        lbltotalValves.setText(totalValves);
        lbldrivewheels.setText(drivewheels);
       
        
    }
}


