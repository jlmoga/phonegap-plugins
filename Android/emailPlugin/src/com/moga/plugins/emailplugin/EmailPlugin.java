/*
 * EmailPlugin
 * Plugin per a Phonegap 2.0
 * Automatitza l'enviament de correu electrònic
 * 
 * (c) 2012, Josep Lluis Monte Galiano
 * @jlmoga
 * www.moga.cat
 * moga@moga.cat
 */
package com.moga.plugins.emailplugin;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.api.Plugin;

public class EmailPlugin extends Plugin {
	public final String ACTION_SEND_EMAIL = "SendEmail";
	public final String ACTION_PREPARE_SMTP = "PrepareSMTP";
	private Mail serveiMail = null;
	
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
	    	if (action.equals(ACTION_SEND_EMAIL)) {
	            String email = arg1.getString(0);
	            String subject = arg1.getString(1);
	            String message = arg1.getString(2);
				result = sendEmail(email, subject, message);
	        }
	    	
	    	if (action.equals(ACTION_PREPARE_SMTP)) {
	    		String host = arg1.getString(0);
	    		int port = Integer.parseInt(arg1.getString(1));
	    		String userName = arg1.getString(2);
	    		String password = arg1.getString(3);
	    		String from	 = arg1.getString(4);
	    		result = prepareSMTP(host, port, userName, password, from);
	        }
	    } catch (JSONException ex) {
	    	result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
	    }

	    return result;
	}

	
	/**
	 * prepareSMTP
	 * Prepara el servei de SMTP
	 * 
     * @param host      Host
     * @param port      Port SMTP
     * @param userName  Usuari del servei SMTP
     * @param password  Password
     * 
	 */
	private PluginResult prepareSMTP(String host, int port, String userName, String password, String from) {
		PluginResult result = new PluginResult(Status.OK);
		
		try {
			serveiMail = new Mail(userName, password);
		    serveiMail.setFrom(from); 
		} catch(Exception ex) {
			ex.printStackTrace();
			result = new PluginResult(Status.IO_EXCEPTION, ex.getMessage());
		}
		
		return result;
	}

	
	/**
	 * sendEmail
	 * Envia un email a l'adreça especificada
	 * 
	 * @param email   Adreça d'email
	 * @param subject Assumpte
	 * @param message Cos del correu
	 */
	private PluginResult sendEmail(String email, String subject, String message) {
		PluginResult result = new PluginResult(Status.OK);
		
		if (serveiMail == null) {
			result = new PluginResult(Status.IO_EXCEPTION, "Cal prepareSMTP");
		} else {		
			try {
				String[] toArr = {email};
			    serveiMail.setTo(toArr); 
			    serveiMail.setSubject(subject); 
			    serveiMail.setBody(message);
			
				if (!serveiMail.send()) {
					result = new PluginResult(Status.IO_EXCEPTION, "Error al fer Mail.send");
				}
			} catch(Exception ex) {
				result = new PluginResult(Status.IO_EXCEPTION, ex.getMessage());
			}
		}

		return result;
	}
}
