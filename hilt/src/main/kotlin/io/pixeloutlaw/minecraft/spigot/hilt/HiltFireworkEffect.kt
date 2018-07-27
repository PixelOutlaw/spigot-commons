package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.meta.FireworkEffectMeta

class HiltFireworkEffect(effect: FireworkEffect) : HiltItemStack(Material.FIREWORK_STAR) {

    val fireworkEffect: FireworkEffect?
        get() {
            createItemMeta()
            return if (itemMeta is FireworkEffectMeta && (itemMeta as FireworkEffectMeta).hasEffect()) {
                (itemMeta as FireworkEffectMeta).effect
            } else null
        }

    init {
        setFireworkEffect(effect)
    }

    fun setFireworkEffect(effect: FireworkEffect): HiltFireworkEffect {
        createItemMeta()
        if (itemMeta is FireworkEffectMeta) {
            (itemMeta as FireworkEffectMeta).effect = effect
        }
        return this
    }

}