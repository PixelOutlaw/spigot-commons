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
