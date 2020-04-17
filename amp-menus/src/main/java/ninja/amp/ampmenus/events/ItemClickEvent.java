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
package ninja.amp.ampmenus.events;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * An event called when an Item in the {@link ninja.amp.ampmenus.menus.ItemMenu} is clicked.
 */
public class ItemClickEvent {

  private Player player;
  private boolean goBack = false;
  private boolean close = false;
  private boolean update = false;
  private final ClickType clickType;

  public ItemClickEvent(Player player) {
    this.player = player;
    this.clickType = ClickType.UNKNOWN;
  }

  public ItemClickEvent(Player player, ClickType clickType) {
    this.player = player;
    this.clickType = clickType;
  }

  /**
   * Gets the player who clicked.
   *
   * @return The player who clicked.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Checks if the {@link ninja.amp.ampmenus.menus.ItemMenu} will go back to the parent menu.
   *
   * @return True if the {@link ninja.amp.ampmenus.menus.ItemMenu} will go back to the parent menu, else false.
   */
  public boolean willGoBack() {
    return goBack;
  }

  /**
   * Sets if the {@link ninja.amp.ampmenus.menus.ItemMenu} will go back to the parent menu.
   *
   * @param goBack If the {@link ninja.amp.ampmenus.menus.ItemMenu} will go back to the parent menu.
   */
  public void setWillGoBack(boolean goBack) {
    this.goBack = goBack;
    if (goBack) {
      close = false;
      update = false;
    }
  }

  /**
   * Checks if the {@link ninja.amp.ampmenus.menus.ItemMenu} will close.
   *
   * @return True if the {@link ninja.amp.ampmenus.menus.ItemMenu} will close, else false.
   */
  public boolean willClose() {
    return close;
  }

  /**
   * Sets if the {@link ninja.amp.ampmenus.menus.ItemMenu} will close.
   *
   * @param close If the {@link ninja.amp.ampmenus.menus.ItemMenu} will close.
   */
  public void setWillClose(boolean close) {
    this.close = close;
    if (close) {
      goBack = false;
      update = false;
    }
  }

  /**
   * Checks if the {@link ninja.amp.ampmenus.menus.ItemMenu} will update.
   *
   * @return True if the {@link ninja.amp.ampmenus.menus.ItemMenu} will update, else false.
   */
  public boolean willUpdate() {
    return update;
  }

  /**
   * Sets if the {@link ninja.amp.ampmenus.menus.ItemMenu} will update.
   *
   * @param update If the {@link ninja.amp.ampmenus.menus.ItemMenu} will update.
   */
  public void setWillUpdate(boolean update) {
    this.update = update;
    if (update) {
      goBack = false;
      close = false;
    }
  }

  public ClickType getClickType() {
    return clickType;
  }

}
