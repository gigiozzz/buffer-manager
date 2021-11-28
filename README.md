# Buffer Manager

> TODO add badge or taglist  <!-- " ([![Build Status](https://img.shields.io/travis/dwyl/quotes/master.svg?style=flat-square)](https://travis-ci.org/dwyl/quotes))" -->


This library is a collection of class and interface to have a fully customizable ( and tested :D ) buffer system.

## Installing / Getting started

This library is based on maven so to use it you should include in your pom.

```maven
<dependency>
	<groupId>com.gigiozzz</groupId>
	<artifactId>buffer-manager</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```
### Initial Configuration

Install Java JDK 11.

## Developing

>TODO description

### Building

To build the library a configured maven system is required:

```shell
mvn clean install javadoc:javadoc
```

Here again you should state what actually happens when the code above gets
executed.

## Features

The list of the feature performed by this library
* Buffer interface with the most common operations (i.e.: add get peek remove flush)
* A way to implment a flush strategy
* A way to implment a persistence strategy
* 2 standard implmentation based on Circular Ring structure and ArrayList
* A statndard factory to initiate the 2 buffers
* TODO: Asynchronous version of previous features

## Configuration

>TODO description

## Links

- Project homepage: https://github.com/gigiozzz/buffer-manager
- Repository: https://github.com/gigiozzz/buffer-manager
- Issue tracker: https://github.com/gigiozzz/buffer-manager/issues

## Licensing

"The code in this project is licensed under Apache license 2.0."