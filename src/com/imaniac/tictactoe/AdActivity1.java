package com.imaniac.tictactoe;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity1 extends Activity {
	 private InterstitialAd mInterstitialAd;
		static Context mContext;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_ad);
		
			
			mContext=this;
			try{
				
				if(AdMobAd.mInterstitialAd!=null)
				{
					Log.e("ad", "not null");
					mInterstitialAd=AdMobAd.mInterstitialAd;
				}
				else
					finish();
			}catch(Exception e)
			{
				Intent i = new Intent(AdActivity1.this,MainActivity.class);
				//startActivity(i);
				finish();
			}
			if(AdMobAd.flag)
				displayAd();
			else
				finish();
			 mInterstitialAd.setAdListener(new AdListener() {
	         	@Override
	         	public void onAdLoaded() {
	         		// TODO Auto-generated method stub
	         		displayAd();
	         	}
	         	@Override
	         	public void onAdClosed() {
	         		// TODO Auto-generated method stub
	         		super.onAdClosed();
	         		try{
	         		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
	       		 
					// set title
					alertDialogBuilder.setTitle("Tic Tac Toe");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Do you Really Want To Exit?")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								finish();
								//DatingApplication.e.putString("uid", "logout");
								//DatingApplication.e.commit();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								//DatingApplication.getService().change_friend_status(uid, "rejected");
								dialog.cancel();
								Intent i = new Intent(AdActivity1.this,MenuActivity.class);
								startActivity(i);
								finish();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
	         		}catch(Exception e)
	         		{
	         			Log.e("alert dialog", ""+e);
	         		}
	         	}
	         	@Override
	         	public void onAdFailedToLoad(int errorCode) {
	         		// TODO Auto-generated method stub
	         		super.onAdFailedToLoad(errorCode);
	         		Log.e("error", "error");
	         		finish();
	         	}
	         	
				});
		
			
		
		}
		 public void displayAd() {
	         // Show the ad if it's ready. Otherwise toast and restart the game. 
	         if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
	             mInterstitialAd.show();
	         }
	     }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
		 
		// set title
		alertDialogBuilder.setTitle("Tic Tac Toe");

		// set dialog message
		alertDialogBuilder
			.setMessage("Do you Really Want To Exit?")
			.setCancelable(false)
			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// current activity
					finish();
					//DatingApplication.e.putString("uid", "logout");
					//DatingApplication.e.commit();
				}
			  })
			.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					//DatingApplication.getService().change_friend_status(uid, "rejected");
					dialog.cancel();
					Intent i = new Intent(AdActivity1.this,MenuActivity.class);
					startActivity(i);
					finish();
				}
			});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

	}
	  public static boolean isDownloadManagerAvailable(Context context) {
		    try {
		        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
		            return false;
		        }
		        Intent intent = new Intent(Intent.ACTION_MAIN);
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);
		        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
		        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
		                PackageManager.MATCH_DEFAULT_ONLY);
		        return list.size() > 0;
		    } catch (Exception e) {
		        return false;
		    }
		}
		public void download (String url)
		{
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
			request.setDescription("Downloading");
			request.setTitle("Apk Download");
			File f=Environment.getExternalStorageDirectory();
			
			String path=f.getAbsolutePath();
			
			path=path+"/my downloads/";
			
			File f1=new File(path);
			f1.mkdirs();
			path=path+"new.apk";
			// in order for this if to run, you must use the android 3.2 to compile your app
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			    request.allowScanningByMediaScanner();
			    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			}
			request.setDestinationInExternalPublicDir("/my downloads/", "new.apk");

			// get download service and enqueue file
			DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			manager.enqueue(request);
		}
	

}
