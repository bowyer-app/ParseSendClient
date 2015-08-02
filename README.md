ParseSendClient
==============================

Parse push send client.

Use Parse REST API Push Notifications

[Parse REST API](https://parse.com/docs/rest/guide/#quick-reference-push-notifications)

Usage
====


Download
====
Download via Gradle:

```
repositories {
    maven {
       url "https://dl.bintray.com/bowyer-app/maven/"
    }
}

dependencies {
   compile 'com.bowyer.app:parsesendclient:0.1.0'
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

Copyright (c) 2015 Bowyer
Released under the MIT license
http://opensource.org/licenses/mit-license.php
