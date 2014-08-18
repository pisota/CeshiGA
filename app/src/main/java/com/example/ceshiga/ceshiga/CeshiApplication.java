package com.example.ceshiga.ceshiga;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class CeshiApplication extends Application {
	private static CeshiApplication ceshiApp = null;

	private static synchronized void setApp(CeshiApplication app) {
		ceshiApp = app;
		
	}

	public static synchronized CeshiApplication getApp() {
		return ceshiApp;
	}
	
	public Handler handler = new Handler();
	
	public Handler getHandler(){
		return handler;
	}

	public void onCreate() {
        super.onCreate();
        Context mContext = getApplicationContext();
        setApp(this);
    }



    private static final String PROPERTY_ID = "UA-51768232-2";


    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
                    : analytics.newTracker(R.xml.ecommerce_tracker);

            mTrackers.put(trackerId, t);
            GoogleAnalytics.getInstance(this).getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            GoogleAnalytics.getInstance(this).enableAutoActivityReports(this);
            // You only need to set User ID on a tracker once. By setting it on the tracker, the ID will be
            // sent with all subsequent hits.
            //t.set("&uid", Utils.getIMEI(this));

        }
        return mTrackers.get(trackerId);
    }


}
