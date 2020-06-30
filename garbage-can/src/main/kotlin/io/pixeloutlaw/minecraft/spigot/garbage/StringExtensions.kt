package io.pixeloutlaw.minecraft.spigot.garbage

import org.bukkit.ChatColor

/**
 * Replaces all arguments (first item in pair) with their values (second item in pair).
 *
 * @param args Pairs of arguments to replace
 * @return copy of [String] with arguments replaced
 */
fun String.replaceArgs(vararg args: Pair<String, String>): String =
    args.fold(this) { acc, pair -> acc.replace(pair.first, pair.second) }

/**
 * Replaces all arguments (first item in pair) with their values (second item in pair).
 *
 * @param args Pairs of arguments to replace
 * @return copy of [String] with arguments replaced
 */
fun String.replaceArgs(args: Collection<Pair<String, String>>): String =
    args.fold(this) { acc, pair -> acc.replace(pair.first, pair.second) }

/**
 * Replaces all ampersands with [ChatColor.COLOR_CHAR] and all instances of two [ChatColor.COLOR_CHAR] with ampersands.
 */
fun String.chatColorize(): String = this.replace('&', '\u00A7').replace("\u00A7\u00A7", "&")

/**
 * Replaces all [ChatColor.COLOR_CHAR] with ampersands.
 */
fun String.unChatColorize(): String = this.replace('\u00A7', '&')

/**
 * Strips the colors from the String.
 */
fun String.stripColors(): String = ChatColor.stripColor(this)!! // using double bangs because `this` cannot be null

/**
 * Returns true if the String starts with any of the provided [list].
 */
@JvmOverloads
fun String.startsWithAny(list: List<String>, ignoreCase: Boolean = false): Boolean {
    for (str in list) {
        if (this.startsWith(str, ignoreCase)) {
            return true
        }
    }
    return false
}

/**
 * Returns true if the String ends with any of the provided [list].
 */
@JvmOverloads
fun String.endsWithAny(list: List<String>, ignoreCase: Boolean = false): Boolean {
    for (str in list) {
        if (this.endsWith(str, ignoreCase)) {
            return true
        }
    }
    return false
}
