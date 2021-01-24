# bandsaw
> Ow, my finger!

Bandsaw is a utility library for getting sensibly formatted logs written
to a file for Spigot plugins.

## Deprecated

Use the log4k library instead: https://github.com/saschpe/log4k

## Usage

First, you need to create a `PluginFileHandler` by passing in your plugin. Then you can pass customizers
to the `JulLoggerFactory` that will make loggers use the `PluginFileHandler`.

### Java
```java
package example.plugin;

import io.pixeloutlaw.minecraft.spigot.bandsaw.BandsawLoggerCustomizer;
import io.pixeloutlaw.minecraft.spigot.bandsaw.JulLoggerFactory;
import io.pixeloutlaw.minecraft.spigot.bandsaw.LoggerExtensionsKt;
import io.pixeloutlaw.minecraft.spigot.bandsaw.PluginFileHandler;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ExamplePlugin extends JavaPlugin {
    private static Logger LOGGER = null;

    @Override
    public void onEnable() {
        PluginFileHandler fileHandler = new PluginFileHandler(plugin);
        JulLoggerFactory.registerLoggerCustomizer("example.plugin", new BandsawLoggerCustomizer() {
            @NotNull public Logger customize(@NotNull String name, @NotNull Logger logger) {
                return LoggerExtensionsKt.rebelliousAddHandler(logger, fileHandler);
            }
        });
        LOGGER = JulLoggerFactory.getLogger(ExamplePlugin.class);
        LOGGER.info("Enabled!");
    }
}
```

### Kotlin
```kotlin
package example.plugin

import io.pixeloutlaw.minecraft.spigot.bandsaw.BandsawLoggerCustomizer
import io.pixeloutlaw.minecraft.spigot.bandsaw.JulLoggerFactory
import io.pixeloutlaw.minecraft.spigot.bandsaw.PluginFileHandler
import io.pixeloutlaw.minecraft.spigot.bandsaw.rebelliousAddHandler
import java.util.logging.Logger
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.NotNull

class ExamplePlugin: JavaPlugin() {
    private val logger by lazy {
        JulLoggerFactory.getLogger(ExamplePlugin::class)
    }

    override fun onEnable() {
        val fileHandler = PluginFileHandler(plugin);
        JulLoggerFactory.registerLoggerCustomizer("example.plugin", object : BandsawLoggerCustomizer {
            override fun customize(name: String, logger: Logger): Logger =
                logger.rebelliousAddHandler(fileHandler)
        });
        logger.info("Enabled!")
    }
}
```