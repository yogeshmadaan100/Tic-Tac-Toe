package com.imaniac.tictactoe;

import java.util.HashMap;

import android.app.Application;
import android.content.Intent;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class MyApplication extends Application {
	public static MyApplication mApplication;
	public enum TrackerName {
	    APP_TRACKER, // Tracker used only in this app.
	    GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
	     }

	  HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication=this;
		Intent i = new Intent(getApplicationContext(),AdMobAd.class);
		startService(i);
	}
	 public synchronized Tracker getTracker(TrackerName trackerId) {
		    if (!mTrackers.containsKey(trackerId)) {

		      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		      
		      Tracker t = (Tracker) ((trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(R.xml.app_tracker)
		          : (trackerId == TrackerName.GLOBAL_TRACKER)) ;
		      mTrackers.put(trackerId, t);

		    }
		    return mTrackers.get(trackerId);
		  }
}
