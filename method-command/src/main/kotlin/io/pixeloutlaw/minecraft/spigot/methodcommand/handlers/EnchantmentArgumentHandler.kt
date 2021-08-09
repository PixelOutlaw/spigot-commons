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
package io.pixeloutlaw.minecraft.spigot.methodcommand.handlers

import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import se.ranzdo.bukkit.methodcommand.ArgumentHandler
import se.ranzdo.bukkit.methodcommand.CommandArgument
import se.ranzdo.bukkit.methodcommand.TransformError

class EnchantmentArgumentHandler : ArgumentHandler<Enchantment>() {
    init {
        setMessage("parse_error", "There is no Enchantment named %1")
        setMessage("include_error", "There is no Enchantment named %1")
        setMessage("exclude_error", "There is no Enchantment named %1")
    }

    @Throws(TransformError::class)
    override fun transform(sender: CommandSender, argument: CommandArgument, value: String): Enchantment {
        val ench = try {
            Enchantment.getByKey(NamespacedKey.minecraft(value))
        } catch (ex: Exception) {
            Enchantment.getByName(value)
        }
        return ench ?: throw TransformError(argument.getMessage("parse_error", value))
    }
}