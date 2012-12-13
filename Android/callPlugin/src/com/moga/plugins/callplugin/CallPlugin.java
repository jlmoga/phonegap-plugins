/*
 * CallPlugin
 * Plugin per a Phonegap 2.0
 * Automatitza trucades de telèfon
 * 
 * (c) 2012, Josep Lluis Monte Galiano
 * @jlmoga
 * www.moga.cat
 * moga@moga.cat
 */

package com.moga.plugins.callplugin;

import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import android.net.Uri;

import android.content.Intent;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;

public class CallPlugin extends Plugin {
	public final String ACTION_CALL = "Call";
	
	/**
	 * Automatització del Plugin
	 * 
	 * @param action  Acció a executar
	 * @param argl    Arguments de l'acció
	 */
	@Override
	public PluginResult execute(String action, JSONArray arg1, String callbackId) {
	    PluginResult result = new PluginResult(Status.INVALID_ACTION);

	    try {
	    	if (action.equals(ACTION_CALL)) {
	            String number = "tel:" + arg1.getString(0);
				call(number);
				result = new PluginResult(Status.OK);
	        }
	    } catch (JSONException ex) {
	    	result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
	    }

	    return result;
	}

    /**
     * Fa una trucada al telèfon especificat
     *
     * @param phoneNumber   Telèfom
     */
	private void call(String phoneNumber) {
	    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber)); 
        this.cordova.getActivity().startActivity(callIntent);	
	}
}
