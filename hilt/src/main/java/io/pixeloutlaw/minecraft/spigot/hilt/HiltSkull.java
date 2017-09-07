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
package io.pixeloutlaw.minecraft.spigot.hilt;

import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

public class HiltSkull extends HiltItemStack {

  public HiltSkull(String owner) {
    super(Material.SKULL_ITEM);
    setOwner(owner);
  }

  public String getOwner() {
    createItemMeta();
    if (getItemMeta() instanceof SkullMeta && ((SkullMeta) getItemMeta()).hasOwner()) {
      return ((SkullMeta) getItemMeta()).getOwner();
    }
    return "";
  }

  public HiltSkull setOwner(String owner) {
    createItemMeta();
    if (getItemMeta() instanceof SkullMeta) {
      ((SkullMeta) getItemMeta()).setOwner(owner);
    }
    return this;
  }

}
