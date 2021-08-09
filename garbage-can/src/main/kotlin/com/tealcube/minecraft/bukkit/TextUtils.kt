/*
 * The MIT License
 * Copyright © 2017 Pixel Outlaw
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
package com.tealcube.minecraft.bukkit

import io.pixeloutlaw.minecraft.spigot.garbage.chatColorize
import io.pixeloutlaw.minecraft.spigot.garbage.replaceArgs

/**
 * Replacement for a removed class for backwards compatibility.
 */
@Deprecated("Use StringExtensionsKt or ListExtensionsKt instead")
object TextUtils {
    @Deprecated(
        "Use .chatColorize() instead",
        ReplaceWith("pList.chatColorize()", "io.pixeloutlaw.minecraft.spigot.garbage.chatColorize")
    )
    @JvmStatic
    fun color(pList: List<String>): List<String> = pList.chatColorize()

    @Deprecated(
        "Use .chatColorize() instead",
        ReplaceWith("pString.chatColorize()", "io.pixeloutlaw.minecraft.spigot.garbage.chatColorize")
    )
    @JvmStatic
    fun color(pString: String): String = pString.chatColorize()

    @Deprecated(
        "Use .replaceArgs(args) instead",
        ReplaceWith("pList.chatColorize(args)", "io.pixeloutlaw.minecraft.spigot.garbage.replaceArgs")
    )
    @JvmStatic
    fun args(pList: List<String>, args: Array<Array<String>>): List<String> = pList.replaceArgs(args)

    @Deprecated(
        "Use .replaceArgs(args) instead",
        ReplaceWith("pString.chatColorize(args)", "io.pixeloutlaw.minecraft.spigot.garbage.replaceArgs")
    )
    @JvmStatic
    fun args(pString: String, args: Array<Array<String>>): String = pString.replaceArgs(args)
}
