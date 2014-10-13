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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		button1 = (Button) findViewById(R.id.button2);
		

		button1.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, CarRecallActivity.class);
                            startActivity(intent);   
 
			}
 
		});
	}
 
}
