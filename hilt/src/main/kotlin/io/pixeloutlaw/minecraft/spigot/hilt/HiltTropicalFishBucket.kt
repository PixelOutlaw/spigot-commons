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

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.TropicalFish
import org.bukkit.inventory.meta.TropicalFishBucketMeta

class HiltTropicalFishBucket(
        bodyColor: DyeColor,
        pattern: TropicalFish.Pattern,
        patternColor: DyeColor
) : HiltItemStack(Material.TROPICAL_FISH_BUCKET) {

    val bodyColor: DyeColor?
        get() {
            createItemMeta()
            return if (itemMeta is TropicalFishBucketMeta) {
                (itemMeta as TropicalFishBucketMeta).bodyColor
            } else null
        }

    val pattern: TropicalFish.Pattern?
        get() {
            createItemMeta()
            return if (itemMeta is TropicalFishBucketMeta) {
                (itemMeta as TropicalFishBucketMeta).pattern
            } else null
        }

    val patternColor: DyeColor?
        get() {
            createItemMeta()
            return if (itemMeta is TropicalFishBucketMeta) {
                (itemMeta as TropicalFishBucketMeta).patternColor
            } else null
        }

    fun setBodyColor(bodyColor: DyeColor): HiltTropicalFishBucket {
        createItemMeta()
        if (itemMeta is TropicalFishBucketMeta) {
            (itemMeta as TropicalFishBucketMeta).bodyColor = bodyColor
        }
        return this
    }

    fun setPattern(pattern: TropicalFish.Pattern): HiltTropicalFishBucket {
        createItemMeta()
        if (itemMeta is TropicalFishBucketMeta) {
            (itemMeta as TropicalFishBucketMeta).pattern = pattern
        }
        return this
    }

    fun setPatternColor(patternColor: DyeColor): HiltTropicalFishBucket {
        createItemMeta()
        if (itemMeta is TropicalFishBucketMeta) {
            (itemMeta as TropicalFishBucketMeta).patternColor = patternColor
        }
        return this
    }

}