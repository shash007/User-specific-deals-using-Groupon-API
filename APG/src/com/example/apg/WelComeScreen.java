/*Final Project
 * WelComeScreen.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;


import com.parse.Parse;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WelComeScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "TJGwr1qB9XVQHiQGnmgViGyeoHoj1vlbfvkUIpvy", "YQP9AA3TYKT5uUESg2WRr849AnV2uAhTjzzWQO84");
		setContentView(R.layout.activity_wel_come_screen);
		 
		final int interval = 2000; // 1 Second
		Handler handler = new Handler();
		 Runnable runnable = new Runnable(){
		    public void run() {
		    	
				Intent intent=new Intent(WelComeScreen.this, LogIn.class);
				startActivity(intent);
				finish();
		    }
		};
		handler.postAtTime(runnable, System.currentTimeMillis()+interval);
		handler.postDelayed(runnable, interval);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wel_come_screen, menu);
		return true;
	}

}
