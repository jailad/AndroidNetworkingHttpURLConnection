
package com.thejspace.www.framework;

import org.acra.ACRA;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public final class CustomApplication extends Application {
	private static final String TAG = CustomApplication.class.getSimpleName();

	/**
	 * Specifies the unique of the property that will hold the common elements
	 * object.
	 */
	public static final String APPLICATION_CONTROL = TAG
			+ ".ApplicationControl";

	protected static CustomApplication _context = null;

	// This is the user-agent cache string used by getUserAgentString() in this
	// class.
	private static String _userAgent = null;

	public static CustomApplication getContext() {
		return _context;
	}




	// Methods
	public void onCreate() {
		// #ifndef PRODBUILD
			ACRA.init(this); // Crash logging library
		// #endif
		// First, call the default
		super.onCreate();
		
		synchronized (CustomApplication.class) {
			_context = (CustomApplication) this.getApplicationContext();
		}

	}

	public boolean hasConnectivity() {
		ConnectivityManager connManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connManager.getActiveNetworkInfo();
		// NetworkInfo.State state = activeInfo.getState();
		return (activeInfo != null) ? activeInfo.isAvailable() : false;
	}
	
	public void onLowMemory() {
		// First, call the default
		super.onLowMemory();
	}

	private Handler m_handler = new Handler();

	/**
	 * @param r
	 * @return
	 */
	public boolean post(final Runnable r) {
		return m_handler.post(r);
	}



}
