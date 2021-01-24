/*
 * The MIT License
 * Copyright © 2015 Pixel Outlaw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.bandsaw

import java.util.logging.Logger
import kotlin.reflect.KClass

/**
 * Utility for getting a [Logger] with file logging defaults.
 */
@Deprecated("Use log4k instead")
object JulLoggerFactory {
    private val cachedLoggers = mutableMapOf<String, Logger>()
    private val loggerCustomizers = mutableMapOf<String, List<BandsawLoggerCustomizer>>()

    @JvmStatic
    fun getLogger(clazz: Class<*>) = getLogger(clazz.canonicalName)

    @JvmStatic
    fun getLogger(clazz: KClass<*>) = getLogger(clazz.java)

    @JvmStatic
    fun getLogger(name: String) = cachedLoggers.getOrPut(name) {
        getCustomizersForName(name).fold(Logger.getLogger(name), { logger, customizer ->
            customizer.customize(name, logger)
        })
    }

    /**
     * Registers a [BandsawLoggerCustomizer] to customize a [Logger].
     *
     * @param name name of logger to customize
     * @param customizer customizer
     */
    @JvmStatic
    fun registerLoggerCustomizer(name: String, customizer: BandsawLoggerCustomizer) {
        loggerCustomizers[name] =
            loggerCustomizers.getOrDefault(name, emptyList()).toMutableList().apply { add(customizer) }.toList()
    }

    // internal-only for testing
    internal fun getCustomizersForName(name: String): List<BandsawLoggerCustomizer> {
        val matchingCustomizerNames = loggerCustomizers.keys.filter { name.startsWith(it) }
        return matchingCustomizerNames.mapNotNull { loggerCustomizers[it] }.flatten()
    }

    internal fun clearCustomizers() {
        loggerCustomizers.clear()
    }

    internal fun clearCachedLoggers() {
        cachedLoggers.clear()
    }
}
