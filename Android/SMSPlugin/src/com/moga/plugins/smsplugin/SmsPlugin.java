/*
 * Plugin per a Phonegap 2.0
 * Basat en Plugin SmsPlugin de Andrew Lunny
 * 
 * (c) 2012, Josep Lluis Monte Galiano
 * @jlmoga
 * www.moga.cat
 * moga@moga.cat
 */
package com.moga.plugins.smsplugin;

import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;

import android.database.Cursor;
import android.net.Uri;

public class SmsPlugin extends Plugin {
	public final String ACTION_SEND_SMS = "SendSMS";
	public final String ACTION_READ_SMS = "ReadSMS";
	
	/**
	 * Automatització del Plugin
	 * 
	 * @param action  Acció a executar
	 * @param argl    Arguments de l'acció
	 */
	@Override
	public PluginResult execute(String action, JSONArray arg1, String callbackId) {
		PluginResult result = new PluginResult(Status.INVALID_ACTION);
		
		if (action.equals(ACTION_SEND_SMS)) {
			try {
				String phoneNumber = arg1.getString(0);
				String message = arg1.getString(1);
				sendSMS(phoneNumber, message);
				result = new PluginResult(Status.OK);
			}
			catch (JSONException ex) {
				result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
			}			
		}
		
		if (action.equals(ACTION_READ_SMS)) {
			try {
				JSONObject messages = new JSONObject();
				String folder = arg1.getString(0);
				messages = readSMS(folder);
				result = new PluginResult(Status.OK, messages);
			}
			catch (JSONException ex) {
				result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
			}			
		}
		
		return result;
	}

	

    /**
     * Llegeix missatges SMS
     * 
     * @param folder 	Repositori de missatges: INBOX o SEND
     * @return 			Llista de missatges
     * @throws JSONException
     */
    private JSONObject readSMS(String folder) throws JSONException {
        JSONObject data = new JSONObject();
        Uri uriSMSURI = Uri.parse("");
        if(folder.equals("inbox")){
            uriSMSURI = Uri.parse("content://sms/inbox");
        }
        else if(folder.equals("sent")){
        	uriSMSURI = Uri.parse("content://sms/sent");
        }
        
        Cursor cur = this.cordova.getActivity().getContentResolver().query(uriSMSURI, null, null, null,null);
        JSONArray smsList = new JSONArray();
        data.put("missatges", smsList);
        while (cur.moveToNext()) {
          JSONObject sms = new JSONObject();
          sms.put("tx1",cur.getString(1));
          sms.put("tx2",cur.getString(2));
          sms.put("tx3",cur.getString(3));
          sms.put("tx4",cur.getString(4));
          sms.put("tx5",cur.getString(5));
          sms.put("tx6",cur.getString(6));
          sms.put("tx7",cur.getString(7));
          sms.put("tx8",cur.getString(8));
          sms.put("tx9",cur.getString(9));
          sms.put("tx10",cur.getString(10));
          sms.put("tx11",cur.getString(11));
          sms.put("tx12",cur.getString(12));
          sms.put("tx13",cur.getString(13));
          sms.put("tx14",cur.getString(14));
          sms.put("tx15",cur.getString(15));
          sms.put("tx16",cur.getString(16));
          sms.put("tx17",cur.getString(17));
          sms.put("tx18",cur.getString(18));
          sms.put("tx19",cur.getString(19));
          sms.put("tx20",cur.getString(20));
            
          smsList.put(sms);
        }
        return data;
    }
    
    
    /**
     * Envia un SMS al telèfon especificat
     *
     * @param phoneNumber    Telèfon
     * @param message        Missatge
     */
	private void sendSMS(String phoneNumber, String message) {
		SmsManager manager = SmsManager.getDefault();
		
        PendingIntent sentIntent = PendingIntent.getActivity(this.cordova.getActivity(), 0, new Intent(), 0);
		manager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
	}
}
