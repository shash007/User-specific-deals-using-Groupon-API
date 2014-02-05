/*Final Project
 JSONparsing.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONparsing 
{
	static public class JSONParser
	{

		static ArrayList<Params> parseParams(String jsonString) throws JSONException 
		{
			Double lat = null,lng=null;
			ArrayList<Params> Params = new ArrayList<Params>();	
			JSONArray personsJSONArray = null;
			Params param = null;
			JSONObject jsonobject = new JSONObject(jsonString);

			personsJSONArray = jsonobject.getJSONArray("deals");

			Log.d("json10",String.valueOf(personsJSONArray.length()));
			//personsJSONArray = new JSONArray(jsonString);
			for(int i=0; i<personsJSONArray.length(); i++)
			{
				JSONObject personJSONObject = null;
				personJSONObject = personsJSONArray.getJSONObject(i);
				String imageurl = personJSONObject.optString("largeImageUrl");
				String title = personJSONObject.optString("announcementTitle");
			    String starttime = personJSONObject.optString("startAt"); 
			    String endtime = personJSONObject.optString("endAt"); 
			    String dealurl = personJSONObject.optString("dealUrl"); 
			    Log.d("test", title.toString());
			    try{
			    	lat =personJSONObject.getJSONArray("options").getJSONObject(0).getJSONArray("redemptionLocations").getJSONObject(0).optDouble("lat");
				    lng= personJSONObject.getJSONArray("options").getJSONObject(0).getJSONArray("redemptionLocations").getJSONObject(0).optDouble("lng");	
			    }
			    catch (JSONException ignored) {

			    if (lat == null) {
			      
			    	lat = personJSONObject.getJSONObject("division").optDouble("lat");
			    }
			   
			    if (lng == null) {
				      
			    	lng = personJSONObject.getJSONObject("division").optDouble("lng");
			    }
			    
			    }
			    
			    String status =personJSONObject.optString("status");
			    
			    String original_price = personJSONObject.getJSONArray("options")
						.getJSONObject(0).getJSONObject("value").optString("formattedAmount");
			    String redemption_loc = personJSONObject.optString("redemptionLocation");
			    Log.d("test1",redemption_loc );
			    String discount_price =personJSONObject.getJSONArray("options")
						.getJSONObject(0).getJSONObject("price").optString("formattedAmount");
				int remaining_quan = personJSONObject.getJSONArray("options").getJSONObject(0).optInt("remainingQuantity");
				int id = personJSONObject.getJSONArray("options").getJSONObject(0).optInt("id");
				String dis_percent = personJSONObject.getJSONArray("options").getJSONObject(0).optString("discountPercent");
				String buy_url = personJSONObject.getJSONArray("options").getJSONObject(0).optString("buyUrl");
				param = new Params(imageurl, title, starttime, endtime, dealurl,lat,lng
				,original_price, discount_price,remaining_quan, id,dis_percent,buy_url,status,redemption_loc);
				Params.add(param);


			}

			Log.d("json11", Params.toString());
			return Params;

		}
	}
}
