/*
 * EmailPlugin
 * Plugin per a Phonegap 2.0
 * Automatitza l'enviament d'email
 * 
 * (c) 2012, Josep Lluis Monte Galiano
 * @jlmoga
 * www.moga.cat
 * moga@moga.cat
 */

var EmailPlugin = (function (gap) {
    function isFunction(f) {
        return typeof f === "function";
    }

    // EmailPlugin
    function EmailPlugin() {}


    /**
     * Perara el servei SMTP
     *
     * @param host      Host
     * @param port      Port SMTP
     * @param userName  Usuari del servei SMTP
     * @param password  Password
     * @param from      Remitent dels correus
     */
    EmailPlugin.prepareSMTP = function (host, port, userName, password, from) {
        gap.exec(onEvent, onError, "EmailPlugin", "PrepareSMTP", [host, port, userName, password, from]);
    };

    /**
     * Envia un email a l'adreça de correu especificada
     *
     * @param email   Adreça de correu
     * @param subject Assumpte del correu
     * @param message Cos del correu
     */
    EmailPlugin.sendEmail = function (email, subject, message) {
        gap.exec(onEvent, onError, "EmailPlugin", "SendEmail", [email, subject, message]);
    };

    /**
     * Esdeveniment en cas d'èxit. El email s'envia
     */
    function onEvent(data) {
        EmailPlugin.onSuccess();
    }

    /**
     * Esdeveniment en cas d'error
     */
    function onError(data) {
        EmailPlugin.onError(data);
    }

    
    /**
     * Càrrega EmailPlugin
     */
    gap.addConstructor(function () {
        if (gap.addPlugin) {
            gap.addPlugin("emailplugin", EmailPlugin);
        } else {
            if (!window.plugins) {
                window.plugins = {};
            }
            
            window.plugins.emailPlugin = EmailPlugin;
        }
    });

    return EmailPlugin;
})(window.cordova || window.Cordova || window.PhoneGap);
