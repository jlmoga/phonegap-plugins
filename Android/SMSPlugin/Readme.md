<h1>SMS plugin for Phonegap 2.0</h1>
By Josep Lluis Monte Galiano


<h1>Adding the Plugin to your project</h1>
1. Move smsplugin.js to your project's www folder and include a reference to it in your html files.
2. Add the java files from src to your project's src hierarchy
3. Reference the plugin in your res/plugins.xml file
4. Ensure that your manifest contains the necessary permissions to send SMS messages:


<h1>Using the plugin</h1>
The plugin creates the object window.plugins.smsPlugin with two method:

<b>send</b>

Send an sms message. For example:

<code>
	var phone = "555667788";<br/>
	var message = "text to message";<br/>
	window.plugins.smsPlugin.send(phone, message);<br/>
</code>

<b>read</b>

Read list messages from inbox device:

<code>window.plugins.smsPlugin.read</code>


<b>events Success and Error</b>

	function init() {
		...
		window.plugins.smsPlugin.onError = onErrorSMS;
		window.plugins.smsPlugin.onSuccess = onSuccessSMS;
	}

	function onErrorSMS(data) {
		...
	}

	function onSuccessSMS(data) {
		...
	}



<h1>Licence</h1>
The MIT License

Copyright (c) 2011 Daniel Shookowsky

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
