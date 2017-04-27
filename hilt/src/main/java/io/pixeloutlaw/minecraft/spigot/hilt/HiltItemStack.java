/**
 * The MIT License
 * Copyright (c) 2015 Pixel Outlaw
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
package io.pixeloutlaw.minecraft.spigot.hilt;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * HiltItemStack is a wrapper around the Bukkit ItemStack that enables easy access to ItemMeta.
 */
public class HiltItemStack extends ItemStack {

  private final String defaultName;

  /**
   * Constructs a new HiltItemStack from a given Material.
   *
   * @param type Material of new HiltItemStack
   */
  public HiltItemStack(Material type) {
    super(type);
    defaultName = WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
    createItemMeta();
  }

  /**
   * Constructs a new HiltItemStack from a given Material and amount.
   *
   * @param type Material of new HiltItemStack
   * @param amount amount of items in HiltItemStack
   */
  public HiltItemStack(Material type, int amount) {
    super(type, amount);
    defaultName = WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
    createItemMeta();
  }

  /**
   * Constructs a new HiltItemStack from a given Material, amount, and durability.
   *
   * @param type Material of new HiltItemStack
   * @param amount amount of items in HiltItemStack
   * @param damage damage done to item (0 is no damage)
   */
  public HiltItemStack(Material type, int amount, short damage) {
    super(type, amount, damage);
    defaultName = WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
    createItemMeta();
  }

  /**
   * Constructs a new HiltItemStack from a given ItemStack.
   *
   * @param stack ItemStack to wrap
   * @throws IllegalArgumentException thrown if argument doesn't match ItemStack specs
   */
  public HiltItemStack(ItemStack stack) throws IllegalArgumentException {
    super(stack);
    defaultName = WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
    createItemMeta();
  }

  protected void createItemMeta() {
    if (!hasItemMeta()) {
      setItemMeta(Bukkit.getItemFactory().getItemMeta(getType()));
    }
  }

  /**
   * Gets and returns the name of this HiltItemStack.
   *
   * Grabs the name from the ItemMeta if it has a display name, otherwise calculates
   * the name of the item based on Material.
   *
   * @return name of the item
   */
  public String getName() {
    createItemMeta();
    if (getItemMeta().hasDisplayName()) {
      return getItemMeta().getDisplayName();
    }
    return getDefaultName();
  }

  /**
   * Sets the name of this ItemStack. Use null to remove name.
   *
   * @param name name to give the item
   * @return this HiltItemStack with new name
   */
  public HiltItemStack setName(String name) {
    createItemMeta();
    ItemMeta itemMeta = getItemMeta();
    itemMeta.setDisplayName(name != null ? name.replace("\\s+", " ") : null);
    setItemMeta(itemMeta);
    return this;
  }

  /**
   * Gets and returns the lore of this HiltItemStack.
   *
   * Grabs the lore from the ItemMeta if it has lore, otherwise returns an
   * empty {@code List<String>}.
   *
   * @return lore of this item
   */
  public List<String> getLore() {
    createItemMeta();
    if (getItemMeta().hasLore()) {
      return new ArrayList<>(getItemMeta().getLore());
    }
    return new ArrayList<>();
  }

  /**
   * Sets the lore of this ItemStack. Use null to remove lore.
   *
   * @param lore lore to give the item
   * @return this HiltItemStack with new lore
   */
  public HiltItemStack setLore(List<String> lore) {
    createItemMeta();
    ItemMeta itemMeta = getItemMeta();
    itemMeta.setLore(lore);
    setItemMeta(itemMeta);
    return this;
  }

  /**
   * Gets and returns the default name of this ItemStack.
   *
   * This is equivalent to the following:
   * <code>
   * WordUtils.capitalizeFully(Joiner.on(" ").skipNulls().join(getType().name().split("_")));
   * </code>
   *
   * @return default name of this HiltItemStack
   */
  public String getDefaultName() {
    return defaultName;
  }

  public boolean isUnbreakable() {
    createItemMeta();
    return getItemMeta().spigot().isUnbreakable();
  }

  public HiltItemStack setUnbreakable(boolean b) {
    createItemMeta();
    ItemMeta itemMeta = getItemMeta();
    ItemMeta.Spigot spigotMeta = itemMeta.spigot();
    spigotMeta.setUnbreakable(b);
    setItemMeta(itemMeta);
    return this;
  }

  public Set<ItemFlag> getItemFlags() {
    createItemMeta();
    return getItemMeta().getItemFlags();
  }

  public HiltItemStack setItemFlags(Set<ItemFlag> flags) {
    createItemMeta();
    ItemMeta itemMeta = getItemMeta();
    itemMeta.removeItemFlags(ItemFlag.values());
    itemMeta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));
    setItemMeta(itemMeta);
    return this;
  }

  @Override
  public HiltItemStack clone() {
    ItemStack itemStack = super.clone();
    HiltItemStack hiltItemStack = new HiltItemStack(
        itemStack.getType(), itemStack.getAmount(), itemStack.getDurability());
    hiltItemStack.setItemMeta(itemStack.getItemMeta().clone());
    return hiltItemStack;
  }
}
