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

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect

open class HiltPotion(
    potionType: PotionType,
    private val originalPotionData: PotionData,
    effects: Collection<PotionEffect>
) : ItemStack(potionType.material) {
    var basePotionData: PotionData
        get() = getFromItemMetaAs<PotionMeta, PotionData> { basePotionData } ?: originalPotionData
        set(value) {
            getThenSetItemMetaAs<PotionMeta> { basePotionData = value }
        }
    var effects: Collection<PotionEffect>
        get() = getFromItemMetaAs<PotionMeta, Collection<PotionEffect>> {
            if (hasCustomEffects()) {
                customEffects
            } else {
                listOf()
            }
        } ?: listOf()
        set(value) {
            getThenSetItemMetaAs<PotionMeta> {
                if (hasCustomEffects()) {
                    clearCustomEffects()
                }
                for (potionEffect in value) {
                    addCustomEffect(potionEffect, false)
                }
            }
        }

    init {
        basePotionData = originalPotionData
        this.effects = effects
    }
}
