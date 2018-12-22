# MaterialToast
A fully and highly customizable material designed Toast for Android.

<img src="https://raw.githubusercontent.com/marcoscgdev/MaterialToast/master/device-2018-12-22-164932.png" width="350">

---

## Releases:

#### Current release: 1.0.0.

You can see all the library releases [here](https://github.com/marcoscgdev/MaterialToast/releases).

---

## Usage:

### Adding the depencency

Add this to your root *build.gradle* file:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Now add the dependency to your app build.gradle file:

```
implementation 'com.github.marcoscgdev:MaterialToast:1.0.0'
```

### Creating a Toast

 - Native version

```java
MaterialToast.makeText(this, "Hello, I'm a material toast!", Toast.LENGTH_SHORT).show();
```

Also with custom icon

```java
MaterialToast.makeText(this, "Hello, I'm a material toast!", R.mipmap.ic_launcher, Toast.LENGTH_SHORT).show();
```

And also with custom background color (text will be automatically colored based on background color)

```java
MaterialToast.makeText(this, "Hello, I'm a material toast!", R.mipmap.ic_launcher, Toast.LENGTH_SHORT).setBackgroundColor(Color.RED).show();
```

 - Complete version
 
 ```java
new MaterialToast(this)
        .setMessage("Hello, I'm a material toast!")
        .setIcon(R.mipmap.ic_launcher)
        .setDuration(Toast.LENGTH_SHORT)
        .setBackgroundColor(Color.RED)
        .show();
```

---
>See the *sample project* to clarify any queries you may have.

---

## License

```
Copyright 2018 Marcos Calvo García

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
