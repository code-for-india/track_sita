package com.example.sita;

import java.util.ArrayList;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import android.widget.Toast;

public class MainActivity extends Activity  implements LocationListener  {
	
	Button back, next;
	private LocationManager locationManager;
	private String provider;
	public ArrayList <SchoolIdName> schoolList;

	DBAdapter schoolInfo = new DBAdapter(this);;
	ArrayList <Integer> optionId;
	
	int pos;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
	    Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
		ArrayList<String> options = new ArrayList<String>();
		optionId = new ArrayList<Integer>();
		
	    		
		if(location!=null)
		{
			onLocationChanged(location);
		}
		
		else
		{
			schoolList = new ArrayList<SchoolIdName>();
		}
	
		next = (Button) findViewById(R.id.next_school_select);
		
		for( int i =0; i< schoolList.size();i++)
		{
			options.add((String)schoolList.get(i).getSchoolName());
			optionId.add((int)schoolList.get(i).getSchoolId());
		}
		
		// this will come after seeing current location and judging nearby schools!
		//each school has unique id.
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,options);
		dropdown.setAdapter(adapter);
		
		 pos = (int) dropdown.getSelectedItemPosition();
		//Log.d("SchoolSelect", "pos : "+pos+" | optionId : "+optionId.size() );
		// GlobalVars.school_id = (int) optionId.get(pos);
		 
		
		dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				 GlobalVars.school_id = (int) optionId.get(pos);
				 GlobalVars.date_time = System.currentTimeMillis()/1000;
				//Log.d("SchoolSelect", GlobalVars.school_id + " " +GlobalVars.date_time);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		next.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{	
    			
    			Intent intent = new Intent (MainActivity.this, PlaceActivity.class);
    			startActivity(intent);
    		}
    		
    	});
		
		/*back.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			Intent intent = new Intent (MainActivity.this,MainActivity.class);
    			startActivity(intent);
    		}
    		
    	});*/
	}

	
	
	 @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	    
	  }
	 
	 @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		float lat = (float) (location.getLatitude());
	    float lng = (float) (location.getLongitude());
	   
	    schoolInfo.open();
	    schoolInfo.fillDB();
	    schoolList = schoolInfo.getNearBySchools(lat, lng);
	    schoolInfo.close();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Disabled provider " + provider,
		        Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
		        Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}



