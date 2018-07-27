package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material

enum class PotionType(val material: Material) {
    POTION(Material.POTION),
    SPLASH_POTION(Material.SPLASH_POTION),
    LINGERING_POTION(Material.LINGERING_POTION),
    TIPPED_ARROW(Material.TIPPED_ARROW)
}