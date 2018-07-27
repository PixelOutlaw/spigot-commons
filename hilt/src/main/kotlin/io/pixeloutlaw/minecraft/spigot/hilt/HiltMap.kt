package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material
import org.bukkit.inventory.meta.MapMeta

class HiltMap(scalable: Boolean) : HiltItemStack(Material.FILLED_MAP) {

    val isScalable: Boolean
        get() {
            createItemMeta()
            return itemMeta is MapMeta && (itemMeta as MapMeta).isScaling
        }

    init {
        setScalable(scalable)
    }

    fun setScalable(b: Boolean): HiltMap {
        createItemMeta()
        if (itemMeta is MapMeta) {
            (itemMeta as MapMeta).isScaling = b
        }
        return this
    }

}