package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Color
import org.bukkit.inventory.meta.LeatherArmorMeta

class HiltLeatherArmor(type: LeatherArmorType, color: Color) : HiltItemStack(type.mat) {

    val color: Color?
        get() {
            createItemMeta()
            return if (itemMeta is LeatherArmorMeta) {
                (itemMeta as LeatherArmorMeta).color
            } else null
        }

    init {
        setColor(color)
    }

    fun setColor(color: Color): HiltLeatherArmor {
        createItemMeta()
        if (itemMeta is LeatherArmorMeta) {
            (itemMeta as LeatherArmorMeta).color = color
        }
        return this
    }

}