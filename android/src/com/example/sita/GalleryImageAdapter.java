package com.example.sita;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class GalleryImageAdapter extends BaseAdapter {
	
	private Context mContext;
	private Integer[] imageIds={
		R.drawable.ic_launcher,
		R.drawable.test
	};
	
	public GalleryImageAdapter(Context context)
	{
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ImageView i = new ImageView(mContext);
		i.setImageResource(imageIds[position]);
		i.setLayoutParams(new Gallery.LayoutParams(200,200));
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		
		return i;
	}
	

}
