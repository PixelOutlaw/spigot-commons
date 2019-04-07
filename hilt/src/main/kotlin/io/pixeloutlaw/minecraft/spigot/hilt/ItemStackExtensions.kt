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

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.reflect.KClass

fun <R> ItemStack.getFromItemMeta(action: ItemMeta.() -> R): R? {
    return this.itemMeta?.run(action)
}

/**
 * Acquires the ItemMeta, runs an action on it, then sets the ItemMeta.
 *
 * @param action Action to perform on the ItemMeta
 */
fun ItemStack.getThenSetItemMeta(action: ItemMeta.() -> Unit) {
    this.itemMeta = this.itemMeta?.apply(action)
}

/**
 * Acquires the ItemMeta, runs an action on it, sets the ItemMeta, and returns the result of the action.
 *
 * @param R Return type of the Action
 * @param action Action to perform on the ItemMeta
 * @return result of the action, null if there is no ItemMeta
 */
fun <R> ItemStack.getThenSetItemMeta(action: ItemMeta.() -> R): R? {
    return this.itemMeta?.let {
        val retValue = action(it)
        this.itemMeta = it
        return retValue
    }
}

/**
 * Checks if the ItemMeta of the current ItemStack is of type `IM`.
 *
 * @param IM Subclass of ItemMeta
 * @return if ItemMeta is of expected type
 */
inline fun <reified IM : ItemMeta> ItemStack.hasItemMetaOf(): Boolean {
    return this.itemMeta is IM
}

inline fun <reified IM: ItemMeta> ItemStack.getItemMetaAs(): IM? {
    return this.itemMeta as? IM
}

inline fun <reified IM: ItemMeta, R> ItemStack.getFromItemMetaAs(action: IM.() -> R): R? {
    return (this.itemMeta as? IM)?.run(action)
}

/**
 * Acquires the ItemMeta of type `IM`, runs an action on it, then sets the ItemMeta.
 *
 * @param IM Subclass of ItemMeta
 * @param action Action to perform on the ItemMeta
 */
inline fun <reified IM : ItemMeta> ItemStack.getThenSetItemMetaAs(action: IM.() -> Unit) {
    (this.itemMeta as? IM)?.let {
        action(it)
        this.itemMeta = it
    }
}

/**
 * Acquires the ItemMeta of type `IM`, runs an action on it, sets the ItemMeta,
 * and returns the result of the action.
 *
 * @param IM Subclass of ItemMeta
 * @param R Return type of action
 * @param action Action to perform on the ItemMeta
 * @return result of the action, null if no ItemMeta found
 */
inline fun <reified IM : ItemMeta, R> ItemStack.getThenSetItemMetaAs(action: IM.() -> R): R? {
    return (this.itemMeta as? IM)?.let {
        val retValue = action(it)
        this.itemMeta = it
        return retValue
    }
}

fun ItemStack.addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier) =
    getThenSetItemMeta<Boolean> { this.addAttributeModifier(attribute, attributeModifier) }

fun ItemStack.addItemFlags(vararg itemFlags: ItemFlag) =
    getThenSetItemMeta { this.addItemFlags(*itemFlags) }

fun ItemStack.getAttributeModifiers(): Multimap<Attribute, AttributeModifier> =
    this.itemMeta?.attributeModifiers ?: ImmutableMultimap.of()

fun ItemStack.getAttributeModifiers(attribute: Attribute): Collection<AttributeModifier> =
    this.itemMeta?.getAttributeModifiers(attribute) ?: mutableListOf()

fun ItemStack.getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> =
    this.itemMeta?.getAttributeModifiers(slot) ?: ImmutableMultimap.of()

fun ItemStack.getDisplayName(): String? = this.itemMeta?.let {
    return if (it.hasDisplayName()) {
        it.displayName
    } else {
        null
    }
}

fun ItemStack.getItemFlags(): Set<ItemFlag> = this.itemMeta?.itemFlags ?: setOf()

fun ItemStack.getLocalizedName(): String? = this.itemMeta?.let {
    return if (it.hasLocalizedName()) {
        it.localizedName
    } else {
        null
    }
}

fun ItemStack.getLore(): List<String> = this.itemMeta?.let {
    return if (it.hasLore()) {
        it.lore?.toList() ?: emptyList()
    } else {
        emptyList()
    }
} ?: listOf()

fun ItemStack.hasAttributeModifiers(): Boolean = this.itemMeta?.hasAttributeModifiers() ?: false

fun ItemStack.hasConflictingEnchantment(ench: Enchantment): Boolean =
    this.itemMeta?.hasConflictingEnchant(ench) ?: false

fun ItemStack.hasDisplayName(): Boolean = this.itemMeta?.hasDisplayName() ?: false

fun ItemStack.hasItemFlag(flag: ItemFlag): Boolean = this.itemMeta?.hasItemFlag(flag) ?: false

fun ItemStack.hasLocalizedName(): Boolean = this.itemMeta?.hasLocalizedName() ?: false

fun ItemStack.hasLore(): Boolean = this.itemMeta?.hasLore() ?: false

fun ItemStack.isUnbreakable(): Boolean = this.itemMeta?.isUnbreakable ?: false

fun ItemStack.removeAttributeModifier(attribute: Attribute) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(attribute) }

fun ItemStack.removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(attribute, modifier) }

fun ItemStack.removeAttributeModifier(slot: EquipmentSlot) =
    getThenSetItemMeta<Boolean> { this.removeAttributeModifier(slot) }

fun ItemStack.removeItemFlags(vararg itemFlags: ItemFlag) =
    getThenSetItemMeta { this.removeItemFlags(*itemFlags) }

fun ItemStack.setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>) =
    getThenSetItemMeta { this.attributeModifiers = attributeModifiers }

fun ItemStack.setDisplayName(string: String?) =
    getThenSetItemMeta { this.setDisplayName(string) }

fun ItemStack.setLocalizedName(string: String?) =
    getThenSetItemMeta { this.setLocalizedName(string) }

fun ItemStack.setLore(list: List<String>) =
    getThenSetItemMeta { this.lore = list }

fun ItemStack.setUnbreakable(unbreakable: Boolean) =
    getThenSetItemMeta { this.isUnbreakable = unbreakable }
