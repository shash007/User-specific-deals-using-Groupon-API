/*Final Project
 Asynctaskspinnersel.java
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
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;

public class Asynctaskspinnersel extends AsyncTask<String, Void, ArrayList<Params>> {
	public static ArrayList<Params> param = new ArrayList<Params>();
	public static ArrayList<Params> resultList = new ArrayList<Params>();

	ListView listview;
	Context currentContext = null;
	Activity act;
	ProgressDialog progressDialog;
	GoogleMap mMap;
	
	public Asynctaskspinnersel(Activity act) {
		this.act = act;
		
	}


	public Asynctaskspinnersel(Context context, ListView list, GoogleMap nMap) {
		currentContext = context;
		listview = list;
		this.mMap = nMap;
	}

	public Asynctaskspinnersel(Context context, ListView list) {
		currentContext = context;
		listview = list;
	}
	
	protected ArrayList<Params> doInBackground(String... params) {

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
			Log.d("reponse", response.toString());
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

			Log.d("json", sb.toString());

			param = JSONparsing.JSONParser.parseParams(sb.toString());
			// Log.d("demo1", movie.toString());
			Log.d("params", param.toString());
			return param;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	protected void onPostExecute(ArrayList<Params> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		progressDialog.dismiss();
		Message msg = new Message();
		Bundle b= new Bundle();
		b.putParcelableArrayList("DATA", result);
		Log.d("data", String.valueOf(result.size()));
		msg.setData(b);
		ListView_Deals1.handler.sendMessage(msg);
		}
		
		

		/*
		 listview.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 * arg2, long arg3) { // TODO Auto-generated method stub
		 * 
		 * int position= (int) arg0.getItemIdAtPosition(arg2); Intent
		 * movieactivity = new Intent(currentContext, MovieActivity.class); 
		 * movieactivity.putExtra(TITLE, title); movieactivity.putExtra(IMAGE,
		 * image); movieactivity.putExtra(CONCAT, concat);
		 * movieactivity.putExtra(AUDIENCE_RATINGS, audience_rating);
		 * movieactivity.putExtra(CRITICS_RATINGS, critics_rating);
		 * movieactivity.putExtra(AUDIENCE_IM, audience_image);
		 * movieactivity.putExtra(CRITICS_IM, critics_image);
		 
		
		 * movieactivity.putParcelableArrayListExtra(MoviesActivity.MOVIEINFO,
		 * resultList); movieactivity.putExtra(MoviesActivity.POSITION,
		 * position); currentContext.startActivity(movieactivity);
		 */
		// }

		// });

	

	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog = new ProgressDialog(currentContext);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Loading Deals");
		progressDialog.show();
	}
}

