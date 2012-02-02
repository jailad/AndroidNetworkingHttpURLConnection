package com.thejspace.www.uiframework;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thejspace.www.framework.CustomApplication;

/**
 * BaseActivity: Provides baseline functionality for all the activities within the Application
 * @author jailad
 */

public class BaseActivity extends Activity {
		
	private static final int ABOUT_ID = 0;
	private static final int REFRESH_ID = 1;
		
	/** {@inheritDoc} */
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		CustomApplication app = (CustomApplication) this.getApplication();
		
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	      menu.add(0, ABOUT_ID, 0, "ABOUT");
	      menu.add(0, REFRESH_ID, 0, "REFRESH").setCheckable(true);
	      return super.onCreateOptionsMenu(menu);
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Toast toast;
		
	      switch (item.getItemId()) {
	          case ABOUT_ID:
	        	  
	        	  toast = Toast.makeText(this, "ABOUT", Toast.LENGTH_SHORT);
	        	  toast.show();
	        	  
	              return true;
	              
	          case REFRESH_ID:
	        	  
	        	  toast = Toast.makeText(this, "REFRESH", Toast.LENGTH_SHORT);
	        	  toast.show();

	              return true;
	          default:
	              return super.onOptionsItemSelected(item);
	      }
	  }

}