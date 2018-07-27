package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.KnowledgeBookMeta
import java.util.*

class HiltKnowledgeBook(recipes: List<NamespacedKey>) : HiltItemStack(Material.KNOWLEDGE_BOOK) {

    val recipes: List<NamespacedKey>
        get() {
            createItemMeta()
            return if (itemMeta is KnowledgeBookMeta) {
                (itemMeta as KnowledgeBookMeta).recipes
            } else ArrayList()
        }

    init {
        setRecipes(recipes)
    }

    fun hasRecipes(): Boolean {
        createItemMeta()
        return if (itemMeta is KnowledgeBookMeta) {
            (itemMeta as KnowledgeBookMeta).hasRecipes()
        } else false
    }

    fun addRecipe(recipe: NamespacedKey): HiltKnowledgeBook {
        createItemMeta()
        if (itemMeta is KnowledgeBookMeta) {
            (itemMeta as KnowledgeBookMeta).addRecipe(recipe)
        }
        return this
    }

    fun setRecipes(recipes: List<NamespacedKey>): HiltKnowledgeBook {
        createItemMeta()
        if (itemMeta is KnowledgeBookMeta) {
            (itemMeta as KnowledgeBookMeta).recipes = recipes
        }
        return this
    }

}