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
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.HashMap

class HiltEnchantedBook(enchantmentMap: Map<Enchantment, Int>) : HiltItemStack(Material.ENCHANTED_BOOK) {

    val storedEnchantments: Map<Enchantment, Int>
        get() {
            createItemMeta()
            return if (itemMeta is EnchantmentStorageMeta) {
                HashMap((itemMeta as EnchantmentStorageMeta).storedEnchants)
            } else HashMap()
        }

    init {
        setStoredEnchantments(enchantmentMap)
    }

    fun setStoredEnchantments(enchantments: Map<Enchantment, Int>): HiltEnchantedBook {
        createItemMeta()
        if (itemMeta is EnchantmentStorageMeta) {
            val enchantmentStorageMeta = itemMeta as EnchantmentStorageMeta
            if (enchantmentStorageMeta.hasStoredEnchants()) {
                for (entry in enchantmentStorageMeta.storedEnchants.entries) {
                    enchantmentStorageMeta.removeStoredEnchant(entry.key)
                }
            }
            for ((key, value) in enchantments) {
                enchantmentStorageMeta.addStoredEnchant(key, value, true)
            }
        }
        return this
    }

}