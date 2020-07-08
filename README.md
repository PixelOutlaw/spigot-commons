# spigot-commons
Common Utilities for all Pixel Outlaw Spigot plugins.

[![](https://jitpack.io/v/io.pixeloutlaw/spigot-commons.svg)](https://jitpack.io/#io.pixeloutlaw/spigot-commons)

![Bintray](https://img.shields.io/bintray/v/pixeloutlaw/pixeloutlaw-jars/spigot-commons?style=flat-square)

## How to Get
Add the following repository to your POM:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
or
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com</url>
    </repository>
</repositories>
```


Add the following dependency to your POM:
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.pixeloutlaw.spigot-commons</groupId>
            <artifactId>spigot-commons-bom</artifactId>
            <version>1.16.1.x</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
