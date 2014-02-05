/*Final Project
 SignUp.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

	EditText etFullName,etUid,etPwd,etRePwd;
	Button btnRegister;
	static String strGetUid;
	static int iFullName;	
	static int iPwd;
	static int iPwdSize;
	static int iUidSize;
	UserDetails user;
	List<ParseObject> ob;
	ArrayList<UserDetails> alUser=new ArrayList<UserDetails>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sign_up);
		etFullName=(EditText)findViewById(R.id.etSuFullName);
		
		etUid=(EditText)findViewById(R.id.etSuUid);
		etPwd=(EditText)findViewById(R.id.etSuPwd);
		etRePwd=(EditText)findViewById(R.id.etSuRePwd);
		btnRegister=(Button)findViewById(R.id.btnSuSubmit);
		btnRegister.setOnClickListener(new View.OnClickListener() 
		{
			
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View v) 
			{
				iFullName=0;	
			iPwd=0;
			iPwdSize=0;
			iUidSize=0;
				String pwd1=etPwd.getText().toString();
				String pwd2=etRePwd.getText().toString();
				
				
				
				if(!(etFullName.getText().length()==0))
					iFullName=1;
				else etFullName.setError("Please enter your name");
				
				if((etUid.getText().length()>3))
					iUidSize=1;
				else
					etUid.setError("User Name should have more than 3 characters");
				
				
				if(pwd1.length()>6)
				{
						iPwdSize=1;
						if(pwd1.equals(pwd2))
							iPwd=1;
						else
							etPwd.setError("Passwords do not match");
							
						
				}
				else
					etPwd.setError("Password should be greater than 6 characters");
				
					
					
				if((iFullName==1)&&(iUidSize==1)&&(iPwd==1)&&(iPwdSize==1))
				{
					
					//String strFullName=etFullName.getText().toString();
					String strUid=etUid.getText().toString().toUpperCase();
					//String strPwd=etPwd.getText().toString();
					alUser.clear();
					new asyncValidateId().execute(strUid);
					
					/*ParseObject userOne = new ParseObject("UserOne");
					
					
					userOne.put("FullName", strFullName);
					userOne.put("UserID", strUid);
					userOne.put("Password", strPwd);
					
					userOne.saveInBackground();*/
					//new RemoteDataTask().execute();
					
				}
				
				
				
					//Toast.makeText(SignUp.this,"NOOO" ,Toast.LENGTH_SHORT).show();
				
			}
		
			
		});
	
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	/*public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	          "\\@" +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	          "(" +
	          "\\." +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	          ")+"
	      );*/
	private class asyncValidateId extends AsyncTask<String, Void, ArrayList<UserDetails>> {
        
		
		 /*checks the existance of the userid*/
        protected ArrayList<UserDetails> doInBackground(String... params) {
            // Locate the class table named "Country" in Parse.com
        	String uid;
        	
        	uid=params[0].toUpperCase().trim();
        
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "UserDetails");
            query.whereEqualTo("UserID", uid);
            
            try {
                ob = query.find();
                //Log.d("ob", String.valueOf(ob));
        //		Toast.makeText(SignUp.this, String.valueOf(ob)+ " ", Toast.LENGTH_LONG).show();

                user=new UserDetails();
                for (ParseObject userOne : ob) {
                	
                	user.setUserId((String) userOne.get("UserID"));
                	//user.setPwd((String) userOne.get("Password"));
                    //lob.add((String) userOne.get("Password"));
                	alUser.add(user);
                }
                
        		//Toast.makeText(LogIn.this, lob.size()+" ", Toast.LENGTH_LONG).show();
        		
        		
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            } catch (com.parse.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return alUser;
        }
 
       
		protected void onPostExecute(ArrayList<UserDetails> result) {
			int flag; 
           result=alUser;
           if(alUser.size()==0){
        	   
        	   flag=0;
           }
           else{
        	   flag=1;
        	  
            
            
           }  
           if(flag==0){
        	   ParseObject userOne = new ParseObject("UserDetails");
				
				
				userOne.put("FullName", etFullName.getText().toString().trim());
				Log.d("FULLNAME",etUid.getText().toString());
				userOne.put("UserID", etUid.getText().toString().toUpperCase().trim());
				userOne.put("Password", etPwd.getText().toString().trim());
				
				userOne.saveInBackground();
				String FILE_NAME = "filename";
				SharedPreferences.Editor editor=getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
				editor.putString("USERID", etUid.getText().toString().toUpperCase().trim());
				 editor.putString("PASSWORD", etPwd.getText().toString());
				 editor.putString("FULLNAME", etFullName.getText().toString().trim());
				 
				 ArrayList<Params_Serial> parm = new ArrayList<Params_Serial>();
				 try {
         			editor.putString("WISH", ObjectSerializer.serialize(parm));
         		} catch (IOException e) {
         			e.printStackTrace();
         		}
         		editor.commit();
				Toast.makeText(SignUp.this, "SUCCESS SIGN UP", Toast.LENGTH_LONG).show();
				//Intent intent=new Intent(SignUp.this,Test.class);
				Intent intent=new Intent(SignUp.this,LogIn.class);
				startActivity(intent);
				finish();
           }
           else{
        	   Toast.makeText(SignUp.this, "UserId already exists. Please choose another ID", Toast.LENGTH_LONG).show();
        	   String strMsg="UserId already exist";
           	//Toast.makeText(LogIn.this, "SUCCESS", Toast.LENGTH_LONG).show();
              	//Toast.makeText(LogIn.this,strMsg+". Please try again",Toast.LENGTH_LONG).show();
               //etPwd.setText("");
               etUid.setText("");
               etUid.setError(strMsg);
        	   
           }
            
          
        }
    }
	
	 
}


