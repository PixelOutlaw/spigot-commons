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

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HiltFirework extends HiltItemStack {

    public HiltFirework(Collection<FireworkEffect> fireworkEffects, int power) {
        super(Material.FIREWORK);
        setFireworkEffects(fireworkEffects);
        setPower(power);
    }

    public int getPower() {
        createItemMeta();
        if (getItemMeta() instanceof FireworkMeta) {
            return ((FireworkMeta) getItemMeta()).getPower();
        }
        return 0;
    }

    public HiltFirework setPower(int power) {
        createItemMeta();
        if (getItemMeta() instanceof FireworkMeta) {
            ((FireworkMeta) getItemMeta()).setPower(power);
        }
        return this;
    }

    public List<FireworkEffect> getFireworkEffects() {
        createItemMeta();
        if (getItemMeta() instanceof FireworkMeta && ((FireworkMeta) getItemMeta()).hasEffects()) {
            return new ArrayList<>(((FireworkMeta) getItemMeta()).getEffects());
        }
        return new ArrayList<>();
    }

    public HiltFirework setFireworkEffects(Collection<FireworkEffect> effects) {
        createItemMeta();
        if (getItemMeta() instanceof FireworkMeta) {
            ((FireworkMeta) getItemMeta()).addEffects(effects);
        }
        return this;
    }

}
