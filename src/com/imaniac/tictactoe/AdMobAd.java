package com.imaniac.tictactoe;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AdMobAd extends Service{
	public static InterstitialAd mInterstitialAd;
	public static boolean flag=false;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("service ads", "called");
		 mInterstitialAd = new InterstitialAd(this);
	        // Defined in values/strings.xml
	        mInterstitialAd.setAdUnitId("ca-app-pub-4572005047829352/4631383623");
	        AdRequest adRequest = new AdRequest.Builder().build();
	        mInterstitialAd.loadAd(adRequest);
	        mInterstitialAd.setAdListener(new AdListener() {
	        	@Override
	        	public void onAdLoaded() {
	        		// TODO Auto-generated method stub
	        		super.onAdLoaded();
	        		Log.e("service ads", "loaded");
	        		flag=true;
	        	}
			});
	}

}
