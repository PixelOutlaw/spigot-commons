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
package com.tealcube.minecraft.bukkit;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

public final class CaselessMap<V> extends HashMap<String, V> {

  public CaselessMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }

  public CaselessMap(int initialCapacity) {
    super(initialCapacity);
  }

  public CaselessMap() {
  }

  public CaselessMap(Map<? extends String, ? extends V> m) {
    Preconditions.checkNotNull(m);
    for (Entry<? extends String, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public V get(Object key) {
    if (!(key instanceof String)) {
      throw new IllegalArgumentException("key must be an instance of String");
    }
    return super.get(((String) key).toLowerCase());
  }

  @Override
  public boolean containsKey(Object key) {
    if (!(key instanceof String)) {
      throw new IllegalArgumentException("key must be an instance of String");
    }
    return super.containsKey(((String) key).toLowerCase());
  }

  @Override
  public V put(String key, V value) {
    return super.put(key.toLowerCase(), value);
  }

  @Override
  public void putAll(Map<? extends String, ? extends V> m) {
    for (Entry<? extends String, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public V remove(Object key) {
    if (!(key instanceof String)) {
      throw new IllegalArgumentException("key must be an instance of String");
    }
    return super.remove(((String) key).toLowerCase());
  }

}
