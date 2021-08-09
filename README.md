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


## CI/CD

### CI

`mvn verify` will be run on every PR.

### CD / Making a Release

Creating a GitHub release will make a new release with the version from the GitHub release name and publish it to the
Sonatype staging repository. You will need ToppleTheNun to log into the Sonatype account and release the update to
Maven Central from the staging repository.
