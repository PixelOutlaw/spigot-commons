/**
 * The MIT License
 * Copyright (c) 2015 Teal Cube Games
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
package ninja.amp.ampmenus.items;

import java.util.Arrays;
import java.util.List;
import ninja.amp.ampmenus.events.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * An Item inside an {@link ninja.amp.ampmenus.menus.ItemMenu}.
 */
public class MenuItem {

  private final String displayName;
  private final ItemStack icon;
  private final List<String> lore;

  public MenuItem(String displayName, ItemStack icon, String... lore) {
    this.displayName = displayName;
    this.icon = icon;
    this.lore = Arrays.asList(lore);
  }

  /**
   * Sets the display name and lore of an ItemStack.
   *
   * @param itemStack The ItemStack.
   * @param displayName The display name.
   * @param lore The lore.
   * @return The ItemStack.
   */
  public static ItemStack setNameAndLore(ItemStack itemStack, String displayName, List<String> lore) {
    ItemMeta meta = itemStack.getItemMeta();
    meta.setDisplayName(displayName);
    meta.setLore(lore);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  /**
   * Gets the display name of the MenuItem.
   *
   * @return The display name.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Gets the icon of the MenuItem.
   *
   * @return The icon.
   */
  public ItemStack getIcon() {
    return icon;
  }

  /**
   * Gets the lore of the MenuItem.
   *
   * @return The lore.
   */
  public List<String> getLore() {
    return lore;
  }

  /**
   * Gets the ItemStack to be shown to the player.
   *
   * @param player The player.
   * @return The final icon.
   */
  public ItemStack getFinalIcon(Player player) {
    return setNameAndLore(getIcon().clone(), getDisplayName(), getLore());
  }

  /**
   * Called when the MenuItem is clicked.
   *
   * @param event The {@link ItemClickEvent}.
   */
  public void onItemClick(ItemClickEvent event) {
    // Do nothing by default
  }
}
