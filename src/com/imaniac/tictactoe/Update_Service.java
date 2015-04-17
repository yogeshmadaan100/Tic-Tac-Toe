package com.imaniac.tictactoe;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.http.util.ByteArrayBuffer;




import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.Toast;

public class Update_Service extends Service{
	Double version,OurVersion;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("update", "called");
		if(IsOnline())
			new DownloadXML().execute();
		
       
        
        
	}
	private class DownloadXML extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.e("Download", "entered");
			
			
			
		}

		@Override
		protected Void doInBackground(String... Url) {
			try {
		           URL updateURL = new URL("https://play.google.com/store/apps/details?id=com.imaniac.tictactoe");                
		            URLConnection conn = updateURL.openConnection(); 
		            InputStream is = conn.getInputStream();
		            BufferedInputStream bis = new BufferedInputStream(is);
		            ByteArrayBuffer baf = new ByteArrayBuffer(50);
		            
		            int current = 0;
		            while((current = bis.read()) != -1){
		                 baf.append((byte)current);
		            }
		            /* Convert the Bytes read to a String. */
		           final String s = new String(baf.toByteArray());  
		           //Log.e("Stringggggg", s);
		           int pos =s.indexOf("softwareVersion");
		            Log.e("pos", ""+pos);
		            /* Get current Version Number */
		            String sub =s.substring(pos+18, pos+21);
		            version = Double.parseDouble(sub);
		            OurVersion = Double.parseDouble(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		            
		            char temp =s.charAt(pos+18);
		            Log.e("subbbbbbbbb", sub);
		            
		                        
		        } catch (Exception e) {
		        	Log.e("ERORRRRRRRRRRRRRR", e.toString());
		        	
		        }
			
			return null;
			
			
		}

		@Override
		protected void onPostExecute(Void args) {

			
			try{
				if (version > OurVersion) {
			
                /* Post a Handler for the UI to pick up and open the Dialog */
               showupdate();
            }    
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	public void update() throws NetworkOnMainThreadException
	{
		 
		
	}
	public void showupdate(){
		new AlertDialog.Builder(MyApplication.mApplication)
        .setIcon(R.drawable.logo)
        .setTitle("Update Available")
        .setMessage("An update is available!Do you want to update?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked OK so do some stuff */
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.imaniac.tictactoe"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                        
                }
        })
        .show();
       }
	public  boolean IsOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {
	      
	    	return true;
	    }
	    return false;
	}
}
