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