/*Final Project
 Login.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person.Image;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LogIn extends Activity {

	EditText etUid, etPwd;
	Button btnLogIn,btnSignUp,btnTestButton;
	List<ParseObject> ob;
	static String strUid,strPwd;
	static String strGetUid,  strGetPwd;
	UserDetails user;
	boolean bVal;
	RelativeLayout rl;
	String strnew;
	 ArrayList<UserDetails> lob=new ArrayList<UserDetails>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Parse.initialize(this, "TJGwr1qB9XVQHiQGnmgViGyeoHoj1vlbfvkUIpvy", "YQP9AA3TYKT5uUESg2WRr849AnV2uAhTjzzWQO84");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		rl=(RelativeLayout)findViewById(R.id.rlLi);
		rl.setBackgroundResource(R.drawable.newone);//Image i=ImageReader(R.drawable.bg);
		
		
		
		etUid=(EditText)findViewById(R.id.etLiUid);
		etPwd=(EditText)findViewById(R.id.etLiPwd);
		//etPwd.setEnabled(false);
		btnLogIn=(Button)findViewById(R.id.btnLiLogIn);
		btnSignUp=(Button)findViewById(R.id.btnLiSignUp);
		
		String FILE_NAME = "filename";
		SharedPreferences prefs = getSharedPreferences(FILE_NAME,MODE_PRIVATE); 
		String restoredUid = prefs.getString("USERID", null);
		String restoredPwd=prefs.getString("PASSWORD", null);
		String restoredFullName=prefs.getString("FULLNAME", null);
		//Toast.makeText(this, restoredPwd, Toast.LENGTH_LONG).show();
		if ((restoredUid != null)&&(restoredPwd!=null)&&(restoredFullName!=null))
		{
			Intent intent=new Intent(LogIn.this,MainActivity1.class);
			startActivity(intent);
			finish();
		 
			//Toast.makeText(LogIn.this, restoredText.toString()+"", Toast.LENGTH_LONG).show();
			//etUid.setText(restoredText.toString());
			//etPwd.setText(prefs.getString("PASSWORD", null));
		}
		else{
			//Toast.makeText(LogIn.this, "NULLLLLLLLLLLL", Toast.LENGTH_LONG).show();
		}

		
		
		
		
		/*REGISTER*/
		btnLogIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				strUid=etUid.getText().toString();
				strPwd=etPwd.getText().toString();
				
				String[] p={strUid,strPwd};
				lob.clear();
				//validateUser(strUid, strPwd);
				if((strUid!=null)&&(strPwd!=null))
				new RemoteDataTask().execute(p);
				else
					etUid.setError("Enter a valid userId");
				
				
			}
		});
		/*SIGN UP*/
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent intent=new  Intent(LogIn.this,SignUp.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		
	}
	
	

	@SuppressWarnings("unused")
	private Image ImageReader(int bg) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	private class RemoteDataTask extends AsyncTask<String, Void, ArrayList<UserDetails>> {
        
		
 
        protected ArrayList<UserDetails> doInBackground(String... params) {
            // Locate the class table named "Country" in Parse.com
        	String uid;
        	uid=params[0].toUpperCase().trim();
        	strGetPwd=params[1];
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "UserDetails");
            query.whereEqualTo("UserID", uid);
            
            try {
                ob = query.find();
                user=new UserDetails();
                for (ParseObject userOne : ob) {
                	
                	user.setUserId((String) userOne.get("UserID"));
                	user.setPwd((String) userOne.get("Password"));
                	user.setFullName((String)userOne.get("FullName"));
                    //lob.add((String) userOne.get("Password"));
                	lob.add(user);
                }
                
        		//Toast.makeText(LogIn.this, lob.size()+" ", Toast.LENGTH_LONG).show();
        		
        		
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            } catch (com.parse.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return lob;
        }
 
       
		protected void onPostExecute(ArrayList<UserDetails> result) {
            
           result=lob;
           if((lob.size()!=0)&&(lob.get(0).getPwd().equals(strGetPwd))){
        	   Toast.makeText(LogIn.this, "Successful Log In", Toast.LENGTH_LONG).show();
        	   String FILE_NAME = "filename";
				SharedPreferences.Editor editor=getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
				editor.putString("USERID", etUid.getText().toString().toUpperCase().trim());
				 editor.putString("PASSWORD", etPwd.getText().toString());
				 editor.putString("FULLNAME", lob.get(0).getFullName().toString());
				 editor.commit();
				 Intent intent=new Intent(LogIn.this,MainActivity1.class);
				 startActivity(intent);
				 finish();
           }else{
        	   String strMsg="UserId doesnt exist or wrong Password";
        	//Toast.makeText(LogIn.this, "SUCCESS", Toast.LENGTH_LONG).show();
           	Toast.makeText(LogIn.this,strMsg+". Please try again",Toast.LENGTH_LONG).show();
            etPwd.setText("");
            etUid.setText("");
            etUid.setError(strMsg);
            etPwd.setError(strMsg);
            
           }         
            
          
        }
    }
}


