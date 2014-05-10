package com.example.sita;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;

public class ReviewActivity extends Activity {
	
	private Button save, submit;
	private RatingBar ratingBar;
	
	double userRating;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		save = (Button) findViewById(R.id.button_save_final);
		submit = (Button) findViewById(R.id.button_submit_final);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			
			
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				userRating = rating; 
				
			}
		});
		
		
		submit.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
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
