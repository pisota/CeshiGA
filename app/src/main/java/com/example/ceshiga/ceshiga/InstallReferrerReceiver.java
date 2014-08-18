package com.example.ceshiga.ceshiga;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.analytics.CampaignTrackingReceiver;

public class InstallReferrerReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
        final String TAG="InstallReferrerReceiver";
		if (intent.hasExtra("referrer")) {
            Log.d(TAG, "Google Analytics referrer is OK");
			//context.startService(GAService.getIntent(context, GAService.INSTALL));
		} else {
			Log.d(TAG,"Google Analytics referrer is null");
		}
        new CampaignTrackingReceiver().onReceive(context, intent);
	}
}
