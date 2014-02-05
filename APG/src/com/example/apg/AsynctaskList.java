/*Final Project
 AsynctaskList.java
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class AsynctaskList extends AsyncTask<String, Void, ArrayList<Params>> {
	public static ArrayList<Params> param = new ArrayList<Params>();
	public static ArrayList<Params> resultList = new ArrayList<Params>();

	ListView listview;
	String url;
	Editor editor;
	Context currentContext = null;
	ProgressDialog progressDialog;
	GoogleMap mMap;
	Double lat,lng;
	Activity act;
	List<ParseObject> pdel;
	List<ParseObject> ob ;
	ArrayList<String> alWIsh;
	static Handler handler1;
	 ArrayList<Divisions>  res;
	 int check;
	 int check2;
	 Params_Serial dbs;
	 ArrayList<Params_Serial> dbresult =new ArrayList<Params_Serial>();
	 UserDetails user;
     ArrayList<UserDetails> alUser=new ArrayList<UserDetails>();
		String FILE_NAME= "filename";
		SharedPreferences prefs; 
		String restoredUid="";
		


	public AsynctaskList(Context context, ListView list,Double lat,Double lng,int check,int check2) {
		currentContext = context;
		listview = list;
		this.lat = lat;
		this.lng =lng;
		this.check =check;
		this.check2 =check2;
	}

	public AsynctaskList(Context context,GoogleMap mmap ,Double lat,Double lng,int check,int check2) {
		currentContext = context;
		this.mMap=mmap;
		this.lat = lat;
		this.lng =lng;
		this.check =check;
		this.check2 =check2;
		
	}
	
	public AsynctaskList(Context context, ListView list,Activity act) {
		currentContext = context;
		listview = list;
		this.act = act;
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
			
			prefs = currentContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE); 
			
			restoredUid = prefs.getString("USERID", null);
			editor =prefs.edit();
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

	@SuppressWarnings("unchecked")
	protected void onPostExecute(ArrayList<Params> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		progressDialog.dismiss();
		resultList = result;
		ListView_Deals1.setResult(result);
		Log.d("check1", String.valueOf(check));
		if (check==0)
		{
			if(check2==0)
			{

			 MovieAdapter adapter = new
			  MovieAdapter(currentContext, R.layout.activity_list_view__deals1, ListView_Deals1.getResult());
			  listview.setAdapter(adapter); adapter.notifyDataSetChanged();
			  listview.setBackgroundColor(Color.TRANSPARENT);
			  listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(currentContext, WebActivity.class);
					 intent.putExtra("WEB", resultList.get(arg2).getDealurl());
					 currentContext.startActivity(intent);
				}
			});
			  
			  listview.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					int id = resultList.get(arg2).getId();
					String uid =String.valueOf(id);
					Log.d("uid", uid);
					//Toast.makeText(currentContext, uid, Toast.LENGTH_LONG).show();
					 ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
							 restoredUid.toUpperCase().trim());
			            query.whereEqualTo("WISHLIST", uid);
			        	
			        	
                        try {
							ob =query.find();
							alWIsh=new  ArrayList<String>();
							//user=new UserDetails();
			                for (ParseObject userOne : ob) {
			                	
			                	

			                	//user.setPwd((String) userOne.get("Password"));
			                	alWIsh.add(userOne.toString());
			                    //lob.add((String) userOne.get("Password"));
			                	//alUser.add(user);
			               }
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        if(alWIsh.size()==0)
                        {
                        	try {
                                dbresult = (ArrayList<Params_Serial>) ObjectSerializer.deserialize(prefs.getString("WISH", ObjectSerializer.serialize(new ArrayList<Params_Serial>())));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        	dbs = new Params_Serial(resultList.get(arg2).getImageurl(), resultList.get(arg2).getTitle(), resultList.get(arg2).getEndtime(), resultList.get(arg2).dealurl, resultList.get(arg2).original_price, resultList.get(arg2).discount_price, resultList.get(arg2).remaining_quan, id, resultList.get(arg2).dis_percent);	
                        	dbresult.add(dbs);
                        	try {
                    			editor.putString("WISH", ObjectSerializer.serialize(dbresult));
                    		} catch (IOException e) {
                    			e.printStackTrace();
                    		}
                    		editor.commit();
                        	
        					Toast.makeText(currentContext, "Deal"+  " added to wish list", Toast.LENGTH_LONG).show();
        					String tblName=restoredUid.toUpperCase().trim();
        			       	String grouponId=uid.trim();
        			       	ParseObject userOne = new ParseObject(tblName);
        					
        					
        					userOne.put("WISHLIST", grouponId);
        					//Log.d("GROUPON",grouponId);
        					//userOne.put("UserID", etUid.getText().toString().toUpperCase().trim());
        					//userOne.put("Password", etPwd.getText().toString().trim());
        					//Log.d("dbresult",dbresult.toString());
        					userOne.saveInBackground();
                        }
                        else
                        {
                        	try {
                                dbresult = (ArrayList<Params_Serial>) ObjectSerializer.deserialize(prefs.getString("WISH", ObjectSerializer.serialize(new ArrayList<Params_Serial>())));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
        					Toast.makeText(currentContext, "Deal deleted from wishlist", Toast.LENGTH_LONG).show();
        					String tblName=restoredUid.toUpperCase().trim();
        			       	String grouponId=uid.trim();
        			       	ParseQuery<ParseObject> queryDel = new ParseQuery<ParseObject>(
        			                tblName);
        			        queryDel.whereEqualTo("WISHLIST",grouponId);
        			        
        			        try {
        			            pdel = query.find();
        			            
        			            }catch(Exception c){
        			            	c.printStackTrace();
        			            }
        			        try {
        						ParseObject.deleteAll(pdel);
        					} catch (com.parse.ParseException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        					//userOne.saveInBackground();
        					 
        			       	//ParseObject userOne = new ParseObject(tblName);
                            // ParseObject userOne1 = new ParseObject(tblName);
        					
                            // userOne1.remove(grouponId);
        					//userOne1.deleteInBackground();
        					//Log.d("dbresult",dbresult.toString());
        					//userOne.put("WISHLIST", grouponId);
        					
        					
        					for(int j = 0; j < dbresult.size(); j++)
        					{
        					    Params_Serial obj = dbresult.get(j);

        					    if(obj.getId()==id){
        					       //found, delete.
        					        dbresult.remove(j);
        					        break;
        					    }

        					}
        					try {
                    			editor.putString("WISH", ObjectSerializer.serialize(dbresult));
                    		} catch (IOException e) {
                    			e.printStackTrace();
                    		}
                    		editor.commit();
                        }
					return false;
				}
			});
			  
			}
			else
			{
				Message msg = new Message();
				Bundle b= new Bundle();
				b.putParcelableArrayList("DATA", result);
				//Log.d("data", String.valueOf(result.size()));
				msg.setData(b);
				ListView_Deals1.handler.sendMessage(msg);
			}
			
		}
		else
		{
			if(check2==0)
			{
				for(int i=0;i<result.size();i++)
				{
					if((!(result.get(i).getRedemption_loc().equals("Online Deal")))&&
						(!(result.get(i).getRedemption_loc().equals("On Location"))))
					{
						Double distanc = distance(lat,lng,result.get(i).getLat(),result.get(i).getLng());
					    DecimalFormat myFormatter = new DecimalFormat("00.00");
						String dist = myFormatter.format(distanc);
					mMap.addMarker(new MarkerOptions()	
			 	     .position(new LatLng(result.get(i).getLat(),result.get(i).getLng()))	
			 	     .title(result.get(i).getTitle())
					.snippet(dist+ " Miles Away")
	                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
					  
					mMap.addMarker(new MarkerOptions()	
				 	     .position(new LatLng(lat,lng))	
				 	    .title("You Are Here"));
					}
					mMap.animateCamera(	
				   			 CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 9));
					
					mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
						
						@Override
						public void onInfoWindowClick(Marker arg0) {
							// TODO Auto-generated method stub
							LatLng lt = arg0.getPosition(); Double lat = lt.latitude; Double lng = lt.longitude; 
							for(int i=0;i<resultList.size();i++) 
							{ 
								if(lat==resultList.get(i).getLat())
									if(lng==resultList.get(i).getLng()) 
									{ 
										url=resultList.get(i).getDealurl();
										}
								}
							//Intent inte = new Intent(currentContext, WebActivity2.class);
							//inte.putExtra("LINK", url); 
							//currentContext.startActivity(inte);   /*Intent detail = new Intent(currentContext, DetailedActivity.class); detail.putParcelableArrayListExtra("PARCEL",ListView_Deals.getResult() ); detail.putExtra("POS", arg2); currentContext.startActivity(detail);*/ ;
						}
					});
				
			}
				
			}
			else
			{
				Message msg = new Message();
				Bundle b= new Bundle();
				b.putParcelableArrayList("DATA", result);
				Log.d("data", String.valueOf(result.size()));
				msg.setData(b);
				GoogleMaps1.handl.sendMessage(msg);
			}
			
		}
		/*
		for(int i=0;i<result.size();i++)
		{
			if((!(result.get(i).getRedemption_loc().equals("Online Deal")))&&
				(!(result.get(i).getRedemption_loc().equals("On Location"))))
			{
				Double distanc = distance(lat,lng,result.get(i).getLat(),result.get(i).getLng());
				String dist = String.valueOf(distanc);
			mMap.addMarker(new MarkerOptions()	
	 	     .position(new LatLng(result.get(i).getLat(),result.get(i).getLng()))	
	 	     .title(result.get(i).getTitle())
			.snippet(dist+ " Miles Away"));
			}
			
		//	Log.d("test", String.valueOf(result.get(i).getLat()) );
		//	Log.d("test1", String.valueOf(result.get(i).getLng()) );
		}
		
		String SHARED_PREFS_FILE ="file";
        SharedPreferences prefs = currentContext.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        try {
          res = (ArrayList<Divisions>) ObjectSerializer.deserialize(prefs.getString("TASKS", ObjectSerializer.serialize(new ArrayList<Divisions>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("value", String.valueOf(res.size()));
        if(res!= null)
        {
	 				for(int i=0;i<res.size();i++)
	 				{
	 				mMap.addMarker(new MarkerOptions()
                 .position(new LatLng(res.get(i).getLat(), res.get(i).getLng()))
                 .title(res.get(i).getName())
                 .snippet("Population: 4,137,400")
                 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	 				}
        }
	 		
	 */
		
		
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

	}

	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog = new ProgressDialog(currentContext);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Loading Deals");
		progressDialog.show();
	}
	
	static double distance(double lat1, double lon1, double lat2, double lon2) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      return (dist);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private static double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private static double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }

}
