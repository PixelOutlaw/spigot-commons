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

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.meta.BookMeta;

public class HiltBook extends HiltItemStack {

  public HiltBook(TomeType tomeType) {
    super(tomeType.material);
  }

  public String getTitle() {
    createItemMeta();
    if (getItemMeta() instanceof BookMeta && ((BookMeta) getItemMeta()).hasTitle()) {
      return ((BookMeta) getItemMeta()).getTitle();
    }
    return "";
  }

  public HiltBook setTitle(String title) {
    createItemMeta();
    if (getItemMeta() instanceof BookMeta) {
      ((BookMeta) getItemMeta()).setTitle(title);
    }
    return this;
  }

  public List<String> getPages() {
    createItemMeta();
    if (getItemMeta() instanceof BookMeta && ((BookMeta) getItemMeta()).hasPages()) {
      return new ArrayList<>(((BookMeta) getItemMeta()).getPages());
    }
    return new ArrayList<>();
  }

  public HiltBook setPages(List<String> pages) {
    createItemMeta();
    if (getItemMeta() instanceof BookMeta) {
      ((BookMeta) getItemMeta()).setPages(pages);
    }
    return this;
  }

  public enum TomeType {
    BOOK_AND_QUILL(Material.BOOK_AND_QUILL),
    WRITTEN_BOOK(Material.WRITTEN_BOOK);

    private final Material material;

    private TomeType(Material mat) {
      this.material = mat;
    }
  }

}
