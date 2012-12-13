/*
 * Plugin per a Phonegap 2.0
 * Basat en Plugin SmsPlugin de Andrew Lunny
 * 
 * (c) 2012, Josep Lluis Monte Galiano
 * @jlmoga
 * www.moga.cat
 * moga@moga.cat
 * 
 * Copyright 2012, Andrew Lunny, Adobe Systems
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2011, IBM Corporation
 * (c) 2010 Jesse MacFadyen, Nitobi
 */

var SmsPlugin = (function (gap) {
    function isFunction(f) {
        return typeof f === "function";
    }

    // SmsPlugin
    function SmsPlugin() {}

    
    /**
     * Llegeix missatges SMS de la carpeta inbox del dispositiu
     */
    SmsPlugin.read = function () {
        gap.exec(onEvent, onError, "SmsPlugin", "ReadSMS", ["inbox"]);
    }
    
    /**
     * Envia un SMS al telèfon especificat
     *
     * @param phone    Telèfon
     * @param message  Missatge
     */
    SmsPlugin.send = function (phone, message) {
        gap.exec(onEvent, onError, "SmsPlugin", "SendSMS", [phone, message]);
    };

    
    /**
     * Esdeveniment en cas d'èxit. El SMS s'envia
     */
    function onEvent(data) {
        SmsPlugin.onSuccess(data);
    }

    /**
     * Esdeveniment en cas d'error
     */
    function onError(data) {
        SmsPlugin.onError(data);
    }

    
    /**
     * Càrrega SmsPlugin
     */
    gap.addConstructor(function () {
        if (gap.addPlugin) {
            gap.addPlugin("smsplugin", SmsPlugin);
        } else {
            if (!window.plugins) {
                window.plugins = {};
            }

            window.plugins.smsPlugin = SmsPlugin;
        }
    });

    return SmsPlugin;
})(window.cordova || window.Cordova || window.PhoneGap);

