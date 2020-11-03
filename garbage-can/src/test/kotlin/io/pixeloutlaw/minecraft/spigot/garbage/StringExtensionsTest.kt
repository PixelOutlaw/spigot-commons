package io.pixeloutlaw.minecraft.spigot.garbage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StringExtensionsTest {
    @Test
    fun `does toTitleCase convert string to title case`() {
        // given
        val toConvert = "a boy went to the park"

        // when
        val actual = toConvert.toTitleCase()

        // then
        assertThat(actual).isEqualTo("A Boy Went To The Park")
    }
}
