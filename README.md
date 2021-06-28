# tosan-widget-guide

Widgets are loaded in Iframe in Netway; So developers can use any programming language and platform to create their own widgets.

Pay attention to the following points when developing and installing your widget.

1. The types of widgets with their feuters are listed in the table below. developers need to choose one of  the types when registering their widgets in the Widget Store.

   1. When you choose Full type, Netway will display your widget in a seperate page
   2. The security of the widgets is the responsibility of the bank and need to be checked by the bank.

Widget's type | Widget's width | Widget's height 
------------- | -------------- | ---------------
SMALL | 300 px | 216 px
SMALL | 300 px | 445 px
WIDE | 630 px | 216 px
MEDIUM | 630 px | 445 px
FULL | 900 px | 400 px

2. To register a widget in the Widget Store, first create an account in the Wiget Store. 
   1. If you don't have an account in Widget Store, first click the 'Signup' button, next complete the form.
   2. after login into the Widget Store; At the top of the siteو click the 'Upgrade To Developer Account' and fill the form.
   2. After your request is approved by the admin, your account will change to Developer-Account. 
   
3. You must register your widget information in the Widget Store.
   1. Login into the Widget Store.
   2. After login, click  the 'User Panel' to enter the user panel section. 
   3. in the user panel section click 'Developer Panel' menu, next click the 'Developer Apps' sub menu.
   4. click the 'New' button
   5. on the 'Create New App' window choose 'Widget' type
   6. click the 'Save' button 
   7. in this step you must fill the form about all information of your widget, then click the 'Next' button.
      1. URL Schema is your widget url and Netway uses this URL to call your widget. 
      2. In the 'Operating System' field, select 'Web'.
      3. Depending on the business that your widget offers, select the categories in the 'Category' field. 
      4. After registering your widget in the Widget Store; your widget will be introduced in a separate page in the Widget Store, so you can upload the pictures of your widget in the 'Pictures' section.
      5. In the 'Services' section you must select the banking services that you want to use and need in your widget.
4. After completeing the above steps, The 'Appkey' and the 'AppSecret' of your widget will be display.Please save this information in a text file. 
5. when you developing your widget, please follow the tips below:
   1.   Widgets are loaded in Iframe in Netway, so you must set the 'X-Frame-Options' Header value to 'allow-from' for the Netway's URL.
   2.   To ensure the widget is available, Netway sends a HTTP HEAD method request to the URL which you fill in the 'URL Schema'.
   3.    
       
   
   
