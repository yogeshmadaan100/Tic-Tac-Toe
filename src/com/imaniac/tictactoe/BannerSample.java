package com.imaniac.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerSample extends ActionBarActivity {
	 
	/** The view to show the ad. */
	 private AdView adView;
	  

	  /* Your ad unit id. Replace with your actual ad unit id. */
	  private static final String AD_UNIT_ID = "ca-app-pub-4572005047829352/3154650426";
	  
	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_banner_sample);

	    // Create an ad.
	    
	  }
public void testing()
{
	adView = new AdView(this);
    adView.setAdSize(AdSize.BANNER);
    adView.setAdUnitId(AD_UNIT_ID);

    // Add the AdView to the view hierarchy. The view will have no size
    // until the ad is loaded.
    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
    layout.addView(adView);
   
    // Create an ad request. Check logcat output for the hashed device ID to
    // get test ads on a physical device.
    AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .build();

    // Start loading the ad in the background.
    adView.loadAd(adRequest);
    
    final Handler handler = new Handler();
    runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(), "No Results Found", 1000).show();
			adView.loadAd(new AdRequest.Builder().build());
			//Toast.makeText(getApplicationContext(), "here", 1000).show();
			//tv.setText(""+System.currentTimeMillis());
			handler.postDelayed(this, 6000);
		}
	});
}
	  @Override
	  public void onResume() {
	    super.onResume();
	    if (adView != null) {
	    	//Toast.makeText(getApplicationContext(), "resumed", 1000).show();
	      adView.resume();
	    }
	  }

	  @Override
	  public void onPause() {
	    if (adView != null) {
	    	//Toast.makeText(getApplicationContext(), "paused", 1000).show();
	      adView.pause();
	    }
	    super.onPause();
	  }

	  /** Called before the activity is destroyed. */
 @Override
	  public void onDestroy() {
	    // Destroy the AdView.
	    if (adView != null) {
	    	//Toast.makeText(getApplicationContext(), "destroyed", 1000).show();
	      adView.destroy();
	    }
	    super.onDestroy();
	  }
 @Override
public void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
}
 @Override
public void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
}
	}

