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
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.TropicalFishBucketMeta

open class HiltTropicalFishBucket(
    bodyColor: DyeColor,
    pattern: TropicalFish.Pattern,
    patternColor: DyeColor
) : ItemStack(Material.TROPICAL_FISH_BUCKET) {
    var bodyColor: DyeColor
        get() = getFromItemMetaAs<TropicalFishBucketMeta, DyeColor> {
            if (hasVariant()) {
                bodyColor
            } else {
                DyeColor.BLACK
            }
        } ?: DyeColor.BLACK
        set(value) {
            getThenSetItemMetaAs<TropicalFishBucketMeta> { bodyColor = value }
        }

    var pattern: TropicalFish.Pattern
        get() = getFromItemMetaAs<TropicalFishBucketMeta, TropicalFish.Pattern> {
            if (hasVariant()) {
                pattern
            } else {
                TropicalFish.Pattern.BETTY
            }
        } ?: TropicalFish.Pattern.BETTY
        set(value) {
            getThenSetItemMetaAs<TropicalFishBucketMeta> { pattern = value }
        }

    var patternColor: DyeColor
        get() = getFromItemMetaAs<TropicalFishBucketMeta, DyeColor> {
            if (hasVariant()) {
                patternColor
            } else {
                DyeColor.BLACK
            }
        } ?: DyeColor.BLACK
        set(value) {
            getThenSetItemMetaAs<TropicalFishBucketMeta> { patternColor = value }
        }

    init {
        this.bodyColor = bodyColor
        this.pattern = pattern
        this.patternColor = patternColor
    }
}