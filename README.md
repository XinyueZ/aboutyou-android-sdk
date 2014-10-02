# aboutyou.de Shop SDK for Android

The aboutyou.de Shop SDK for Android is a library for [AboutYou](http://aboutyou.de) Shop integration in Android.

## Getting Started

* [Register for an account](https://devcenter.aboutyou.de) at the AboutYou Devcenter and create a new app. You will be given credentials to utilize the About You API.
* Add a depedency to the aboutyou.de Shop SDK Android to you project - see section "Download" below for details
* Run and discover the sample application to become familiar with the basic principles of the aboutyou.de Shop SDK for Android.
* Need some help? Feel free to ask questions to henning.dodenhof(at)slice-dice.de

### Overview

To get your app running with the aboutyou.de Shop SDK for Android just obtain a new `ShopAPIClient` instance providing your app credentials and some configuration options. The `ShopAPIClient` is the only class you need to interact with for all your requests to the aboutyou.de backend, you should use a single instance all over your app.
 
Running a request is fairly easy:

* Instanciate a new request builder for your desired request, e.g. a `ProductSearchRequest.Builder`
* Configure the request using method chaining on the request builder
* Build the request calling `build()` on the request builder
* Call the corresponding request method to execute the request, e.g. `shopAPIClient.requestProductSearch()`. As the request is executing a network call, you have to do this from a background thread, e.g. an `AsyncTask`.

Putting all this together results in:
```java
final ProductSearchRequest request = new ProductSearchRequest.Builder("foobar")
        .filterByMinPrice(1000)
        .filterByMaxPrice(5000)
        .sortBy(Sortby.RELEVANCE, Direction.DESC)
        .limit(10)
        .build();

new AsyncTask<Void, Void, Void>(){
    @Override
    protected Void doInBackground(Void... params) {
        ProductSearch productSearch = shopApiClient.requestProductSearch(request);
        return null;
    }
}.execute();
```

All requests may throw a `CollinsException`, which is either a low-level `NetworkException` or a `HttpException` containing a `HttpError` instance with some more information about what went wrong.

### OAuth2

You can access user details from the aboutyou.de shop backend using OAuth2. To start the OAuth2 flow just call `shopApiClient.requestAuthentication()` providing the request details (no builder pattern here) and an `AuthenticationCallback`. The user will be prompted with a web-dialog to login / register and authorize your app. If all goes well, `onSuccess()` on the `AuthenticationCallback` is called with an accessToken - in case of an error `onError()` is called instead. If the user cancel the OAuth flow, `onCancel()` will be called.

With the newly obtained accessToken you can now request a user object using `requestShopUser()`. The `ShopUser` instance that is returned contains only the fields, you requested access to using the `List<AuthScope>` parameter of `shopApiClient.requestAuthentication()`.

A simplified example:
```java
private void doAuth() {
    List<AuthScope> scopes = Arrays.asList(AuthScope.FIRSTNAME, AuthScope.LASTNAME, AuthScope.ID, AuthScope.EMAIL);
    String redirectUrl = "http://mp.sdk/oauth";
    ShopApiClient.AuthenticationCallback callback = new ShopApiClient.AuthenticationCallback() {
        @Override
        public void onSuccess(String accessToken) {
            getUser(accessToken);
        }
    
        @Override
        public void onCancel() {
            // User canceled
        }
    
        @Override
        public void onFailure() {
            // Something went wrong
        }
    };
    
    shopApiClient.requestAuthentication(getContext(), scopes, AuthenticationRequestMode.DEFAULT, redirectUrl, callback);
}

private void getUser(final String accessToken) {
    new AsyncTask<Void, Void, ShopUser>(){
        @Override
        protected ShopUser doInBackground(Void... params) {
            return shopApiClient.requestShopUser(accessToken);
        }
        
        @Override
        protected void onPostExecute(ShopUser shopUser) {
            Toast.makeText(getContext(), String.format("Hello %s", shopUser.getFirstname()), Toast.LENGTH_SHORT).show();
        }        
    }.execute();
}
```

## Download
Grab the latest release via Maven:
```xml
<dependency>
  <groupId>de.abouyou</groupId>
  <artifactId>shop-sdk-android</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'de.aboutyou:shop-sdk-android:1.0.0'
```

## Changelog
* **1.0.0**
    * Initial release

## License

    The MIT License (MIT)
    
    Copyright (c) 2014 tbd
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
=======
The aboutyou.de Shop SDK for Android is gonna be a library for [AboutYou](http://aboutyou.de) Shop integration in Android.
