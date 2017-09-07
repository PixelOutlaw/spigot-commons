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

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.meta.BannerMeta;

public class HiltBanner extends HiltItemStack {

  public HiltBanner(Collection<Pattern> patterns, DyeColor color) {
    super(Material.BANNER);
    setPatterns(patterns);
    setColor(color);
  }

  public Collection<Pattern> getPatterns() {
    createItemMeta();
    if (getItemMeta() instanceof BannerMeta) {
      return ((BannerMeta) getItemMeta()).getPatterns();
    }
    return new ArrayList<>();
  }

  public HiltBanner setPatterns(Collection<Pattern> patterns) {
    createItemMeta();
    if (getItemMeta() instanceof BannerMeta) {
      ((BannerMeta) getItemMeta()).setPatterns(new ArrayList<>(patterns));
    }
    return this;
  }

  public DyeColor getColor() {
    createItemMeta();
    if (getItemMeta() instanceof BannerMeta) {
      return ((BannerMeta) getItemMeta()).getBaseColor();
    }
    return null;
  }

  public HiltBanner setColor(DyeColor color) {
    createItemMeta();
    if (getItemMeta() instanceof BannerMeta) {
      ((BannerMeta) getItemMeta()).setBaseColor(color);
    }
    return this;
  }

}
