/*Final Project
 MovieAdapter1.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieAdapter1 extends ArrayAdapter<Params_Serial>{

	Context context;
	int resource;
	ArrayList<Params_Serial> objects;
	Params_Serial param =null;
	TextView textview1;
	TextView textview2;
	TextView textview3;
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	static Handler mHandler;
	ViewHolder1 holder;

	public MovieAdapter1(Context context, int resource, ArrayList<Params_Serial> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context =context;
		this.resource = resource;
		this.objects = objects;
	}

	public static class ViewHolder1{

		TextView textview1;
		TextView textview2;
		//TextView textview3;
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
			holder = new ViewHolder1();
			//holder.imageview1 = (ImageView) convertView.findViewById(R.id.imageView1);
			holder.textview1 = (TextView) convertView.findViewById(R.id.textView1);
			holder.textview2 = (TextView) convertView.findViewById(R.id.textView2);
			convertView.setTag(holder);
		}

        
		holder = (ViewHolder1) convertView.getTag();
		param = (Params_Serial) objects.get(position);
		holder.position =position;
		String thumbimage = param.getImageurl();
		new DownloadImage(position,holder,context,convertView,1).execute(thumbimage);
		holder.textview1.setText(param.getTitle()+"");
		holder.textview2.setText(param.getOriginal_price() + "");


		return convertView;

	}



}

