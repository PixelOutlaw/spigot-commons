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