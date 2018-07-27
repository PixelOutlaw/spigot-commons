package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material

enum class BookType(val material: Material) {
    WRITABLE_BOOK(Material.WRITABLE_BOOK),
    WRITTEN_BOOK(Material.WRITTEN_BOOK)
}