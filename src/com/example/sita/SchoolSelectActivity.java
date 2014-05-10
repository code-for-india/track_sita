package com.example.sita;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SchoolSelectActivity extends Activity {
	
	Button back, next;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_select);
		back = (Button)findViewById(R.id.back_school_select);
		next = (Button) findViewById(R.id.next_school_select);
		
		Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
		ArrayList<String> options = new ArrayList<String>();
		options.add("opt 1");
		options.add("opt 2");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,options);
		dropdown.setAdapter(adapter);
		
		next.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (SchoolSelectActivity.this,TakePictureActivity.class);
    			startActivity(intent);
    		}
    		
    	});
		
		back.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (SchoolSelectActivity.this,MainActivity.class);
    			startActivity(intent);
    		}
    		
    	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.school_select, menu);
		return true;
	}

}
