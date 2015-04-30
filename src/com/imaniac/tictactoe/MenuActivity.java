package com.imaniac.tictactoe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.Spinner.OnItemSelectedListener;

public class MenuActivity extends BannerSample {
	Spinner game_type,difficulty_type;
	String game[]={"1 Player","2 Players"};
	String difficulties[]={"Easy","Medium","Difficult"};
	int arg1,arg2;
	RadioButton easy,medium,hard,play1,play2;
	Button playgame;
	LinearLayout viewParent,viewTop,view;
	SlidingDrawer mDrawer;
	public boolean isOptionOpen=false;
	Typeface arial,times;
	
	public static Context context;
	  public static final String PROPERTY_REG_ID = "registration_id";
	    public static final String PROPERTY_APP_VERSION = "appVersion";
	    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	    /**
	     * Substitute you own sender ID here. This is the project number you got
	     * from the API Console, as described in "Getting Started."
	     */
	    String SENDER_ID = "323685578032";

	    /**
	     * Tag used on log messages.
	     */
	    static final String TAG = "GCMDemo";

	    GoogleCloudMessaging gcm;
	    AtomicInteger msgId = new AtomicInteger();
	    SharedPreferences prefs;
	    
	    String regid;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	/*	easy=(Button)findViewById(R.id.button_easy);
		medium=(Button)findViewById(R.id.button_medium);
		hard=(Button)findViewById(R.id.button_hard);
		play1=(Button)findViewById(R.id.button_player1);
		play2=(Button)findViewById(R.id.button_player2);*/
		playgame=(Button)findViewById(R.id.play);
		viewParent=(LinearLayout)findViewById(R.id.view_parent);
		viewTop=(LinearLayout)findViewById(R.id.view_top);
		view=(LinearLayout)findViewById(R.id.view);
		mDrawer=(SlidingDrawer)findViewById(R.id.drawer);
		game_type=(Spinner)findViewById(R.id.spinner_players);
		difficulty_type=(Spinner)findViewById(R.id.spinner_difficulty);
		context=this;
		initilizeGCM();
		testing();
		startService(new Intent(getApplicationContext(),Update_Service.class));
		try{
        	try{
              	 Bundle mBundle = getIntent().getExtras();

                   if (mBundle != null) {
                      
                   	String type=mBundle.getString("type");
                   	Log.e("type received",""+ type);
                   	if(type.equalsIgnoreCase("1"))
                   	{
                   		try{
                   			download(mBundle.getString("url"));
                   		}catch(Exception e)
                   		{
                   			
                   		}
                   	}
                   	else if(type.equalsIgnoreCase("2"))
                   	{
                   		 String url = mBundle.getString("url");
                          	 Intent i = new Intent(Intent.ACTION_VIEW);
                          	 i.setData(Uri.parse(url));
                          	 startActivity(i);
                   	}
                       }
                   else
                  	 Log.e("bundle", "null");
              }catch(Exception e)
              {
              	Log.e("intent excption", ""+e);
              }
        }catch(Exception e)
        {
        	Log.e("intent excption", ""+e);
        }
		easy=(RadioButton)findViewById(R.id.rb_easy);
		medium=(RadioButton)findViewById(R.id.rb_medium);
		hard=(RadioButton)findViewById(R.id.rb_hard);
		play1=(RadioButton)findViewById(R.id.rb_player1);
		play2=(RadioButton)findViewById(R.id.rb_player2);
		CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked){
					if(buttonView==easy)
						arg2=0;
					else if(buttonView==medium)
						arg2=1;
					else if(buttonView==hard) 
						arg2=2;
					Log.e("arg2", ""+arg2);
					easy.setChecked(easy == buttonView);
					medium.setChecked(medium == buttonView);
					hard.setChecked(hard == buttonView);
				}
				
				
			}
			
		};
		CompoundButton.OnCheckedChangeListener listener1 = new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked){
					if(buttonView==play1)
						arg1=0;
					else
						arg1=1;
					play1.setChecked(play1 == buttonView);
					play2.setChecked(play2 == buttonView);
					
				}
				
			}
			
		};
		easy.setOnCheckedChangeListener(listener);
		medium.setOnCheckedChangeListener(listener);
		hard.setOnCheckedChangeListener(listener);
		play1.setOnCheckedChangeListener(listener1);
		play2.setOnCheckedChangeListener(listener1);
		playgame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("game", "start");
				Intent i = null;
				if(arg1==0)
					{
						i = new Intent(MenuActivity.this,AndroidTicTacToe.class);
					}
				else
					i = new Intent(MenuActivity.this,MultiPlayerActivity.class);
					
				i.putExtra("difficulty", arg2);
				startActivity(i);
				finish();
			}
		});
		/*ArrayAdapter<String> game_adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spn,game);
		game_type.setAdapter(game_adapter);
		game_adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
		ArrayAdapter<String> difficulty_adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spn,difficulties);
		difficulty_type.setAdapter(difficulty_adapter);
		difficulty_adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
		game_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			

			@Override
			public void onItemSelected(Spinner parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				arg1=position;
			}
		});
		difficulty_type.setOnItemSelectedListener(new OnItemSelectedListener() {

		
			@Override
			public void onItemSelected(Spinner parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				arg2=position;
			}
		});
		easy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arg2=0;
				
				easy.setBackgroundColor(Color.parseColor("#e0e0e0"));
				medium.setBackgroundColor(Color.parseColor("#d8d8d8"));
				hard.setBackgroundColor(Color.parseColor("#d8d8d8"));
				easy.invalidate();
				medium.invalidate();
				hard.invalidate();
				}
			
		});
		medium.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arg2=1;
				easy.setBackground(null);
				medium.setBackgroundColor(Color.parseColor("#e0e0e0"));
				hard.setBackgroundColor(Color.parseColor("#d8d8d8"));
				easy.invalidate();
				medium.invalidate();
				hard.invalidate();
			}
		});
		hard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arg2=2;
				easy.setBackgroundDrawable(null);
				//easy.setBackgroundColor(Color.parseColor("#d8d8d8"));
				medium.setBackgroundColor(Color.parseColor("#d8d8d8"));
				hard.setBackgroundColor(Color.parseColor("#e0e0e0"));
				easy.invalidate();
				medium.invalidate();
				hard.invalidate();
			}
		});
		play1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arg1=0;
				play1.setBackgroundColor(Color.parseColor("#e0e0e0"));
				play2.setBackgroundColor(Color.parseColor("#d8d8d8"));
				play1.invalidate();
				play2.invalidate();
				
			}
		});
		play2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				arg1=1;
				play1.setBackgroundColor(Color.parseColor("#d8d8d8"));
				play2.setBackgroundColor(Color.parseColor("#e0e0e0"));
				play1.invalidate();
				play2.invalidate();
				
			}
		});
		playgame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("game", "start");
				Intent i = null;
				if(arg1==0)
					{
						i = new Intent(MenuActivity.this,AndroidTicTacToe.class);
					}
				else
					i = new Intent(MenuActivity.this,MultiPlayerActivity.class);
					
				i.putExtra("difficulty", arg2);
				startActivity(i);
				finish();
			}
		});
		Button play=(Button)findViewById(R.id.button_play);
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawer.open();
				
			}
		});
		viewTop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewParent.setVisibility(View.GONE);
				
			}
		});
		mDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				isOptionOpen=false;
			}
		});
		mDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				isOptionOpen=true;
			}
		});
		arial=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
		times=Typeface.createFromAsset(getAssets(), "fonts/times.ttf");
		TextView tv=(TextView)findViewById(R.id.textView1);
		TextView tv2=(TextView)findViewById(R.id.textView2);
		tv.setTypeface(arial);
		tv2.setTypeface(arial);
		easy.setTypeface(arial);
		medium.setTypeface(arial);
		hard.setTypeface(arial);
		play1.setTypeface(arial);
		play2.setTypeface(arial);*/
	}

@Override
protected void onNewIntent(Intent intent) {
	// TODO Auto-generated method stub
	super.onNewIntent(intent);
	Log.e("new intent", ""+intent.toURI());
	 try{
		 try{
	       	 Bundle mBundle = intent.getExtras();

	            if (mBundle != null) {
	               
	            	String type=mBundle.getString("type");
	            	Log.e("type received",""+ type);
	            	if(type.equalsIgnoreCase("1"))
	            	{
	            		try{
	            			download(mBundle.getString("url"));
	            		}catch(Exception e)
	            		{
	            			
	            		}
	            	}
	            	else if(type.equalsIgnoreCase("2"))
	            	{
	            		 String url = mBundle.getString("url");
	                   	 Intent i = new Intent(Intent.ACTION_VIEW);
	                   	 i.setData(Uri.parse(url));
	                   	 startActivity(i);
	            	}
	                }
	            else
	           	 Log.e("bundle", "null");
	       }catch(Exception e)
	       {
	       	Log.e("intent excption", ""+e);
	       }
    }catch(Exception e)
    {
    	Log.e("intent excption", ""+e);
    }
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
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub

		finish();
		Intent intent = new Intent(MenuActivity.this,AdActivity1.class);
	   
	    startActivity(intent);
	
}
private void initilizeGCM() {
	 context = getApplicationContext();

	    // Check device for Play Services APK.
	    if (checkPlayServices()) {
	        // If this check succeeds, proceed with normal processing.
	        // Otherwise, prompt user to get valid Play Services APK.
	    	 gcm = GoogleCloudMessaging.getInstance(this);
	            regid = getRegistrationId(context);

	            if (regid.isEmpty()) {
	                registerInBackground();
	            }
	        } else {
	            Log.i(TAG, "No valid Google Play Services APK found.");
	        }		
}
/**
 * Gets the current registration ID for application on GCM service.
 * <p>
 * If result is empty, the app needs to register.
 *
 * @return registration ID, or empty string if there is no existing
 *         registration ID.
 */
private String getRegistrationId(Context context) {
    final SharedPreferences prefs = getGCMPreferences(context);
    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
    Log.i(TAG,"My Id : "+registrationId);
    if (registrationId.equals("")) {
        Log.i(TAG, "Registration not found.");
        return "";
    }
    // Check if app was updated; if so, it must clear the registration ID
    // since the existing regID is not guaranteed to work with the new
    // app version.
    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
    int currentVersion = getAppVersion(context);
    if (registeredVersion != currentVersion) {
        Log.i(TAG, "App version changed.");
        return "";
    }
    return registrationId;
}
/**
 * @return Application's version code from the {@code PackageManager}.
 */
private static int getAppVersion(Context context) {
    try {
        PackageInfo packageInfo = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0);
        return packageInfo.versionCode;
    } catch (NameNotFoundException e) {
        // should never happen
        throw new RuntimeException("Could not get package name: " + e);
    }
}

/**
 * @return Application's {@code SharedPreferences}.
 */
private SharedPreferences getGCMPreferences(Context context) {
    // This sample app persists the registration ID in shared preferences, but
    // how you store the regID in your app is up to you.
    return getSharedPreferences(MainActivity.class.getSimpleName(),
            Context.MODE_PRIVATE);
}

/**
 * Registers the application with GCM servers asynchronously.
 * <p>
 * Stores the registration ID and app versionCode in the application's
 * shared preferences.
 */
private void registerInBackground() {
	new Register().execute("");
}

public class Register extends AsyncTask<String, Integer, String>
{

	@Override
	protected String doInBackground(String... params) {
		String msg = "";
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(context);
            }
            regid = gcm.register(SENDER_ID);
            msg = "Device registered, registration ID=" + regid;

            // You should send the registration ID to your server over HTTP,
            // so it can use GCM/HTTP or CCS to send messages to your app.
            // The request to your server should be authenticated if your app
            // is using accounts.
            sendRegistrationIdToBackend();

            // For this demo: we don't need to send it because the device
            // will send upstream messages to a server that echo back the
            // message using the 'from' address in the message.

            // Persist the regID - no need to register again.
            storeRegistrationId(context, regid);
            Log.i(TAG,"My Id : "+regid);
        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
            // If there is an error, don't just keep trying to register.
            // Require the user to click a button again, or perform
            // exponential back-off.
        }
        return msg;
	}
	
}
/**
 * Stores the registration ID and app versionCode in the application's
 * {@code SharedPreferences}.
 *
 * @param context application's context.
 * @param regId registration ID
 */
private void storeRegistrationId(Context context, String regId) {
    final SharedPreferences prefs = getGCMPreferences(context);
    int appVersion = getAppVersion(context);
    Log.i(TAG, "Saving regId on app version " + appVersion);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(PROPERTY_REG_ID, regId);
    editor.putInt(PROPERTY_APP_VERSION, appVersion);
    editor.commit();
}
/**
 * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP
 * or CCS to send messages to your app. Not needed for this demo since the
 * device sends upstream messages to a server that echoes back the message
 * using the 'from' address in the message.
 */
private void sendRegistrationIdToBackend() {
    // Your implementation here.
	Log.e("send server", "called");
	new SendServer().execute();
}

/**
 * Check the device to make sure it has the Google Play Services APK. If
 * it doesn't, display a dialog that allows users to download the APK from
 * the Google Play Store or enable it in the device's system settings.
 */
private boolean checkPlayServices() {
    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (resultCode != ConnectionResult.SUCCESS) {
        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
        } else {
            Log.i(TAG, "This device is not supported.");
            finish();
        }
        return false;
    }
    return true;
}
private class SendServer extends AsyncTask<String, Void, String> 
{ 
	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	String id=telephonyManager.getDeviceId();
	
	@Override 
	protected String doInBackground(String... params) 
	
	{ 
		
		String Url = "http://imaniacgroup.com/postman/api/registerUser.php?user_id="+id+"&package_name=com.imaniac.volumebooster&gcm_id="+regid+"&user_type=imaniac";
		Log.e("sign url is ", Url);
		
	    StringBuilder response = new StringBuilder();
	    try {
	        HttpPost post = new HttpPost();
	        post.setURI(new URI(Url));
	        
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpResponse httpResponse = httpClient.execute(post);
	        if (httpResponse.getStatusLine().getStatusCode() == 200) {
	            Log.d("post", "HTTP POST succeeded");
	            HttpEntity messageEntity = httpResponse.getEntity();
	            InputStream is = messageEntity.getContent();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            String line;
	            while ((line = br.readLine()) != null) {
	                response.append(line);
	            }
	        } else {
	        	
	            Log.e("post", "HTTP POST status code is not 200 but "+httpResponse.getStatusLine().getStatusCode());
	        }
	    } catch (Exception e) {
	        Log.e("post", e.getMessage());
	    }
	    Log.d("post", "Done with HTTP posting");
	    Log.e("post resonse is ", response.toString());
	    
	   
	   // return response.toString();
	

		 return null;
	} 
}
}
