package com.thejspace.www.activities;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thejspace.www.R;
import com.thejspace.www.uiframework.BaseActivity;

public class NetworkingActivity extends BaseActivity {
	
	private static final String KUSERAGENT = "CUSTOM USER AGENT";
	private static final String KTARGETURL = "http://jldev.tumblr.com";
	private static final String KLOGTAG = "NetworkingActivity.java";
	private static final String KMIMETYPE = "text/html";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		WebView webView = (WebView) findViewById(R.id.webView1);
		webView.setWebViewClient(new MyWebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setUserAgentString(KUSERAGENT);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        // Did not work due to readStream method not being found
        URL url;
		try {
			url = new URL(KTARGETURL);
			
		       HttpURLConnection urlConnection;
				try {
					urlConnection = (HttpURLConnection) url.openConnection();
					
			        try
			        {
			        	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			            String x = readStream(in);
			            Log.d(KLOGTAG, x);
			            webView.loadData(x, KMIMETYPE, null);
			        }
			        finally{urlConnection.disconnect();}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
	/**
	 * MyWebViewClient
	 * @author jailad
	 *
	 */
	 class MyWebViewClient extends WebViewClient {
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return false;
	        }
	    }
    
    /**
     * readStream method - used in conjunction with HttpURLConnection networking
     * @param in
     * @return Converts the Byte Array to String
     */

	private String readStream(InputStream in) {
		// TODO Auto-generated method stub
		
		try{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = in.read();
			while ( i != -1 )
			{
				bo.write(i);
				i = in.read();
			}
			return bo.toString();
		}
		catch(IOException e)
		{
			return "";
		}
		
	}
    
   
}