package com.cranberrygame.phonegap.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import android.app.Activity;
//back key exit method1
import android.app.AlertDialog;
import android.content.DialogInterface;
//back key exit method2: back key twice
import android.widget.Toast;//

public class ExitAppIfTwice extends CordovaPlugin {
	private Activity activity;
	//back key exit method2: back key twice
	private long lastPressedTime;
	private static final int PERIOD = 2000;
  
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		
		activity=cordova.getActivity();
	}
	
	@Override
	public boolean execute(String action, JSONArray args,CallbackContext callbackContext) {
		PluginResult result = null;
		
		//try {		
			//args.length()
			//args.getString(0)
			//args.getString(1)
			//args.Int(0)
			//args.Int(1)
			//args.getBoolean(0)
			//args.getBoolean(1)

			if (action.equals("exitAppIfTwice")) {
/*	
				//back key exit method1
				new AlertDialog.Builder(activity)
				.setTitle("Exit")
				.setMessage("Do you want to exit?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				  
				  @Override
				  public void onClick(DialogInterface dialog, int which) {
					android.os.Process.killProcess(android.os.Process.myPid());
				  }
				})
				.setNegativeButton("No", null)
				.setCancelable(false)
				.show();		
*/
				//back key exit method2: back key twice
				//if (event.getDownTime() - lastPressedTime < PERIOD) {
				if (System.currentTimeMillis() - lastPressedTime < PERIOD) {
					android.os.Process.killProcess(android.os.Process.myPid());
				} 
				else {
					Toast.makeText(activity.getApplicationContext(), "press again to exit.",
							Toast.LENGTH_SHORT).show();
					//lastPressedTime = event.getEventTime();
					lastPressedTime = System.currentTimeMillis();
				}	
				
				result = new PluginResult(PluginResult.Status.OK);	
			}		
			else {
				result = new PluginResult(PluginResult.Status.INVALID_ACTION);
			}				
		//}
		//catch (JSONException e) {
		//  result = new PluginResult(PluginResult.Status.JSON_EXCEPTION);
		//}
		
		callbackContext.sendPluginResult(result);
		return true;		
	}
}