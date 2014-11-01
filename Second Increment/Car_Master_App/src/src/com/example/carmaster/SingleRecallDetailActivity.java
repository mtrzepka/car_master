package com.example.carmaster;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class SingleRecallDetailActivity extends Activity{

		// 7 information from xml
	static final String Recall ="success";
	static final String Title ="recall_subject";
	static final String dSummary ="defect_summary";
	static final String cSummary ="consequence_summary";
	static final String Notes ="notes";
	static final String Link="recall_url";
	static final String Date="recall_date";
	static final String Manufacturer ="manufacturer";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recall_detail);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get jason values from previous intent
        String title = in.getStringExtra(Title);
        String ddescription = in.getStringExtra(dSummary);
        String cdescription = in.getStringExtra(cSummary);
        String notes = in.getStringExtra(Notes);
        String date = in.getStringExtra(Date);
        String link = in.getStringExtra(Link);
        String mf =in.getStringExtra(Manufacturer);
        // Displaying all values on the screen
        TextView lblTitle = (TextView) findViewById(R.id.title_label);
        TextView lblds = (TextView) findViewById(R.id.ddescription_label);
        TextView lblcs = (TextView) findViewById(R.id.cdescription_label);
        TextView lblnote = (TextView) findViewById(R.id.note_label);
        TextView lblDate = (TextView) findViewById(R.id.Date_label);
        TextView lblLink = (TextView) findViewById(R.id.Link_label);
        TextView lblmf = (TextView) findViewById(R.id.mf_label);
        
        lblTitle.setText(title);
        lblds.setText(ddescription);
        lblcs.setText(cdescription);
        lblnote.setText(notes);
        lblDate.setText(date);
        lblLink.setText(link);
        lblmf.setText(mf);
        
    }
}


