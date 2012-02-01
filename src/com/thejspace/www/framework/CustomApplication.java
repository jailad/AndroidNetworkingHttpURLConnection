

package com.thejspace.www.framework;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

//import org.acra.ACRA;
//import org.acra.ErrorReporter;
//import org.acra.ReportField;
//import org.acra.ReportingInteractionMode;
//import org.acra.annotation.ReportsCrashes;
import org.acra.ACRA;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

//import com.ml.framework.cache.Cache;
//import com.ml.framework.cache.ICacheable;
//import com.ml.framework.cache.IReferenceFactory;
//import com.ml.framework.mvp.Model;
//import com.ml.framework.utils.BrandingUtil;
//import com.ml.framework.utils.CEBranding;
//import com.ml.framework.utils.StringUtil;
//import com.ml.mobile.framework.service.HttpServiceCallExecutor;
//import com.ml.mobile.framework.service.ServiceCallClient;
//import com.ml.mobile.framework.service.ServiceManager;
//import com.ml.mobile.mymerrill.R;

// #ifndef PRODBUILD

//@ReportsCrashes(formKey = "", mode = ReportingInteractionMode.NOTIFICATION, formUri = "http://mphewdndsh002.amrs.win.ml.com/crash/crash.php", resToastText = R.string.crash_toast_text, 
//resNotifTickerText = R.string.crash_notif_ticker_text, resNotifTitle = R.string.crash_notif_title, resNotifText = R.string.crash_notif_text, resNotifIcon = android.R.drawable.stat_notify_error, 
//resDialogText = R.string.crash_dialog_text, resDialogTitle = R.string.crash_dialog_title, 
//resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, 
//resDialogOkToast = R.string.crash_dialog_ok_toast, 
//customReportContent = { ReportField.USER_COMMENT, ReportField.USER_CRASH_DATE,
//		ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
//		ReportField.CUSTOM_DATA, ReportField.STACK_TRACE })

// #endif

public final class CustomApplication extends Application {
	private static final String TAG = CustomApplication.class.getSimpleName();

	/**
	 * Specifies the unique of the property that will hold the common elements
	 * object.
	 */
	public static final String APPLICATION_CONTROL = TAG
			+ ".ApplicationControl";

	// If either of the flags below is not set, the URLs will be loaded from
	// SharedPreferences if they exist, else will be defaulted to QA
	// TBD: When Raaj's dependency injection code is finished, the Flag related
	// items below will have to be re-factored to use the updated conditional
	// build system
	// The Flags are listed in decreasing order of priority
	// 'BUILDFLAG_SETTINGSBUILD' is checked within HeaderPanel.java to determine
	// whether to setup a touch listener for the logo or not.
	public static final boolean BUILDFLAG_FINALMOBILEBUILD = false;
	public static final boolean BUILDFLAG_PRODUCTIONMOBILEBUILD = false;
	public static final boolean BUILDFLAG_SETTINGSBUILD = true;

	protected static CustomApplication _context = null;

	// This is the user-agent cache string used by getUserAgentString() in this
	// class.
	private static String _userAgent = null;

	public static CustomApplication getContext() {
		return _context;
	}


//	/**
//	 * Fetches the value of a property with the given unique name.
//	 * 
//	 * @param name
//	 *            specifies the unique property name.
//	 * 
//	 * @return null or reference the property's current value.
//	 */
//	public Object getProperty(final String name) {
//		return m_applicationModel.getProperty(name);
//	}

	/**
	 * Stores the given value into the property with the given unique name.
	 * Please note that any value stored using this method will pin value object
	 * in memory until either the property has been removed or assigned a new
	 * value or the application is stopped. <b>Please do not use this method to
	 * cache costly value objects.</b> Use the cache method
	 * {@link #putCacheItem(String, ICacheable)} for such purposes.
	 * 
	 * @param name
	 *            specifies the unique property name.
	 * @param value
	 *            null or reference the property's new value.
	 * @param notify
	 *            if true, the observers (if any) of this application model will
	 *            be notified when the property's value is changed.
	 */
//	public void setProperty(final String name, final Object value,
//			final boolean notify) {
//		m_applicationModel.setProperty(name, value, notify);
//	}

	/**
	 * Stores the given value into the property with the given unique name.
	 * Please note that any value stored using this method will pin value object
	 * in memory until either the property has been removed or assigned a new
	 * value or the application is stopped. <b>Please do not use this method to
	 * cache costly value objects.</b> Use the cache method
	 * {@link #putCacheItem(String, ICacheable)} for such purposes.
	 * 
	 * @param name
	 *            specifies the unique property name.
	 * @param value
	 *            null or reference the property's new value.
	 */
//	public void setProperty(final String name, final Object value) {
//		m_applicationModel.setProperty(name, value);
//	}

//	public void removeProperty(final String name, final boolean notify) {
//		m_applicationModel.removeProperty(name, notify);
//	}
//
//	public void removeProperty(final String name) {
//		m_applicationModel.removeProperty(name);
//	}

	/**
	 * Adds the specified observer to the list of observers who will notified
	 * whenever a property value is changed.
	 * 
	 * @param observer
	 *            reference to the observer to be added.
	 */
//	public void addModelObserver(final Observer observer) {
//		m_applicationModel.addObserver(observer);
//	}

	/**
	 * Removes the specified observer from the list of observers who will
	 * notified whenever a property value is changed.
	 * 
	 * @param observer
	 *            reference to the observer to be removed.
	 */
//	public void deleteModelObserver(final Observer observer) {
//		m_applicationModel.deleteObserver(observer);
//	}

	// Methods
	public void onCreate() {
		// #ifndef PRODBUILD
		if (BUILDFLAG_SETTINGSBUILD) {
			ACRA.init(this); // Crash logging library
		}
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
