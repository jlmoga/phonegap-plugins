<h1>Email plugin for Phonegap 2.0</h1>
By Josep Lluis Monte Galiano


<h1>Adding the Plugin to your project</h1>
1. Move smsplugin.js to your project's www folder and include a reference to it in your html files.
2. Add the java files from src to your project's src hierarchy
3. Reference the plugin in your res/plugins.xml file
4. Ensure that your manifest contains the necessary permissions to send SMS messages:


<h1>Using the plugin</h1>
The plugin creates the object window.plugins.emailPlugin with two method:

<b>prepareSMTP</b>

Prepare the SMTP server:

<pre>
var host = "smtp.gmail.com";
var port = 465;
var user = "user@gmail.com";
var passw = "passw";
var from = "mail@aaa.com";

window.plugins.emailPlugin.prepareSMTP(host, port, user, passw, from);
</pre>

<b>sendEmail</b>

Send an email:

<pre>
var to = "mail@aaa.com";
var subject = "subject";
var message = "message body";

window.plugins.emailPlugin.sendEmail(to, subject, message);
</pre>


<b>events Success and Error</b>

	function init() {
		...
		window.plugins.emailPlugin.onError = onErrorEmail;
		window.plugins.emailPlugin.onSuccess = onSuccessEmail;
	}

	function onErrorEmail(data) {
		...
	}

	function onSuccessEmail() {
		...
	}



<h1>Licence</h1>
The MIT License

Copyright (c) 2012 Josep Lluis Monte Galiano

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
