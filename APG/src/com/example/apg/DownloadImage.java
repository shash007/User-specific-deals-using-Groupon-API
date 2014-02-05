/*Final Project
 Downloadimage.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;

import com.example.apg.MovieAdapter.ViewHolder;
import com.example.apg.MovieAdapter1.ViewHolder1;


public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

	private int mPosition;
	private ViewHolder mHolder;
	private ViewHolder1 mHolder1;
	Context context;
	public Activity activity; 
	View view;
	int check;

	public DownloadImage( Activity _activity){

		   this.activity = _activity;
		//other initializations...

		}

	
	public DownloadImage(int position, ViewHolder holder, Context context,View view,int check) {
		// TODO Auto-generated constructor stub
		mPosition = position;
		mHolder = holder;
		this.context = context;
		this.view =view;
		this.check =check;
		
	}
	
	public DownloadImage(int position, ViewHolder1 holder, Context context,View view,int check) {
		// TODO Auto-generated constructor stub
		mPosition = position;
		mHolder1 = holder;
		this.context = context;
		this.view =view;
		this.check=check;
		
	}

	protected Bitmap doInBackground(String... params) {

		URL aURL;
		Bitmap bm = null;
		URLConnection conn;
		try {
			aURL = new URL(params[0]);
			conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
			// bm;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;
		return bm;
	}

	@SuppressLint("NewApi")
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (check==0)
		{
		if (mHolder.position == mPosition) {
			//mHolder.imageview1.setImageBitmap(result);
			//@SuppressWarnings("deprecation")
			//BitmapDrawable draw = new BitmapDrawable(result);
			//RelativeLayout relative = (RelativeLayout)this.activity.findViewById(R.id.relativelayout);
			//relative.setBackgroundDrawable(draw);
			@SuppressWarnings("deprecation")
			Drawable draw = new BitmapDrawable(result);
			draw.setAlpha(90);
			view.setBackground(draw);
		}
		}
		else
		{
			if (mHolder1.position == mPosition) {
				//mHolder.imageview1.setImageBitmap(result);
				//@SuppressWarnings("deprecation")
				//BitmapDrawable draw = new BitmapDrawable(result);
				//RelativeLayout relative = (RelativeLayout)this.activity.findViewById(R.id.relativelayout);
				//relative.setBackgroundDrawable(draw);
				@SuppressWarnings("deprecation")
				Drawable draw = new BitmapDrawable(result);
				view.setBackground(draw);
			}
		}
		
	}
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		// progressDialog.show();
	}
}
