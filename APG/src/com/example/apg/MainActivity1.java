/*Final Project
 MainActivity1.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity1 extends TabActivity implements
OnTabChangeListener {
/** Called when the activity is first created. */
TabHost tHost;
static Activity main;
static ListView listview;
TextView tvName;
TextView textview;
static Handler hand;


/*Creating menu options*/
public boolean onOptionsItemSelected(MenuItem item) {
	switch(item.getItemId()){
	case R.id.iWishList:
		Intent intent=new Intent(MainActivity1.this,TestWish.class);
		startActivity(intent);
		break;
		
	
	}
	// TODO Auto-generated method stub
	return super.onOptionsItemSelected(item);
}

@SuppressLint({ "NewApi", "DefaultLocale" })
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main_activity1);
textview=(TextView)findViewById(R.id.textView1);
LinearLayout linear = (LinearLayout)findViewById(R.id.linearlayout);
linear.setBackgroundColor(Color.LTGRAY);
OptionsMenu();
String PREF_FILE_NAME = "filename";
String sName_capital;
SharedPreferences sharedPreferences = getSharedPreferences(
		PREF_FILE_NAME, 0);
Editor editor = sharedPreferences.edit();
editor.putString("SPINNER", "NO");
editor.putString("REFRESH", "TES");
editor.apply();
main =this;
tvName=(TextView)findViewById(R.id.tvMaName);
tHost = getTabHost();
TabHost.TabSpec tSpec;
Intent intent;
String FILE_NAME = "filename";
SharedPreferences prefs = getSharedPreferences(FILE_NAME,MODE_PRIVATE); 
String sName=prefs.getString("FULLNAME", null);
if(sName !=null){
sName_capital= sName.substring(0, 1).toUpperCase()+sName.substring(1);
}
else sName_capital="";
if(prefs.getString("FULLNAME", null)!=null){
	//Toast.makeText(this, sName+"", Toast.LENGTH_LONG).show();
	tvName.setText("Welcome "+sName_capital+"");
	tvName.setTextColor(Color.BLUE);
	
}
else{}
	//Toast.makeText(this, "NULL", Toast.LENGTH_LONG).show();
//tvName.setText(prefs.getString("FULLNAME", null));
tvName.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity1.this);
 
			
			alertDialogBuilder.setTitle("Leave Session");
 
			// set dialog message
			alertDialogBuilder.setMessage("Log out of the current session?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						String FILE_NAME = "filename";
						SharedPreferences.Editor editor=getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
						editor.remove("USERID");
						editor.remove("PASSWORD");
						editor.remove("FULLNAME");
						editor.commit();
						Intent intent=new Intent(MainActivity1.this,LogIn.class);
						startActivity(intent);
						finish();
						
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
			}
});
listview = (ListView)findViewById(R.id.listView1);
Spinner spinner = (Spinner) findViewById(R.id.spinner1);
new Asynctask_spinner(this,spinner,listview,tHost).execute("http://api.groupon.com/v2/divisions.json?client_id=dbaa2d7f2b3d92ec6b509f74f48243b58689af11");
intent = new Intent().setClass(this, ListView_Deals1.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;

tSpec = tHost.newTabSpec("first").setIndicator("DEALS")
        .setContent(intent);
tHost.addTab(tSpec);

intent = new Intent().setClass(this, GoogleMaps1.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
tSpec = tHost.newTabSpec("second").setIndicator("MAP VIEW")
        .setContent(intent);
tHost.addTab(tSpec);



tHost.setCurrentTab(0); // Default Selected Tab

tHost.setOnTabChangedListener(this);

tHost.getTabWidget().getChildAt(0).getLayoutParams().height = 90;
tHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.DKGRAY);
tHost.getTabWidget().getChildAt(1).getLayoutParams().height = 90;
tHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.DKGRAY);



tHost.getTabWidget().getChildAt(0)
        .setBackgroundColor(Color.rgb(00, 219, 239));

hand = new Handler(new Handler.Callback() {

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		String res = msg.getData().getString("TEXT");
	    textview.setText(res + " not your city? ");
		return false;
	}
});

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

@Override
public void onTabChanged(String tabId) {
if (tabId.equals("first")) {
    tHost.getTabWidget().getChildAt(0)
            .setBackgroundResource(R.drawable.tab_selector);
    tHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.DKGRAY);
} else if (tabId.equals("second")) {
    tHost.getTabWidget().getChildAt(1)
            .setBackgroundResource(R.drawable.tab_selector);

    tHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.DKGRAY);
    
} 
}




	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
