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
package ninja.amp.ampmenus;

import ninja.amp.ampmenus.menus.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Passes inventory click events to their menus for handling.
 */
public class MenuListener implements Listener {

  private static final MenuListener INSTANCE = new MenuListener();
  private Plugin plugin = null;

  private MenuListener() {
  }

  /**
   * Gets the {@link MenuListener} instance.
   *
   * @return The {@link MenuListener} instance.
   */
  public static MenuListener getInstance() {
    return INSTANCE;
  }

  /**
   * Closes all {@link ninja.amp.ampmenus.menus.ItemMenu}s currently open.
   */
  public static void closeOpenMenus() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getOpenInventory() != null) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        if (inventory.getHolder() instanceof MenuHolder) {
          player.closeInventory();
        }
      }
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getWhoClicked() instanceof Player && event.getInventory().getHolder() instanceof MenuHolder) {
      event.setCancelled(true);
      ((MenuHolder) event.getInventory().getHolder()).getMenu().onInventoryClick(event);
    }
  }

  /**
   * Registers the events of the {@link MenuListener} to a plugin.
   *
   * @param plugin The plugin used to register the events.
   */
  public void register(JavaPlugin plugin) {
    if (!isRegistered(plugin)) {
      plugin.getServer().getPluginManager().registerEvents(INSTANCE, plugin);
      this.plugin = plugin;
    }
  }

  /**
   * Checks if the {@link MenuListener} is registered to a plugin.
   *
   * @param plugin The plugin.
   * @return True if the {@link MenuListener} is registered to the plugin, else false.
   */
  public boolean isRegistered(JavaPlugin plugin) {
    if (plugin.equals(this.plugin)) {
      for (RegisteredListener listener : HandlerList.getRegisteredListeners(plugin)) {
        if (listener.getListener().equals(INSTANCE)) {
          return true;
        }
      }
    }
    return false;
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPluginDisable(PluginDisableEvent event) {
    if (event.getPlugin().equals(plugin)) {
      closeOpenMenus();
      plugin = null;
    }
  }
}
