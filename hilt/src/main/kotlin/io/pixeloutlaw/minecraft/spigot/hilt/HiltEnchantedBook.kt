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
package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.HashMap

open class HiltEnchantedBook(enchantmentMap: Map<Enchantment, Int>) : ItemStack(Material.ENCHANTED_BOOK) {
    var storedEnchantments: Map<Enchantment, Int>
        get() {
            return if (itemMeta is EnchantmentStorageMeta) {
                (itemMeta as EnchantmentStorageMeta).storedEnchants.toMap()
            } else HashMap()
        }
        set(values) {
            getThenSetItemMetaAs<EnchantmentStorageMeta> {
                if (hasStoredEnchants()) {
                    for (entry in storedEnchants.entries) {
                        removeStoredEnchant(entry.key)
                    }
                }
                for ((key, value) in values) {
                    addStoredEnchant(key, value, true)
                }
            }
        }

    init {
        storedEnchantments = enchantmentMap
    }
}