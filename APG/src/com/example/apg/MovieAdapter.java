/*Final Project
 MovieAdapter.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */

package com.example.apg;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends ArrayAdapter<Params>{

	Context context;
	int resource;
	ArrayList<Params> objects;
	Params param =null;
	TextView textview1;
	TextView textview2;
	TextView textview3;
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	static Handler mHandler;
	ViewHolder holder;

	public MovieAdapter(Context context, int resource, ArrayList<Params> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context =context;
		this.resource = resource;
		this.objects = objects;
	}

	public static class ViewHolder{

		TextView textview1;
		TextView textview2;
		TextView textview3;
		ImageView imageview1;
		//ImageView imageview2;
		//ImageView imageview3;
		int position;


	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub


		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if(convertView == null)
		{

			convertView = mInflater.inflate(R.layout.layoutview, null);
			holder = new ViewHolder();
			//holder.imageview1 = (ImageView) convertView.findViewById(R.id.imageView1);
			holder.textview1 = (TextView) convertView.findViewById(R.id.textView1);
			holder.textview2 = (TextView) convertView.findViewById(R.id.textView2);
			holder.textview3 = (TextView) convertView.findViewById(R.id.textView3);
			convertView.setTag(holder);
		}

        
		holder = (ViewHolder) convertView.getTag();
		param = (Params) objects.get(position);
		holder.position =position;
		String thumbimage = param.getImageurl();
		new DownloadImage(position,holder,context,convertView,0).execute(thumbimage);

		holder.textview1.setTextColor(Color.BLACK);
		holder.textview1.setText(param.getTitle()+"");
		holder.textview3.setText(param.getDiscount_price()+"");
		holder.textview3.setTextColor(Color.YELLOW);
		holder.textview2.setText(param.getOriginal_price()+"");
		holder.textview2.setTextColor(Color.GREEN);
		holder.textview2.setPaintFlags(holder.textview3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

	/*	if(param.getStatus().equals("open"))
		{
			holder.textview2.setTextColor(Color.GREEN);
			holder.textview2.setText(param.getStatus() + "");

		}
		else
		{
			holder.textview2.setTextColor(Color.RED);
			holder.textview2.setText(param.getStatus() + "");

		}*/


		return convertView;

	}



}
