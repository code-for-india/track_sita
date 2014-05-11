package com.example.sita;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class ReviewActivity extends Activity {
	
	private Button save, submit;
	private RatingBar ratingBar;
	private EditText reviewtext;
	double userRating;
	
	JSONObject json = new JSONObject();
	JSONObject imgJson = new JSONObject();
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		save = (Button) findViewById(R.id.button_save_final);
		//submit = (Button) findViewById(R.id.button_submit_final);
		// get the edit text
		reviewtext = (EditText) findViewById(R.id.reviewText);
		
		reviewtext.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				GlobalVars.review = reviewtext.getText().toString();
				
			}
		});
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				GlobalVars.rating = rating; 
				
			}
		});
		
		
//		submit.setOnClickListener(new OnClickListener()
//    	{
//    		public void onClick(View v)
//    		{
//    			//json create and save
//    			for(int i=0; i<GlobalVars.imagePaths.size(); i++)
//    			{
//    				try 
//    				{
//						imgJson.put("img"+Integer.toString(i), GlobalVars.imagePaths.get(i).toString() );
//					} 
//    				
//    				catch (JSONException e) 
//    				{
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//    			}
//    			
//    			try {
//    				json.put("school_id",Integer.toString(GlobalVars.school_id));
//					json.put("date_time", Long.toString(GlobalVars.date_time));
//					json.put("type_id", Integer.toString(GlobalVars.place_id));
//					json.put("img", imgJson);
//					json.put("rating", Float.toString(GlobalVars.rating));
//					json.put("review", GlobalVars.review);
//					
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    			
//    			try
//    			{
//    				FileWriter file = new FileWriter(Environment.getExternalStorageDirectory()+"/SITA/"+Integer.toString(GlobalVars.school_id)+"_"+Long.toString(GlobalVars.date_time)+"/data.json");
//    				file.write(json.toString());
//    				file.flush();
//    				file.close();
//    				
//    			}
//    			catch(IOException e)
//    			{
//    				e.printStackTrace();
//    			}
//    			
//    			Intent intent = new Intent (ReviewActivity.this,PushToServerActivity.class);
//    			startActivity(intent);
//    		}
//    		
//    	});
//		
		save.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			//json create and save
    			for(int i=0; i<GlobalVars.imagePaths.size(); i++)
    			{
    				try 
    				{
						imgJson.put("img"+Integer.toString(i), GlobalVars.imagePaths.get(i).toString() );
					} 
    				
    				catch (JSONException e) 
    				{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    			try {
					json.put("school_id",Integer.toString(GlobalVars.school_id));
					json.put("date_time", Long.toString(GlobalVars.date_time));
					json.put("type_id", Integer.toString(GlobalVars.place_id));
					json.put("img", imgJson);
					json.put("rating", Float.toString(GlobalVars.rating));
					json.put("review", GlobalVars.review);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			try
    			{
    				FileWriter file = new FileWriter(Environment.getExternalStorageDirectory()+"/SITA/"+Integer.toString(GlobalVars.school_id)+"_"+Long.toString(GlobalVars.date_time)+"/data.json");
    				file.write(json.toString());
    				file.flush();
    				file.close();
    				
    			}
    			catch(IOException e)
    			{
    				e.printStackTrace();
    			}
    			
    			Intent intent = new Intent (ReviewActivity.this,PushToServerActivity.class);
    			startActivity(intent);
    		}
    		
    	});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}

}
