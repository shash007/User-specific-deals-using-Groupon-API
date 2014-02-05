/*Final Project
 Asynctask_spinner.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
public class Asynctask_spinner extends AsyncTask<String, Void, ArrayList<Divisions>> {
	public static ArrayList<Divisions> divisions = new ArrayList<Divisions>();
	Divisions division;

	ListView listview;
	Context currentContext = null;
	ProgressDialog progressDialog;
	GoogleMap mMap;
	Spinner spinner;
	TabHost tab;

	public Asynctask_spinner(Context context,Spinner spinner,ListView list,TabHost tab)
	{
		currentContext = context;
		this.spinner =spinner;
		listview =list;
		this.tab = tab;
	}
	
	protected ArrayList<Divisions> doInBackground(String... params) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet();
			try {
				httpget.setURI(new URI(params[0]));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httpget);
			// String responseBody = EntityUtils.toString(response.getEntity());
			HttpEntity httpEntity = response.getEntity();
			//Log.d("reponse", response.toString());
			InputStream in = httpEntity.getContent();
			// InputStream in = con.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line = null;
			line = reader.readLine();
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			//Log.d("json", sb.toString());

			JSONObject jsonobject = new JSONObject(sb.toString());

			JSONArray personsJSONArray = jsonobject.getJSONArray("divisions");

			//personsJSONArray = new JSONArray(jsonString);
			for(int i=0; i<personsJSONArray.length(); i++)
			{
				JSONObject personJSONObject = null;
				personJSONObject = personsJSONArray.getJSONObject(i);
				String div_name = personJSONObject.optString("name");
				String div_id = personJSONObject.optString("id");
				Double lat = personJSONObject.optDouble("lat");
				Double lng = personJSONObject.optDouble("lng");
				division = new Divisions(div_name,div_id,lat,lng);
				divisions.add(division);
			}
				return divisions;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	protected void onPostExecute(ArrayList<Divisions> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//Log.d("res", String.valueOf(result.size()));
		ArrayList<String> divnames = new ArrayList<String>();
		divnames.add("Please Select City");
		
		String SHARED_PREFS_FILE = "file";
		SharedPreferences prefs = currentContext.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		try {
			editor.putString("TASKS", ObjectSerializer.serialize(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		editor.commit();
		
	
		
        for(int i=0;i<result.size();i++)
        {
        	String res = result.get(i).getName();
        	divnames.add(res);
        }
		//Log.d("res", String.valueOf(divnames.size()));
		
		CustomAdapter dataadapter = new CustomAdapter(currentContext, android.R.layout.simple_spinner_item,divnames,0);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(dataadapter);
	    
	    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				if(arg2!=0)
				{
					String id = divisions.get(arg2-1).getId();
					String title = divisions.get(arg2-1).getName();
					String PREF_FILE_NAME = "filename";
					SharedPreferences sharedPreferences = currentContext.getSharedPreferences(
							PREF_FILE_NAME, 0);
					Editor editor = sharedPreferences.edit();
					editor.putString("REFRESH", "TEST");
					editor.putString("SPINNER", "YES");
					editor.putString("ID", id);
					editor.apply();
					
					Message msg = new Message();
					Bundle b= new Bundle();
					b.putString("TEXT", title);
					msg.setData(b);
					MainActivity1.hand.sendMessage(msg);
					
					Double latitude = 10.2;
					Double longitude = 10.2;
					
				    if(tab.getCurrentTab()==0)
				    {
					 //   new Asynctaskspinnersel(currentContext, listview).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&division_id="+id);
						new AsynctaskList(currentContext, listview,latitude,longitude,0,1).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&division_id="+id);

				    }
				    else
				    {
					  //  new Asynctaskspinnersel(currentContext, listview).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&division_id="+id);
						new AsynctaskList(currentContext, listview,latitude,longitude,1,1).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&division_id="+id);

				    }
				   // new Asynctaskspinnersel(currentContext, listview).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&division_id="+id);
				}
			

			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	
	    
	}

	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	public class CustomAdapter extends ArrayAdapter<String> {

	     private int hidingItemIndex;

	     public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects, int hidingItemIndex) {
	         super(context, textViewResourceId, objects);
	         this.hidingItemIndex = hidingItemIndex;
	     }

	     @Override
	     public View getDropDownView(int position, View convertView, ViewGroup parent) {
	         View v = null;
	         if (position == hidingItemIndex) {
	             TextView tv = new TextView(getContext());
	             tv.setVisibility(View.GONE);
	             v = tv;
	         } else {
	             v = super.getDropDownView(position, null, parent);
	         }
	         return v;
	     }
	 }
}
