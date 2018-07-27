package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.meta.BannerMeta
import java.util.ArrayList

class HiltBanner(bannerType: BannerType, patterns: Collection<Pattern>) : HiltItemStack(bannerType.mat) {
    val patterns: Collection<Pattern>
        get() {
            createItemMeta()
            return if (itemMeta is BannerMeta) {
                (itemMeta as BannerMeta).patterns
            } else ArrayList()
        }

    init {
        setPatterns(patterns)
    }

    fun setPatterns(patterns: Collection<Pattern>): HiltBanner {
        createItemMeta()
        if (itemMeta is BannerMeta) {
            (itemMeta as BannerMeta).patterns = ArrayList(patterns)
        }
        return this
    }

}