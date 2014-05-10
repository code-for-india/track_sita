package com.example.sita;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button collect, submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		collect = (Button) findViewById(R.id.button_collect_main);
		submit = (Button) findViewById(R.id.button_submit_main);
		
		
		collect.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (MainActivity.this,SchoolSelectActivity.class);
    			startActivity(intent);
    		}
    		
    	});
		
		submit.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (MainActivity.this,SchoolSelectActivity.class);
    			startActivity(intent);
    		}
    		
    	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
