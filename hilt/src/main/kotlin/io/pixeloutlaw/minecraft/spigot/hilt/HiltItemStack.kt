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
     * Constructs a new HiltItemStack from a given Material, amount, and durability.
     *
     * @param type Material of new HiltItemStack
     * @param amount amount of items in HiltItemStack
     * @param damage damage done to item (0 is no damage)
     */
    constructor(type: Material, amount: Int, damage: Short) : super(type, amount, damage) {
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
        val hiltItemStack = HiltItemStack(
                itemStack.type, itemStack.amount, itemStack.durability)
        hiltItemStack.itemMeta = itemStack.itemMeta.clone()
        return hiltItemStack
    }

}