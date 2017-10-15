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

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A {@link MenuItem} that opens a sub {@link ninja.amp.ampmenus.menus.ItemMenu}.
 */
public class SubMenuItem extends MenuItem {

  private final JavaPlugin plugin;
  private final ItemMenu menu;

  public SubMenuItem(JavaPlugin plugin, String displayName, ItemStack icon, ItemMenu menu, String... lore) {
    super(displayName, icon, lore);
    this.plugin = plugin;
    this.menu = menu;
  }

  @Override
  @SuppressWarnings("deprecation")
  public void onItemClick(ItemClickEvent event) {
    event.setWillClose(true);
    final String playerName = event.getPlayer().getName();
    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
      Player p = Bukkit.getPlayerExact(playerName);
      if (p != null) {
        menu.open(p);
      }
    }, 3);
  }
}
