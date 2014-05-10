package com.example.sita;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

@SuppressWarnings("deprecation")
public class TakePictureActivity extends Activity {
	
	private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
   
    private static final String IMAGE_DIRECTORY_NAME = "SITA";
    
    private Uri fileUri; // file url to store image
    
    private Button takePicture, next, back;
    
 
	
	
	private Integer[] imageIds ={
		R.drawable.ic_launcher, 
		R.drawable.test
		
		// has to be fetched from directory school_id_date_time
		// on app start, keep long, lat, date_time remembered. prefix school_id to it before creating folder in external storage
		// name the photos as : school_id_same_date_time_i i=counter.
	};

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_picture);
		
		// Do the gallery part at last.
		// Load all images in SITA/date_time folder.
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		takePicture = (Button) findViewById(R.id.button_take_image);
		back = (Button) findViewById(R.id.button_back_take_image);
		next = (Button) findViewById(R.id.button_next_take_image);
		
		gallery.setSpacing(1);
		gallery.setAdapter(new GalleryImageAdapter(this));
		
		 takePicture.setOnClickListener(new View.OnClickListener() {
			 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				captureImage();
				// reload this activity probably! ensure session variables are intact!
			}
		});
		 
		 next.setOnClickListener(new OnClickListener()
	    	{
	    		public void onClick(View v)
	    		{
	    			Intent intent = new Intent (TakePictureActivity.this,ReviewActivity.class);
	    			startActivity(intent);
	    		}
	    		
	    	});
		 
		 back.setOnClickListener(new OnClickListener()
	    	{
	    		public void onClick(View v)
	    		{
	    			Intent intent = new Intent (TakePictureActivity.this,PlaceActivity.class);
	    			startActivity(intent);
	    		}
	    		
	    	});
		
	} 

	public void captureImage()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//fileUri = getOutputMediaFileUri (MEDIA_TYPE_IMAGE);
		//intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		File sitaFolder = new File(Environment.getExternalStorageDirectory(),"SITA/" + (Integer.toString(GlobalVars.school_id))+"_"+Long.toString(GlobalVars.date_time));
		sitaFolder.mkdirs();
		String imagename = Integer.toString(GlobalVars.school_id)+"_"+ Long.toString(System.currentTimeMillis()/1000)+".jpg";
		
		File image = new File(sitaFolder, imagename);
		
		Uri uriSavedImage = Uri.fromFile(image);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
		
		
		startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // if the result is capturing Image
	    if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // successfully captured the image
	            // display it in image view
	          //  previewCapturedImage();
	        	// gallery has to load all images from directory
	        } else if (resultCode == RESULT_CANCELED) {
	            // user cancelled Image capture
	            Toast.makeText(getApplicationContext(),
	                    "User cancelled image capture", Toast.LENGTH_SHORT)
	                    .show();
	        } else {
	            // failed to capture image
	            Toast.makeText(getApplicationContext(),
	                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
	                    .show();
	        }
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_picture, menu);
		return true;
	}

}
