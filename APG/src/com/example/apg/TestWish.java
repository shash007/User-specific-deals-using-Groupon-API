/*Final Project
 TestWish.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TestWish extends Activity {

	ArrayList<Params_Serial> parm =new ArrayList<Params_Serial>();
	ListView listview;
	List<ParseObject> pdel;
	List<ParseObject> ob ;
	ArrayList<String> alWIsh;
	  ArrayList<UserDetails> alUser=new ArrayList<UserDetails>();
			String FILE_NAME= "filename";
			SharedPreferences prefs; 
			String restoredUid="";
			 ArrayList<Params_Serial> dbresult =new ArrayList<Params_Serial>();
				Editor editor;
				MovieAdapter1 adapter;

				@SuppressLint("DefaultLocale")
				@SuppressWarnings("unchecked")
				public boolean onOptionsItemSelected(MenuItem item) {
					switch(item.getItemId()){
					
					case R.id.iWishList:
						String FILE_NAME = "filename";
						prefs = getSharedPreferences(FILE_NAME,MODE_PRIVATE); 
						
						restoredUid = prefs.getString("USERID", null);
						editor =prefs.edit();
						try {
				            parm = (ArrayList<Params_Serial>) ObjectSerializer.deserialize(prefs.getString("WISH", ObjectSerializer.serialize(new ArrayList<Params_Serial>())));
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						for (int i=0;i<parm.size();i++)
						{
							int id = parm.get(i).getId();
							String uid =String.valueOf(id);
							restoredUid = prefs.getString("USERID", null);
							//Toast.makeText(currentContext, uid, Toast.LENGTH_LONG).show();
							 ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
									 restoredUid.toUpperCase().trim());
					            query.whereEqualTo("WISHLIST", uid);
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
						}
						parm.clear();
						listview = (ListView)findViewById(R.id.listView2);
				        adapter = new MovieAdapter1(this,R.layout.activity_test_wish , parm);
				        listview.setAdapter(adapter);
				        try {
                			editor.putString("WISH", ObjectSerializer.serialize(parm));
                		} catch (IOException e) {
                			e.printStackTrace();
                		}
                		editor.commit();
				    	
						Toast.makeText(this, "All Deals Deleted from WishList", Toast.LENGTH_LONG).show();
						break;
						
					
					}
					// TODO Auto-generated method stub
					return super.onOptionsItemSelected(item);
				}
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_wish);
		OptionsMenu();
		String FILE_NAME = "filename";
		prefs = getSharedPreferences(FILE_NAME,MODE_PRIVATE); 
		
		restoredUid = prefs.getString("USERID", null);
		editor =prefs.edit();
		try {
            parm = (ArrayList<Params_Serial>) ObjectSerializer.deserialize(prefs.getString("WISH", ObjectSerializer.serialize(new ArrayList<Params_Serial>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
		if(parm.isEmpty())
		{
			Toast.makeText(this, "Your Wishlist is empty", Toast.LENGTH_LONG).show();
		}
		else
		{
			  listview = (ListView)findViewById(R.id.listView2);
		        adapter = new MovieAdapter1(this,R.layout.activity_test_wish , parm);
		        listview.setAdapter(adapter);
		        listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(TestWish.this, WebActivity.class);
						 intent.putExtra("WEB", parm.get(arg2).getDealurl());
						 TestWish.this.startActivity(intent);
					}
				});
		        
		        listview.setOnItemLongClickListener(new OnItemLongClickListener() {

					@SuppressLint("DefaultLocale")
					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						
						int id = parm.get(arg2).getId();
						String uid =String.valueOf(id);
						String tblName=restoredUid.toUpperCase().trim();
    			       	String grouponId=uid.trim();
    			       	ParseQuery<ParseObject> queryDel = new ParseQuery<ParseObject>(
    			                tblName);
    			        queryDel.whereEqualTo("WISHLIST",grouponId);
    			        
    			        try {
    			            pdel = queryDel.find();
    			            
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
    					
    					
    					for(int j = 0; j < parm.size(); j++)
    					{
    					    Params_Serial obj = parm.get(j);

    					    if(obj.getId()==id){
    					       //found, delete.
    							Toast.makeText(TestWish.this, "Deal deleted from wishlist", Toast.LENGTH_SHORT).show();
    					        parm.remove(j);
    					        if(parm.isEmpty())
    							{
    								Toast.makeText(TestWish.this, "Your Wishlist is empty", Toast.LENGTH_LONG).show();
    							}
    					        adapter.notifyDataSetChanged();
    					        break;
    					    }

    					}
    					try {
                			editor.putString("WISH", ObjectSerializer.serialize(parm));
                		} catch (IOException e) {
                			e.printStackTrace();
                		}
                		editor.commit();
                    
						return false;
					}
				});
		}
      
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_wish, menu);
		return true;
	}
	@SuppressWarnings("unused")
	private class asyncAddToWishList extends AsyncTask<String, Void, Void> {
        
		
		 /*checks the existance of the userid*/
       @SuppressLint("DefaultLocale")
	protected Void doInBackground(String... params) {
           // Locate the class table named "Country" in Parse.com
       	String uid,tblName,grouponId;
       	
       	tblName=uid=params[0].toUpperCase().trim();
       	grouponId=params[1].trim();
       	ParseObject userOne = new ParseObject(tblName);
		
		
		userOne.put("WISHLIST", grouponId);
		//Log.d("GROUPON",grouponId);
		//userOne.put("UserID", etUid.getText().toString().toUpperCase().trim());
		//userOne.put("Password", etPwd.getText().toString().trim());
		
		userOne.saveInBackground();
		
		 
		// editor.putInt("selection-end", mSaved.getSelectionEnd());
		
		
		//Toast.makeText(TestWish.this, "SUCCESSFULLY ADDED", Toast.LENGTH_LONG).show();
		//Intent intent=new Intent(SignUp.this,Test.class);
		//Intent intent=new Intent(SignUp.this,LogIn.class);
       	return null;
           
       }

      
		protected void onPostExecute() {
			
       	   
			Toast.makeText(TestWish.this, "SUCCESSFULLY ADDED", Toast.LENGTH_LONG).show();
           
         
       }
   }
	
	public void OptionsMenu()
	{ 
		try
		{ 
			ViewConfiguration config = ViewConfiguration.get(this); 
			java.lang.reflect.Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey"); 
					//ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey"); 
			if (menuKeyField != null) 
			{
				menuKeyField.setAccessible(true); 
				menuKeyField.setBoolean(config, false);
			} 
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		} 
	}
	
}
