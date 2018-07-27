package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.OfflinePlayer
import org.bukkit.inventory.meta.SkullMeta

class HiltSkull(skullType: SkullType, owner: OfflinePlayer) : HiltItemStack(skullType.material) {

    val owner: OfflinePlayer?
        get() {
            createItemMeta()
            return if (itemMeta is SkullMeta && (itemMeta as SkullMeta).hasOwner()) {
                (itemMeta as SkullMeta).owningPlayer
            } else null
        }

    init {
        setOwner(owner)
    }

    fun setOwner(owner: OfflinePlayer): HiltSkull {
        createItemMeta()
        if (itemMeta is SkullMeta) {
            (itemMeta as SkullMeta).owningPlayer = owner
        }
        return this
    }

}
