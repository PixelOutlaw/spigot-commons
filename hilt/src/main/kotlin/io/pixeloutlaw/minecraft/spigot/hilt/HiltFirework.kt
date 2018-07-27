package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.inventory.meta.FireworkMeta
import java.util.ArrayList

class HiltFirework(fireworkEffects: Collection<FireworkEffect>, power: Int) : HiltItemStack(Material.FIREWORK_ROCKET) {

    val power: Int
        get() {
            createItemMeta()
            return if (itemMeta is FireworkMeta) {
                (itemMeta as FireworkMeta).power
            } else 0
        }

    val fireworkEffects: List<FireworkEffect>
        get() {
            createItemMeta()
            return if (itemMeta is FireworkMeta && (itemMeta as FireworkMeta).hasEffects()) {
                ArrayList((itemMeta as FireworkMeta).effects)
            } else ArrayList()
        }

    init {
        setFireworkEffects(fireworkEffects)
        setPower(power)
    }

    fun setPower(power: Int): HiltFirework {
        createItemMeta()
        if (itemMeta is FireworkMeta) {
            (itemMeta as FireworkMeta).power = power
        }
        return this
    }

    fun setFireworkEffects(effects: Collection<FireworkEffect>): HiltFirework {
        createItemMeta()
        if (itemMeta is FireworkMeta) {
            (itemMeta as FireworkMeta).addEffects(effects)
        }
        return this
    }

}