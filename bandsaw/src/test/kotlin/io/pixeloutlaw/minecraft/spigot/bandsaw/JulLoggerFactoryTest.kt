package io.pixeloutlaw.minecraft.spigot.bandsaw

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.logging.Logger

internal class JulLoggerFactoryTest {
    @BeforeEach
    fun setup() {
        JulLoggerFactory.clearCachedLoggers()
        JulLoggerFactory.clearCustomizers()
    }

    @Test
    fun `does getCustomizersForName find empty list when no customizers exist`() {
        assertThat(JulLoggerFactory.getCustomizersForName("dank.memes.DankMemes")).isEmpty()
    }

    @Test
    fun `does getCustomizersForName find non-empty list when customizers exist`() {
        JulLoggerFactory.registerLoggerCustomizer("dank.memes", object : BandsawLoggerCustomizer {
            override fun customize(name: String, logger: Logger): Logger = logger
        })

        assertThat(JulLoggerFactory.getCustomizersForName("dank.memes.DankMemes")).isNotEmpty
    }

    @Test
    fun `does getCustomizersForName work with canonical names`() {
        JulLoggerFactory.registerLoggerCustomizer("io.pixeloutlaw.minecraft.spigot.bandsaw", object : BandsawLoggerCustomizer {
            override fun customize(name: String, logger: Logger): Logger = logger
        })

        assertThat(JulLoggerFactory.getCustomizersForName(JulLoggerFactoryTest::class.java.canonicalName)).isNotEmpty
    }
}