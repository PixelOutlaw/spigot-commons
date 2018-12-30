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
package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.Material

enum class SkullType(val material: Material) {
    CREEPER_HEAD(Material.CREEPER_HEAD),
    CREEPER_WALL_HEAD(Material.CREEPER_WALL_HEAD),
    DRAGON_HEAD(Material.DRAGON_HEAD),
    DRAGON_WALL_HEAD(Material.DRAGON_WALL_HEAD),
    PISTON_HEAD(Material.PISTON_HEAD),
    PLAYER_HEAD(Material.PLAYER_HEAD),
    PLAYER_WALL_HEAD(Material.PLAYER_WALL_HEAD),
    SKELETON_SKULL(Material.SKELETON_SKULL),
    SKELETON_WALL_SKULL(Material.SKELETON_WALL_SKULL),
    WITHER_SKELETON_SKULL(Material.WITHER_SKELETON_SKULL),
    WITHER_SKELETON_WALL_SKULL(Material.WITHER_SKELETON_WALL_SKULL),
    ZOMBIE_HEAD(Material.ZOMBIE_HEAD),
    ZOMBIE_WALL_HEAD(Material.ZOMBIE_WALL_HEAD)
}