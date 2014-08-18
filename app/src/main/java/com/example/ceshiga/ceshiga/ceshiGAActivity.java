package com.example.ceshiga.ceshiga;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;
import java.util.Map;


public class ceshiGAActivity extends ActionBarActivity {

    private Map<String, String> mMap = new HashMap<String, String>();
    public static final String CH = "CH";// 渠道号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi_ga);
        // Get tracker.
        Tracker t = ((CeshiApplication) getApplication()).getTracker(CeshiApplication.TrackerName.APP_TRACKER);

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("ceshiGAActivity1");

        // Send a screen view.
       // t.send(new HitBuilders.AppViewBuilder().build());




        t.send(new HitBuilders.ScreenViewBuilder()
                        .setAll(getMap())
                        .build()
        );

       /* t.send(new HitBuilders.AppViewBuilder().setAll(getMap())
                        .setCustomMetric(1, 5)
                        .build()
        );*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ceshi_ga, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get an Analytics tracker to report app starts & uncaught exceptions etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    public Map<String,String> getMap(){
        mMap.put(CH, "0");
        mMap.put("Test","testceshi");
        return mMap;
    }
}
