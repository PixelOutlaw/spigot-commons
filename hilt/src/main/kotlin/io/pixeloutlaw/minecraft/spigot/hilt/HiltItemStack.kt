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

import com.google.common.base.Joiner
import org.apache.commons.text.WordUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.util.ArrayList

/**
 * HiltItemStack is a wrapper around the Bukkit ItemStack that enables easy access to ItemMeta.
 */
open class HiltItemStack : ItemStack {

    /**
     * Gets and returns the default name of this ItemStack.
     *
     * This is equivalent to the following:
     * `
     * WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
    ` *
     *
     * @return default name of this HiltItemStack
     */
    val defaultName: String

    /**
     * Gets and returns the name of this HiltItemStack.
     *
     * Grabs the name from the ItemMeta if it has a display name, otherwise calculates
     * the name of the item based on Material.
     *
     * @return name of the item
     */
    val name: String
        get() {
            createItemMeta()
            return if (itemMeta.hasDisplayName()) {
                itemMeta.displayName
            } else defaultName
        }

    /**
     * Gets and returns the lore of this HiltItemStack.
     *
     * Grabs the lore from the ItemMeta if it has lore, otherwise returns an
     * empty `List<String>`.
     *
     * @return lore of this item
     */
    val lore: List<String>
        get() {
            createItemMeta()
            return if (itemMeta.hasLore()) {
                ArrayList(itemMeta.lore)
            } else ArrayList()
        }

    val isUnbreakable: Boolean
        get() {
            createItemMeta()
            return itemMeta.isUnbreakable
        }

    val itemFlags: Set<ItemFlag>
        get() {
            createItemMeta()
            return itemMeta.itemFlags
        }

    private val zero: Short = 0

    /**
     * Constructs a new HiltItemStack from a given Material.
     *
     * @param type Material of new HiltItemStack
     */
    constructor(type: Material) : super(type, 1) {
        defaultName = WordUtils.capitalizeFully(
                Joiner.on(" ").skipNulls().join(type.name.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        )
        createItemMeta()
    }

    /**
     * Constructs a new HiltItemStack from a given Material and amount.
     *
     * @param type Material of new HiltItemStack
     * @param amount amount of items in HiltItemStack
     */
    constructor(type: Material, amount: Int) : super(type, amount) {
        defaultName = WordUtils.capitalizeFully(
                Joiner.on(" ").skipNulls().join(type.name.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        )
        createItemMeta()
    }

    /**
     * Constructs a new HiltItemStack from a given ItemStack.
     *
     * @param stack ItemStack to wrap
     * @throws IllegalArgumentException thrown if argument doesn't match ItemStack specs
     */
    @Throws(IllegalArgumentException::class)
    constructor(stack: ItemStack) : super(stack) {
        defaultName = WordUtils.capitalizeFully(
                Joiner.on(" ").skipNulls().join(type.name.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        )
        createItemMeta()
    }

    protected fun createItemMeta() {
        if (!hasItemMeta()) {
            itemMeta = Bukkit.getItemFactory().getItemMeta(type)
        }
    }

    /**
     * Sets the name of this ItemStack. Use null to remove name.
     *
     * @param name name to give the item
     * @return this HiltItemStack with new name
     */
    fun setName(name: String?): HiltItemStack {
        createItemMeta()
        val itemMeta = itemMeta
        itemMeta.displayName = name?.replace("\\s+", " ")
        setItemMeta(itemMeta)
        return this
    }

    /**
     * Sets the lore of this ItemStack. Use null to remove lore.
     *
     * @param lore lore to give the item
     * @return this HiltItemStack with new lore
     */
    fun setLore(lore: List<String>): HiltItemStack {
        createItemMeta()
        val itemMeta = itemMeta
        itemMeta.lore = lore
        setItemMeta(itemMeta)
        return this
    }

    fun setUnbreakable(b: Boolean): HiltItemStack {
        createItemMeta()
        val itemMeta = itemMeta
        itemMeta.isUnbreakable = b
        setItemMeta(itemMeta)
        return this
    }

    fun setItemFlags(flags: Set<ItemFlag>): HiltItemStack {
        createItemMeta()
        val itemMeta = itemMeta
        itemMeta.removeItemFlags(*ItemFlag.values())
        itemMeta.addItemFlags(*flags.toTypedArray())
        setItemMeta(itemMeta)
        return this
    }

    override fun clone(): HiltItemStack {
        val itemStack = super.clone()
        val hiltItemStack = HiltItemStack(itemStack.type, itemStack.amount)
        hiltItemStack.itemMeta = itemStack.itemMeta.clone()
        return hiltItemStack
    }

}