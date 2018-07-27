package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import java.util.ArrayList

class HiltPotion(
        potionType: PotionType,
        potionData: PotionData,
        effects: Collection<PotionEffect>
) : HiltItemStack(potionType.material) {

    val basePotionData: PotionData?
        get() {
            createItemMeta()
            return if (itemMeta is PotionMeta) {
                (itemMeta as PotionMeta).basePotionData
            } else null
        }

    val effects: List<PotionEffect>
        get() {
            createItemMeta()
            return if (itemMeta is PotionMeta && (itemMeta as PotionMeta).hasCustomEffects()) {
                ArrayList((itemMeta as PotionMeta).customEffects)
            } else ArrayList()
        }

    init {
        setBasePotionData(potionData)
        setEffects(effects)
    }

    fun setBasePotionData(potionData: PotionData): HiltPotion {
        createItemMeta()
        if (itemMeta is PotionMeta) {
            (itemMeta as PotionMeta).basePotionData = potionData
        }
        return this
    }

    fun setEffects(effects: Collection<PotionEffect>): HiltPotion {
        createItemMeta()
        if (itemMeta is PotionMeta) {
            if ((itemMeta as PotionMeta).hasCustomEffects()) {
                (itemMeta as PotionMeta).clearCustomEffects()
            }
            for (potionEffect in effects) {
                (itemMeta as PotionMeta).addCustomEffect(potionEffect, false)
            }
        }
        return this
    }

}
