# tosan-widget-guide

Widgets are loaded in Iframe in Netway; So developers can use any programming language and platform to create their own widgets.

Pay attention to the following points when developing and installing your widget.

1. The types of widgets with their feuters are listed in the table below. developers need to choose one of  the types when registering their widgets in WidgetStore.

   1. When you choose Full type, Netway will display your widget in a seperate page
   2. The security of widgets is the responsibility of the bank and need to be checked by the bank.

Widget's type | Widget's width | Widget's height 
------------- | -------------- | ---------------
SMALL | 300 px | 216 px
SMALL | 300 px | 445 px
WIDE | 630 px | 216 px
MEDIUM | 630 px | 445 px
FULL | 900 px | 400 px

2. To register a widget in  WidgetStore, first create an account in WigetStore. 
   1. If you don't have an account in WidgetStore, first click the 'Signup' button, next complete the form.
   2. after login into  WidgetStore; At the top of the siteو click the 'Upgrade To Developer Account' and fill the form.
   2. After your request is approved by the admin, your account will change to Developer-Account. 
   
3. You must register your widget information in  WidgetStore.
   1. Login into WidgetStore.
   2. After login, click  the 'User Panel' to enter the user panel section. 
   3. In the user panel section click 'Developer Panel' menu, next click the 'Developer Apps' sub menu.
   4. Click the 'New' button
   5. On the 'Create New App' window choose 'Widget' type
   6. Click the 'Save' button 
   7. In this step you must fill the form about all information of your widget, then click the 'Next' button.
      1. URL Schema is your widget url and Netway uses this URL to call your widget. 
      2. In the 'Operating System' field, select 'Web'.
      3. Depending on the business that your widget offers, select the categories in the 'Category' field. 
      4. After registering your widget in  WidgetStore; your widget will be introduced in a separate page in  WidgetStore, so you can upload pictures of your widget in the 'Pictures' section.
      5. In the 'Services' section you must select the banking services that you want to use and need in your widget.
4. After completeing the above steps, The 'Appkey' and the 'AppSecret' of your widget will be display.Please save this information in a text file. 
5. when you developing your widget, please follow tips below:
   1. Widgets are loaded in Iframe in Netway, so you must set the 'X-Frame-Options' Header value to 'allow-from' for the Netway's URL.
   2. To ensure the widget is available, Netway sends a HTTP HEAD method request to the URL which you fill in the 'URL Schema'.
   3. If the Widget responses with 200 Http status code,Netway will send a HTTP GET method request with 'ssoJwt' query parameter to the URL which fill in the 'URL Schema'.  
   4. After the widget receives 'ssoJwt', it must be decoded by the widget. 
   5. The widget needs the value of 'ssoToken' in the payload of decoded 'ssoJwt' parameter to call the login service of  WidgetStore.
   6. After calling the login service of the Widget Store, the widget receives a json which conatins 'session_id'.
   7. With the 'session_id' the widget can be call all banking service.
   
### ssoJwt
the 'ssoJwt' parameter is a JWT(JSON Web Tokens).
      
for example:
>     
      
       eyJhbGciOiJub25lIn0.eyJqdGkiOiJlNDBmY2VkYy0zNmYyLTQxOTYtOTc2Yi1mMWUxY2NhOWQwY2YiLCJiYW5rQ29kZSI6IkJPSU1JUiIsInNzb1Rva2VuRXhwaXJhdGlvblRpbWUiOjE1ODA3MTMxNTcyODUsImxhbmd1YWdlIjoiZmEiLCJzc29Ub2tlbiI6IjM5YmZjNDA2LWZiMDgtNDk1Zi05MTNjLTUyMWM4MTFlNTRhNCIsImV4cCI6MTU4MDcxMzQ1NX0.
       
this token must be encoded by the widget and its payload contains the below information:

```json
   {
      "jti": "e40fcedc-36f2-4196-976b-f1e1cca9d0cf",
      "bankCode": "BOIMIR",
      "ssoTokenExpirationTime": 1580713157285,
      "language": "fa",
      "ssoToken": "39bfc406-fb08-495f-913c-521c811e54a4",
      "exp": 1580713455
   }
```
> exp is the token expiration date. See [https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.4](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.4).

> jti is the JWT Id. See [https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.7](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.7).

> bankCode is the swift code of bank.

> language is the selected language which is selected in Netway by the user.

> The widget can call  WidgetStore login service with the ssoToken.**it's disposable**

> ssoTokenExpirationTime is the ssoToken expiration date.

### Calling the WidgetStore login service
To call the WidgetStore login you must call this URL: 

> https://wistore-api-url/v1/auth/market/login

> HTTP Method: POST

You must set these parameters in the header of request

```
App-Key: YOUR-WIDGET-APPKEY
Device-Id: 192.168.1.1
Accept-Language: fa
Content-Type: application/json
ACCEPT: application/json
CLIENT-IP-ADDRESS: 192.168.1.1
CLIENT-PLATFORM-TYPE: ANDROID
CLIENT-DEVICE-ID: 192.168.1.1
CLIENT-USER-ID: 091212*****
CLIENT-USER-AGENT: Android - Android 5.1 - Sumsung - Gallexy8
```

You must set these parameters in the body of request

```
"username":"YOUR-WIDGET-STORE-USERNAME",
"password":"YOUR-WIDGET-PASSWORD"
```

The body of response is

```
"token":"dnfghsfl45md4df.ww/dsf5;ldsfksd,sfsdl",
"first_name":"USER-FIRST-NAME",
"last_name":"USER-LAST-NAME"
```

Then you must call this service with this URL:

> https://wistore-api-url/v1/auth/login/token

> HTTP Method: POST

You must set these parameters in the header of request

```
App-Key: YOUR-WIDGET-APPKEY
Device-Id: 192.168.1.1
Accept-Language: fa
Content-Type: application/json
ACCEPT: application/json
CLIENT-IP-ADDRESS: 192.168.1.1
CLIENT-PLATFORM-TYPE: ANDROID
CLIENT-DEVICE-ID: 192.168.1.1
CLIENT-USER-ID: 091212*****
CLIENT-USER-AGENT: Android - Android 5.1 - Sumsung - Gallexy8
Bank-Id:The-BankCode-You-Get-From-ssoJwt
token-Id:The-Token-Youe-Get-From-The-Previous-Service 
```
You must set these parameters in the body of request

```
"token":"ssoToken-You-Get-From-ssoJwt"
```

The body of response is

```
"code":"USER-CODE",
"gender":"USER-GENDER",
"name":"USER-FULL-NAME",
"session_id":"SESSION-ID",
"customer_number":"CUSTOMER-BANK-NUMBER",
"bankCode":"BANK-SWIFT-CODE",
"loginToken":"LOGIN-TOKEN"
```

### Calling the deposit list service(a banking service)
To call the the deposit list service you must call this URL: 

> https://wistore-api-url/v1/deposits

> HTTP Method: POST

You must set these parameters in the header of request

```
App-Key: YOUR-WIDGET-APPKEY
Device-Id: 192.168.1.1
Accept-Language: fa
Content-Type: application/json
ACCEPT: application/json
CLIENT-IP-ADDRESS: 192.168.1.1
CLIENT-PLATFORM-TYPE: ANDROID
CLIENT-DEVICE-ID: 192.168.1.1
CLIENT-USER-ID: 091212*****
CLIENT-USER-AGENT: Android - Android 5.1 - Sumsung - Gallexy8
Bank-Id:The-BankCode-You-Get-From-The-Previous-Service
session:The-session-You-Get-From-The-Previous-Service 
```

The body of response is a list of the below object

```
"deposit_number":"DEPOSIT-NUMBER",
"balance":"DEPOSIT-BALANCE"
```

### Open mobile application camera module and scan qr code

First of all write a function named onMessage and assign this function to ReactNativeWebView object.
This function have a param filled by qr data and called when qr code scanned.

```
function onMessage(data) {
   document.getElementById("div").innerHTML = "Camera Data: " + data;
}
window.ReactNativeWebView.onMessage = onMessage;
```

Then create a function to contact mobile application for openning camera, write something like this:

```
function openCamera() {
   var data = JSON.stringify({action: 'openCamera'});
   window.ReactNativeWebView.postMessage(data);
}
```

and call this function like this:

```
<button onclick="openCamera()">Open Camera</button>
```

Full example:

```
<html lang="en">
   <head>
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <script>
           function onMessage(data) {
               document.getElementById("div").innerHTML = "Camera Data: " + data;
           }
           window.ReactNativeWebView.onMessage = onMessage;
           function openCamera() {
               var data = JSON.stringify({action: 'openCamera'});
               window.ReactNativeWebView.postMessage(data);
           }
       </script>
   </head>
	<body>
		<h1 id="div">Camera: </h1>
		<button onclick="openCamera()">Open Camera</button>
	</body>
</html>
```

### Use digital signiture to signe documents (PDF or TXT)

Write a function named onMessage and assign this function to ReactNativeWebView object. This function have a param filled by signed data and called when document signing is compelete.

function onMessage(data) {
               document.getElementById("div").innerHTML = "Document: " + data;
           }
           window.ReactNativeWebView.onMessage = onMessage;
           function signDocument() {
				//var dataToSign = convert PDF to base64 and pass as string / pass txt as string
				//var dataType = PDF or TXT
               var data = JSON.stringify({action: 'signDocument', dataToSign: 'dataToSign', dataType: 'pdf'});
               window.ReactNativeWebView.postMessage(data);
           }
	   
and call this function like this:
<button onclick="signDocument()">Sign My Documetn</button>

Full example:
<html lang="en">
   <head>
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <script>
           function onMessage(data) {
               document.getElementById("div").innerHTML = "Document: " + data;
           }
           window.ReactNativeWebView.onMessage = onMessage;
           function signDocument() {
				//var dataToSign = convert PDF to base64 and pass as string / pass txt as string
				//var dataType = PDF or TXT
               var data = JSON.stringify({action: 'signDocument', dataToSign: 'dataToSign', dataType: 'pdf'});
               window.ReactNativeWebView.postMessage(data);
           }
       </script>
   </head>
	<body>
		<h1 id="div">Document: </h1>
		<button onclick="signDocument()">Sign My Documetn</button>
	</body>
</html>








   
