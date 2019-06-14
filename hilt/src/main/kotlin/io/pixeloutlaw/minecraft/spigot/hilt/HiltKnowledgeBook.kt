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

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.KnowledgeBookMeta

open class HiltKnowledgeBook(recipes: List<NamespacedKey>) : ItemStack(Material.KNOWLEDGE_BOOK) {
    var recipes: List<NamespacedKey>
        get() = getFromItemMetaAs<KnowledgeBookMeta, List<NamespacedKey>> { recipes } ?: listOf()
        set(value) {
            getThenSetItemMetaAs<KnowledgeBookMeta> { recipes = value }
        }

    init {
        this.recipes = recipes
    }

    fun hasRecipes(): Boolean {
        return getFromItemMetaAs<KnowledgeBookMeta, Boolean> { hasRecipes() } ?: false
    }

    fun addRecipe(recipe: NamespacedKey): HiltKnowledgeBook {
        getThenSetItemMetaAs<KnowledgeBookMeta> { addRecipe(recipe) }
        return this
    }
}