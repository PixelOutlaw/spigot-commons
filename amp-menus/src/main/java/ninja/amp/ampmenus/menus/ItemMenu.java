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
package ninja.amp.ampmenus.menus;

import ninja.amp.ampmenus.MenuListener;
import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;
import ninja.amp.ampmenus.items.StaticMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A Menu controlled by ItemStacks in an Inventory.
 */
public class ItemMenu {

  /**
   * The {@link StaticMenuItem} that appears in empty slots if {@link ItemMenu#fillEmptySlots()} is called.
   */
  @SuppressWarnings("deprecation")
  private static final MenuItem EMPTY_SLOT_ITEM = new StaticMenuItem(" ",
      new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getWoolData()));
  private JavaPlugin plugin;
  private String name;
  private Size size;
  private MenuItem[] items;
  private ItemMenu parent;

  /**
   * Creates an {@link ItemMenu}.
   *
   * @param name The name of the inventory.
   * @param size The {@link Size} of the inventory.
   * @param plugin The {@link JavaPlugin} instance.
   * @param parent The ItemMenu's parent.
   */
  public ItemMenu(String name, Size size, JavaPlugin plugin, ItemMenu parent) {
    this.plugin = plugin;
    this.name = name;
    this.size = size;
    this.items = new MenuItem[size.getSize()];
    this.parent = parent;
  }

  /**
   * Creates an {@link ItemMenu} with no parent.
   *
   * @param name The name of the inventory.
   * @param size The {@link Size} of the inventory.
   * @param plugin The Plugin instance.
   */
  public ItemMenu(String name, Size size, JavaPlugin plugin) {
    this(name, size, plugin, null);
  }

  /**
   * Gets the name of the {@link ItemMenu}.
   *
   * @return The {@link ItemMenu}'s name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the {@link Size} of the {@link ItemMenu}.
   *
   * @return The {@link ItemMenu}'s {@link Size}.
   */
  public Size getSize() {
    return size;
  }

  /**
   * Checks if the {@link ItemMenu} has a parent.
   *
   * @return True if the {@link ItemMenu} has a parent, else false.
   */
  public boolean hasParent() {
    return parent != null;
  }

  /**
   * Gets the parent of the {@link ItemMenu}.
   *
   * @return The {@link ItemMenu}'s parent.
   */
  public ItemMenu getParent() {
    return parent;
  }

  /**
   * Sets the parent of the {@link ItemMenu}.
   *
   * @param parent The {@link ItemMenu}'s parent.
   */
  public void setParent(ItemMenu parent) {
    this.parent = parent;
  }

  /**
   * Sets the {@link MenuItem} of a slot.
   *
   * @param position The slot position.
   * @param menuItem The {@link MenuItem}.
   * @return The {@link ItemMenu}.
   */
  public ItemMenu setItem(int position, MenuItem menuItem) {
    items[position] = menuItem;
    return this;
  }

  /**
   * Fills all empty slots in the {@link ItemMenu} with a certain {@link MenuItem}.
   *
   * @param menuItem The {@link MenuItem}.
   * @return The {@link ItemMenu}.
   */
  public ItemMenu fillEmptySlots(MenuItem menuItem) {
    for (int i = 0; i < items.length; i++) {
      if (items[i] == null) {
        items[i] = menuItem;
      }
    }
    return this;
  }

  /**
   * Fills all empty slots in the {@link ItemMenu} with the default empty slot item.
   *
   * @return The {@link ItemMenu}.
   */
  public ItemMenu fillEmptySlots() {
    return fillEmptySlots(EMPTY_SLOT_ITEM);
  }

  /**
   * Opens the {@link ItemMenu} for a player.
   *
   * @param player The player.
   */
  public void open(Player player) {
    if (!MenuListener.getInstance().isRegistered(plugin)) {
      MenuListener.getInstance().register(plugin);
    }
    Inventory inventory = Bukkit
        .createInventory(new MenuHolder(this, Bukkit.createInventory(player, size.getSize())), size.getSize(), name);
    apply(inventory, player);
    player.openInventory(inventory);
  }

  /**
   * Updates the {@link ItemMenu} for a player.
   *
   * @param player The player to update the {@link ItemMenu} for.
   */
  @SuppressWarnings("deprecation")
  public void update(Player player) {
    if (player.getOpenInventory() != null) {
      Inventory inventory = player.getOpenInventory().getTopInventory();
      if (inventory.getHolder() instanceof MenuHolder && ((MenuHolder) inventory.getHolder()).getMenu().equals(this)) {
        apply(inventory, player);
        player.updateInventory();
      }
    }
  }

  /**
   * Applies the {@link ItemMenu} for a player to an Inventory.
   *
   * @param inventory The Inventory.
   * @param player The Player.
   */
  private void apply(Inventory inventory, Player player) {
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        inventory.setItem(i, items[i].getFinalIcon(player));
      }
    }
  }

  /**
   * Handles InventoryClickEvents for the {@link ItemMenu}.
   */
  @SuppressWarnings("deprecation")
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getClick() == ClickType.LEFT) {
      int slot = event.getRawSlot();
      if (slot >= 0 && slot < size.getSize() && items[slot] != null) {
        Player player = (Player) event.getWhoClicked();
        ItemClickEvent itemClickEvent = new ItemClickEvent(player, event.isShiftClick());
        items[slot].onItemClick(itemClickEvent);
        if (itemClickEvent.willUpdate()) {
          update(player);
        } else {
          player.updateInventory();
          if (itemClickEvent.willClose() || itemClickEvent.willGoBack()) {
            final String playerName = player.getName();
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
              Player p = Bukkit.getPlayerExact(playerName);
              if (p != null) {
                p.closeInventory();
              }
            }, 1);
          }
          if (itemClickEvent.willGoBack() && hasParent()) {
            final String playerName = player.getName();
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
              Player p = Bukkit.getPlayerExact(playerName);
              if (p != null) {
                parent.open(p);
              }
            }, 3);
          }
        }
      }
    }
  }

  /**
   * Destroys the {@link ItemMenu}.
   */
  public void destroy() {
    plugin = null;
    name = null;
    size = null;
    items = null;
    parent = null;
  }

  /**
   * Possible sizes of an {@link ItemMenu}.
   */
  public enum Size {
    ONE_LINE(9),
    TWO_LINE(18),
    THREE_LINE(27),
    FOUR_LINE(36),
    FIVE_LINE(45),
    SIX_LINE(54);

    private final int size;

    Size(int size) {
      this.size = size;
    }

    /**
     * Gets the required {@link Size} for an amount of slots.
     *
     * @param slots The amount of slots.
     * @return The required {@link Size}.
     */
    public static Size fit(int slots) {
      if (slots < 10) {
        return ONE_LINE;
      } else if (slots < 19) {
        return TWO_LINE;
      } else if (slots < 28) {
        return THREE_LINE;
      } else if (slots < 37) {
        return FOUR_LINE;
      } else if (slots < 46) {
        return FIVE_LINE;
      } else {
        return SIX_LINE;
      }
    }

    /**
     * Gets the {@link Size}'s amount of slots.
     *
     * @return The amount of slots.
     */
    public int getSize() {
      return size;
    }
  }
}
