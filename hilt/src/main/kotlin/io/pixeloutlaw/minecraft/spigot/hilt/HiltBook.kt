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

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

open class HiltBook(bookType: BookType) : ItemStack(bookType.material) {
    var author: String?
        get() = getFromItemMetaAs<BookMeta, String?> {
            if (hasAuthor()) {
                author
            } else {
                ""
            }
        }
        set(value) {
            getThenSetItemMetaAs<BookMeta> { author = value }
        }

    var pages: List<String>
        get() = getFromItemMetaAs<BookMeta, List<String>> {
            if (hasPages()) {
                pages.toList()
            } else {
                listOf()
            }
        } ?: listOf()
        set(value) {
            getItemMetaAs<BookMeta>()?.pages = value
        }

    var title: String?
        get() = getFromItemMetaAs<BookMeta, String?> {
            if (hasTitle()) {
                title
            } else {
                ""
            }
        }
        set(value) {
            getItemMetaAs<BookMeta>()?.title = value
        }
}