package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.meta.BookMeta
import java.util.ArrayList

class HiltBook(bookType: BookType) : HiltItemStack(bookType.material) {

    val title: String
        get() {
            createItemMeta()
            return if (itemMeta is BookMeta && (itemMeta as BookMeta).hasTitle()) {
                (itemMeta as BookMeta).title
            } else ""
        }

    val pages: List<String>
        get() {
            createItemMeta()
            return if (itemMeta is BookMeta && (itemMeta as BookMeta).hasPages()) {
                ArrayList((itemMeta as BookMeta).pages)
            } else ArrayList()
        }

    fun setTitle(title: String): HiltBook {
        createItemMeta()
        if (itemMeta is BookMeta) {
            (itemMeta as BookMeta).title = title
        }
        return this
    }

    fun setPages(pages: List<String>): HiltBook {
        createItemMeta()
        if (itemMeta is BookMeta) {
            (itemMeta as BookMeta).pages = pages
        }
        return this
    }

}