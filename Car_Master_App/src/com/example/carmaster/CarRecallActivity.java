package com.example.carmaster;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CarRecallActivity extends Activity implements OnItemSelectedListener{
	Spinner sp1,sp2,sp3;
	ArrayAdapter<String> adp1,adp2,adp3,adp4,adp5,adp6,adp7,adp8,adp9,adp10,adp11,adp12,adp13,adp14,adp15,adp16,
	adp17,adp18,adp19,adp20,adp21,adp22,adp23,adp24,adp25,adp26,adp27,adp28,adp29,adp30,adp31,adp32,adp33,adp34,
	adp35,adp36,adp37,adp38;
	List<String> make,model,year;
	Button b;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recall_activity_main);
        // set sp1 for car manufacturers
        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp1.setOnItemSelectedListener(this);
        make = new ArrayList<String>();
        make.add("");
        make.add("Audi");make.add("Acura");make.add("Bentley");make.add("BMW");
        make.add("Buick");make.add("Cadillac");make.add("Chevrolet");make.add("Chrysler");
        make.add("Dodge");make.add("Ferrari");
        make.add("Ford");make.add("GMC");make.add("Honda");
        make.add("Hyundai");make.add("Infiniti");make.add("Jaguar");make.add("Jeep");
        make.add("Kia");make.add("Lamborghini");
        make.add("Lincoln");make.add("Lexus");make.add("Maserati");
        make.add("Mazda");make.add("Mercedes-Benz");
        make.add("Mini");make.add("Mitsubishi");make.add("Nissan");
        make.add("Pontiac");make.add("Porsche");
        make.add("Scion");make.add("Subaru");make.add("Tesla");make.add("Toyota");
        make.add("volkswagen");make.add("Volvo");
        adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, make);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);
        
        
        // set sp2 is subcategory for each org
        sp2= (Spinner) findViewById(R.id.spinner2);
        sp2.setOnItemSelectedListener(this);
        //set sp3 is subategory of sp2
        sp3=(Spinner) findViewById(R.id.spinner3);
        sp3.setOnItemSelectedListener(this);
        year = new ArrayList<String>();
        year.add("");year.add("2014");year.add("2013");year.add("2012");year.add("2011");year.add("2010");year.add("2009");
        year.add("2008");year.add("2007");year.add("2006");year.add("2005");year.add("2004");year.add("2003");year.add("2002");
        year.add("2001");year.add("2000");year.add("1999");year.add("1998");year.add("1997");year.add("1996");year.add("1995");
        adp15 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
        adp15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adp15);
        addListenerOnButton();

    } 
 

	@Override
    public void onItemSelected (AdapterView<?> arg1, View view, int arg2, long id) {
    	String make = arg1.getItemAtPosition(arg2).toString();
        Toast.makeText(arg1.getContext(), "Selected: " + make, Toast.LENGTH_SHORT).show();
    	if(arg1.equals(sp1)){
    		if(sp1.getSelectedItem().equals("")){
    			sp2.setEnabled(false);
    			sp3.setEnabled(false);
    			model=new ArrayList<String>();
    			model.add("");
    			adp2=new ArrayAdapter<String>(this,
		                  android.R.layout.simple_dropdown_item_1line,model);
		        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		        sp2.setAdapter(adp2);
		        select();
    		 
    		}else if(sp1.getSelectedItem().equals("Audi")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			// add model of the Audi to the spinner
    			model=new ArrayList<String>();
    			model.add("TT");model.add("R8");model.add("RS6");model.add("RS4");
    			model.add("S7");
    			model.add("A4");model.add("A6");model.add("A7");model.add("A8");
    			model.add("Q3");model.add("Q5");model.add("Q7");
		        adp3=new ArrayAdapter<String>(this,
		                  android.R.layout.simple_dropdown_item_1line,model);
		        adp3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		        sp2.setAdapter(adp3);
		        select();
    		
    		}else if(sp1.getSelectedItem().equals("Acura")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("TLX");model.add("RLX");model.add("MDX");model.add("RDX");
    			model.add("NSX");model.add("ILX");model.add("TL");model.add("TSX");
    			model.add("ZDX");model.add("RL");
    	        adp4=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp4);
    	        select();  

    	    	
    		}else if(sp1.getSelectedItem().equals("Bentley")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Mulsanne");model.add("Flying");model.add("Continental");model.add("EXP");
    			model.add("Azure");model.add("Arnage");model.add("BrookLands");
    	        adp5=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp5);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("BMW")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("X6");model.add("X5");model.add("X4");model.add("X3");model.add("Z4");
    			model.add("M6");model.add("M5");model.add("M4");model.add("M3");
    			model.add("650i");model.add("635d");model.add("645ci");model.add("760Li");
    			model.add("750i");model.add("740i");model.add("530i");model.add("530d");model.add("540i");model.add("545i");
    			model.add("328i");model.add("335i");model.add("330i");model.add("325i");model.add("325");model.add("323i");
    	        adp6=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp6);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Buick")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Regal");model.add("LaCrosse");model.add("Verano");model.add("Riviera");model.add("Encore");
    			model.add("Enclave");model.add("GL8");model.add("Envision");model.add("Lucerne");
    			model.add("Invicta");model.add("Terraza");model.add("Rendezvous");model.add("Rainier");
    			model.add("Royaum");model.add("Park");model.add("LeSabre");model.add("Century");model.add("Velite");
    	        adp7=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp7.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp7);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Cadillac")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Escalade");model.add("ATS");model.add("ELR");model.add("CTS");model.add("XTS");
    			model.add("SRX");model.add("SLS");model.add("BLS");model.add("Seville");
    			model.add("Deville");model.add("Eldorado");model.add("DTS");
    			model.add("Concours");model.add("Fleetwood");
    	        adp8=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp8.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp8);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Chevrolet")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Tahoe");model.add("Suburban");model.add("Silverado");model.add("Cruze");model.add("Corvette");
    			model.add("Colorado");model.add("SS");model.add("Malibu");model.add("Impala");
    			model.add("Camaro");model.add("Cobalt");model.add("Traverse");model.add("Trailblazer");
    			model.add("Silverado");model.add("Aveo");
    	        adp9=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp9.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp9);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Chrysler")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Town and Country");model.add("300M");model.add("300C");model.add("300L");
    			model.add("300");model.add("200");model.add("200S");model.add("Sebring");
    			model.add("Pacifica");model.add("Crossfire");model.add("CONCORDE");model.add("LHS");
    	        adp10=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp10.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp10);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Dodge")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Charger");model.add("Challenger");model.add("Ram");model.add("Durango");model.add("viper");
    			model.add("Dart");model.add("Grand");model.add("Journey");model.add("Neon");
    			model.add("Dakota");model.add("Magnum");
    	        adp11=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp11.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp11);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Ferrari")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("California");model.add("458");model.add("F12");model.add("FF");model.add("599");
    			model.add("Scuderia");model.add("F430");model.add("612");model.add("575");
    			model.add("360");model.add("456");model.add("512");
    			model.add("Concours");model.add("Fleetwood");
    	        adp12=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp12.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp12);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Ford")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Super Duty");model.add("F150");model.add("F250");model.add("F350");model.add("F450");model.add("F550");
    			model.add("Mustang");model.add("Focus");model.add("Expedition");model.add("Edge");
    			model.add("Falcon");model.add("Taurus");model.add("Mondeo");
    			model.add("Fusion");model.add("Explorer");model.add("Escort");model.add("Ranger");
    	        adp13=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp13.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp13);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("GMC")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Yukon");model.add("Sierra");model.add("Canyon");model.add("Terrain");model.add("Acadia");
    			model.add("Granite");model.add("Envoy");
    	        adp14=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp14.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp14);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Honda")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("HRV");model.add("Civic");model.add("Accord");model.add("Crosstour");model.add("CRV");
    			model.add("Odyssey");model.add("Jazz");model.add("Insight");model.add("Fit");
    	        adp16=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp16.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp16);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Hyundai")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Sonata");model.add("Genesis");model.add("Elantra");model.add("I30");model.add("I20");
    			model.add("Santa");model.add("Verna");model.add("Azera");model.add("Accent");
    			model.add("IX55");model.add("IX35");
    	        adp17=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp17.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp17);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Infiniti")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Q70");model.add("Q80");model.add("Q50");model.add("Q45");model.add("Q30");model.add("M35");
    			model.add("JX");model.add("FX");model.add("LE");model.add("QX");
    			model.add("EX");model.add("G37");model.add("G35");
    	        adp18=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp18.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp18);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Jaguar")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("XE");model.add("F-Type");model.add("X-Type");model.add("S-Type");model.add("XKR");
    			model.add("XJR");model.add("XFR");model.add("C-X75");model.add("C-X17");
    			model.add("XJ");model.add("XF");
    	        adp19=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp19.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp19);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Jeep")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Renegade");model.add("Wrangler");model.add("Patriot");model.add("Grand");model.add("Compass");
    			model.add("Cherokee");model.add("Liberty");
    	        adp20=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp20.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp20);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Kia")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Soul");model.add("sorento");model.add("Sedona");model.add("Sportage");model.add("Pro Ceed");
    			model.add("Optima");model.add("Forte");model.add("Cerato");model.add("Cadenza");
    			model.add("K9");model.add("Ceed");model.add("Rio");model.add("K2");model.add("Grand");
    			model.add("Venga");
    	        adp21=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp21.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp21);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Lamborghini")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Huracan");model.add("Veneno Roadster");model.add("Gallardo");model.add("Aventador");model.add("Murcielago");
    			model.add("Diablo");
    	        adp22=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp22.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp22);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Lincoln")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Navigator");model.add("MKC");model.add("MKX");model.add("MKZ");model.add("MKT");
    			model.add("MKS");model.add("Town");
    	        adp23=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp23.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp23);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Lexus")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("RC");model.add("NX");model.add("IS");model.add("GX");model.add("GT");
    			model.add("GS");model.add("RX");model.add("LX");model.add("LS");
    			model.add("GS");model.add("ES");model.add("LF");

    	        adp24=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp24.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp24);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Maserati")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("GranTurismo");model.add("Ghibli");model.add("Quattroporte");model.add("Spyder");model.add("Coupe");
    	        adp25=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp25.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp25);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Mazda")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("2");model.add("3");model.add("MX5");model.add("6");model.add("5");
    			model.add("CX-9");model.add("CX-5");model.add("CX-7");model.add("RX-8");
    			model.add("Deville");model.add("Eldorado");model.add("DTS");
    			model.add("Concours");model.add("Fleetwood");
    	        adp26=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp26.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp26);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Mercedes-Benz")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("AMG");model.add("S65");model.add("S63");model.add("S600");model.add("S500");
    			model.add("SLS");model.add("S550");model.add("CLS");model.add("C63");
    			model.add("CLA");model.add("CLS");model.add("SL65");model.add("SL63");model.add("SL550");model.add("SL500");
    			model.add("E550");model.add("E350");model.add("E63");model.add("G55");
    	        adp27=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp27.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp27);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Mini")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Cooper");model.add("Paceman");model.add("Countryman");model.add("Roadster");model.add("Clubvan");
    			
    	        adp28=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp28.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp28);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Mitsubishi")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("OutLander");model.add("CUV");model.add("Lancer");model.add("Galant");model.add("Eclipse");
    			model.add("Colt");model.add("Prototype");model.add("Raider");
    	        adp29=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp29.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp29);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Nissan")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Versa");model.add("Pulsar");model.add("Sentra");model.add("200SX");model.add("Frontier");
    			model.add("Xterra");model.add("Maxima");model.add("Quest");model.add("Rogue");
    			model.add("Juke");model.add("370z");model.add("Note");
    			model.add("Murano");model.add("X-Trail");
    	        adp30=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp30.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp30);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Pontiac")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("G8");model.add("G6");model.add("G3");model.add("GTO");model.add("Solstice");
    			model.add("Torrent");model.add("Montana");model.add("Grand");model.add("Aztek");
    			model.add("Vibe");
    	        adp31=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp31.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp31);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Porsche")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Macan");model.add("Cayman");model.add("cayenne");model.add("Boxster");model.add("918");
    			model.add("911");model.add("Panamera");
    	        adp32=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp32.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp32);
    	        select(); 
    		}else if(sp1.getSelectedItem().equals("Scion")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("tC");model.add("FR-S");model.add("XB");model.add("XD");
    	        adp33=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp33.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp33);
    	        select();    	        
    		}else if(sp1.getSelectedItem().equals("Subaru")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("WRX");model.add("outback");model.add("Legacy");model.add("XV");model.add("Forester");
    			model.add("Levorg");model.add("BRZ");model.add("Impreza");
    	        adp34=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp34.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp34);
    	        select();
    		}else if(sp1.getSelectedItem().equals("Tesla")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Model S");model.add("Roadster");
    	        adp35=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp35.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp35);
    	        select();
    		}else if(sp1.getSelectedItem().equals("Toyota")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("FJ Cruiser");
    			model.add("Tundra");model.add("Tacoma");model.add("Camry");model.add("Verso");model.add("Land");
    			model.add("Highlander");model.add("Corolla");model.add("Venza");model.add("RAV4");
    			model.add("4Runner");model.add("Sienna");model.add("Avalon");
    			model.add("Echo");model.add("Celica");
    	        adp36=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp36.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp36);
    	        select();
    		}else if(sp1.getSelectedItem().equals("volkswagen")){
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("Beetle");model.add("Golf");model.add("Polo");model.add("Taigun");model.add("Cross");
    			model.add("Touareg");model.add("Santana");model.add("Jetta");model.add("CC");
    			model.add("Sharan");model.add("Passat");model.add("Phaeton");
    	        adp37=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp37.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp37);
    	        select();
    		}else {
    			sp2.setEnabled(true);
    			sp3.setEnabled(true);
    			model=new ArrayList<String>();
    			model.add("XC90");model.add("XC70");model.add("XC60");model.add("V70");model.add("V60");
    			model.add("V40");model.add("S80");model.add("S60");model.add("S40");
    			model.add("C70");model.add("C30");
    	        adp38=new ArrayAdapter<String>(this,
    	                  android.R.layout.simple_dropdown_item_1line,model);
    	        adp38.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    	        sp2.setAdapter(adp38);
    	        select();
    	}
    	}
	}

    private void select() {


        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	String models = arg0.getItemAtPosition(arg2).toString();
                Toast.makeText(arg0.getContext(), "Selected: " + models, Toast.LENGTH_SHORT).show();
                

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }
        });

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
    private void addListenerOnButton() {
    	final Context ct =this;
 		b = (Button) findViewById(R.id.Submit);
 		b.setOnClickListener(new OnClickListener(){
 			@Override
 			public void onClick(View arg0){
 				Spinner make = (Spinner) findViewById(R.id.spinner1);
 				Spinner model = (Spinner) findViewById(R.id.spinner2);
 				Spinner year = (Spinner) findViewById(R.id.spinner3);
 				CharSequence mc = (CharSequence) make.getSelectedItem();
 				CharSequence moc=(CharSequence) model.getSelectedItem();
 				CharSequence yc = (CharSequence) year.getSelectedItem();
 				String mText="";
 				String moText="";
 				String yText="";
 				mText = mc.toString();//make
 				moText = moc.toString();//model
 				yText = yc.toString();//year
 				System.out.println(",,,,,,,,"+mText);
 					Intent intent = new Intent(ct, RecallListActivity.class);
 					Bundle b =new Bundle();
 					Bundle b1 =new Bundle();
 					Bundle b2=new Bundle();
 	 				b.putString("mk", mText);
 	 				b1.putString("mod", moText);
 	 				b2.putString("yr", yText);
 	 				intent.putExtras(b);
 	 				intent.putExtras(b1);
 	 				intent.putExtras(b2);
 	 				System.out.println("org: "+b+"cate:   "+b1+"subcate: "+b2);
				    System.out.println(mText+"cate: "+moText+"subcate: "+yText);
	                startActivity(intent);
 				
 			}
 		});
 	}
	}
