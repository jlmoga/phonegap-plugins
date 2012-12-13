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

var CallPlugin = (function (gap) {
    function isFunction(f) {
        return typeof f === "function";
    }

    // CallPlugin
    function CallPlugin() {}

    
    /**
     * Fa una trucada al telèfon especificat
     *
     * @param phone   Telèfom
     */
    CallPlugin.call = function (phone) {
        gap.exec(onEvent, onError, "CallPlugin", "Call", [phone]);
    };

    
    /**
     * Esdeveniment en cas d'èxit. La trucada es fa.
     */
    function onEvent(data) {
        CallPlugin.onSuccess();
    }

    /**
     * Esdeveniment en cas d'error.
     */
    function onError(data) {
        CallPlugin.onError(data);
    }

    
    /**
     * Càrrega CallPlugin
     */
    gap.addConstructor(function () {
        if (gap.addPlugin) {
            gap.addPlugin("callplugin", CallPlugin);
        } else {
            if (!window.plugins) {
                window.plugins = {};
            }

            window.plugins.callPlugin = CallPlugin;
        }
    });

    return CallPlugin;
})(window.cordova || window.Cordova || window.PhoneGap);

