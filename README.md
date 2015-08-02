ParseSendClient
==============================

Parse push send client.

Use Parse REST API Push Notifications

[Parse REST API](https://parse.com/docs/rest/guide/#quick-reference-push-notifications)

Usage
====
Create notification Object

```java
ParsePushModel model = new ParsePushModel().setTitle(pushTitle).setMessage(pushMessage);
```

Create channel

```java
String[] channel = new String[1];
channel[0] = "demo";
```

Send push.

```java
PushSendLogic.sendPush(model, channel, new PushSendLogic.PushSendCallBack() {
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String message) {

    }

});
```

Here's Custom push Object

```java
public class ParsePushModel {

    String title;
    String message;


    public ParsePushModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ParsePushModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
```

Download
====
Download via Gradle:

```
repositories {
    maven {
       jcenter()
    }
}

dependencies {
   compile 'com.bowyer.app:parsesendclient:0.1.0@aar'
   compile 'com.squareup.retrofit:retrofit:1.9.0'
   compile 'com.google.code.gson:gson:1.7.2'
   compile 'com.squareup.okhttp:okhttp:2.2.0'
   compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
}
```

# parsepush.properties

add parsepush.properties into resources dir

| property  | description |
| ------------- | ------------- |
| PARSE_APPLICATION_ID | your Application ID |
| PARSE_REST_API_KEY | your Client Key |
| PARSE_REST_API_KEY | your REST API Key |

![Parse](./art/properties.png)
License
--------
```
Copyright (c) 2015 Bowyer
Released under the MIT license
http://opensource.org/licenses/mit-license.php
```
