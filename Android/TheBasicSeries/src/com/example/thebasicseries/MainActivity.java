package com.example.thebasicseries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
private Button btn1;
private Button btn2;
private Button btn3;
private ImageView imageToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   btn1=(Button)findViewById(R.id.button1);
   btn2=(Button)findViewById(R.id.button2);
   btn3=(Button)findViewById(R.id.button3);
   
   imageToShow=(ImageView)findViewById(R.id.imageView1);
    btn1.setOnClickListener(new OnClickListener(){
    	
    	public void onClick1(View v){
    		imageToShow.setBackgroundResource(R.drawable.layout);
    		
    		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}		
		}
		);;
    } ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
