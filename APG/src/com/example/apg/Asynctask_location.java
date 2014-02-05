/*Final Project
 Asynctask_location.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

public class Asynctask_location extends AsyncTask<String, Void, String> {

Context cont;
ListView listview;
GoogleMap map;
TextView textview;
protected MainActivity1 context;
Double latitude, longitude;
int check;

public Asynctask_location(Context context,GoogleMap gmap,Double lat,Double lng, int check){
	super();
	// TODO Auto-generated constructor stub
	cont = context;
	map = gmap;
	latitude =lat;
	longitude = lng;
	this.check=check;
}
public Asynctask_location(Context context,ListView list,Double lat,Double lng,int check){
	super();
	// TODO Auto-generated constructor stub
	cont = context;
	listview =list;
	latitude =lat;
	longitude = lng;
	this.check=check;
}
@Override
protected String doInBackground(String... params) {
	// TODO Auto-generated method stub
	HttpGet httpGet = new HttpGet(params[0]);
    HttpClient client = new DefaultHttpClient();
    HttpResponse response;
    StringBuilder stringBuilder = new StringBuilder();

    try {
        response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream stream = entity.getContent();
        int b;
        while ((b = stream.read()) != -1) {
            stringBuilder.append((char) b);
        }
    } catch (ClientProtocolException e) {
        } catch (IOException e) {
    }

    JSONObject jsonObject = new JSONObject();
    try {
        jsonObject = new JSONObject(stringBuilder.toString());
    } catch (JSONException e) {
        e.printStackTrace();
    }
    JSONObject location = null;
	try {
		location = jsonObject.getJSONArray("results").getJSONObject(0);
		JSONObject name = location.getJSONArray("address_components").getJSONObject(3);
		String location_string = name.getString("short_name");
		return location_string;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

	return null;
}

@Override
protected void onPostExecute(String result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);
	//LayoutInflater inflater = (LayoutInflater) MainActivity.main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	Message msg = new Message();
	Bundle b= new Bundle();
	b.putString("TEXT", result);
	msg.setData(b);
	MainActivity1.hand.sendMessage(msg);
	//Log.d("check", String.valueOf(check));
	//Toast.makeText(cont, "address is "+result, Toast.LENGTH_LONG).show();
//	final LinearLayout yourLayout = (LinearLayout) inflater.inflate(R.id.linearlayout,null);
	//TextView myText = (TextView) yourLayout.findViewById(R.id.textView1);
	//MainActivity.main.listview.setText(result+" not your location? Please select your city below");
	//new AsynctaskList(cont, listview,map).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&&division_id="+result);
    if (check==0)
	new AsynctaskList(cont, listview,latitude,longitude,check,0).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&lat="+latitude+"&lng="+longitude+"&radius=30");
    else
    new AsynctaskList(cont, map,latitude,longitude,check,0).execute("http://api.groupon.com/v2/deals.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11&lat="+latitude+"&lng="+longitude+"&radius=30");
	
}

}