<h1 align="center"> LibRadioactive </h1>
<p align="center"> A lib to create radioactive items, blocks and contaminated areas </a>
<p align="center"> And now you can create pollution blocks and areas (above 0.1.2) </p>
<p align="center">
    <a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-brightgreen.svg"></a>
    <a href="https://github.com/Rochedo098/LibRadioactive/">  <img src="http://cf.way2muchnoise.eu/versions/391708_latest.svg"> </a>
</p>

### Some Information's
A large part of the LibRadioactive API is based on CottonMC's LibPolution API  
Thanks for that

## Importing the API

```groovy
/* File: build.gradle */
repositories {
    maven {
        name = "Rochedo098"
        url = "https://github.com/Rochedo098/rochedo098.github.io/tree/master/maven"
        content {
            includeGroup "com.github.rochedo098"
        }
    }   
}

dependencies {
    // Using gradle.properties
    modImplementation include("com.github.rochedo098:LibRadioactive:${project.libradioactive_version}")
    
    // Without gradle.properties
    modImplementation include("com.github.rochedo098:LibRadioactive:0.1.1")
}
```

```properties
## File: gradle.properties ##
libradioactive_version = "0.1.1"
```

## Building From Source

### Linux

Run `./gradlew build`, the compiled jar will be in the `/build/libs` folder.  
You must obtain the file without -dev and -sources.

### Windows

Run `gradlew.bat build`, the compiled jar will be in the `/build/libs` folder.  
You must obtain the file without -dev and -sources.

## License

This project is available under the Apache License, Version 2.0. See `LICENSE` for more information
