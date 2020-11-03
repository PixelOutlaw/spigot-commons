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
package io.pixeloutlaw.minecraft.spigot.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MasterConfiguration {

  private Map<String, Object> settingMap;

  public MasterConfiguration() {
    settingMap = new ConcurrentHashMap<>();
  }

  public static MasterConfiguration loadFromFiles(SmartConfiguration... configurations) {
    MasterConfiguration masterConfiguration = new MasterConfiguration();
    masterConfiguration.load(configurations);
    return masterConfiguration;
  }

  private static double toDouble(String str, double defaultValue) {
    if (str == null) {
      return defaultValue;
    } else {
      try {
        return Double.parseDouble(str);
      } catch (NumberFormatException numberFormatException) {
        return defaultValue;
      }
    }
  }

  private static int toInt(String str, int defaultValue) {
    if (str == null) {
      return defaultValue;
    } else {
      try {
        return Integer.parseInt(str);
      } catch (NumberFormatException numberFormatException) {
        return defaultValue;
      }
    }
  }

  private static long toLong(String str, long defaultValue) {
    if (str == null) {
      return defaultValue;
    } else {
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException numberFormatException) {
        return defaultValue;
      }
    }
  }

  public void load(SmartConfiguration... configurations) {
    if (configurations == null) {
      return;
    }
    for (SmartConfiguration yc : configurations) {
      String ending = yc.getFileName().substring(yc.getFileName().lastIndexOf("."));
      String name = yc.getFileName().replace(ending, "");
      for (String key : yc.getKeys(true)) {
        if (yc.isConfigurationSection(key)) {
          continue;
        }
        Object value;
        if (yc.isBoolean(key) || yc.isDouble(key) || yc.isInt(key) || yc.isLong(key) || yc
            .isString(key)) {
          value = yc.getString(key);
        } else {
          value = yc.get(key);
        }
        set(name + "." + key, value);
      }
    }
  }

  public void set(String key, Object object) {
    if (settingMap == null) {
      return;
    }
    settingMap.put(key, object);
  }

  public Map<String, Object> getSettingMap() {
    return new HashMap<>(settingMap);
  }

  public boolean isSet(String key) {
    return getSettingMap().containsKey(key);
  }

  public Object get(String key, Object fallback) {
    return settingMap == null || !settingMap.containsKey(key) ? fallback : settingMap.get(key);
  }

  public String getString(String key) {
    return getString(key, "");
  }

  public String getString(String key, String fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (val instanceof String) {
      return (String) val;
    }
    return String.valueOf(val);
  }

  public List<String> getStringList(String key) {
    return getStringList(key, new ArrayList<String>());
  }

  public List<String> getStringList(String key, List<String> fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (!(val instanceof List)) {
      return fallback;
    }
    List<?> valList = (List) val;
    List<String> ret = new ArrayList<>();
    for (Object o : valList) {
      ret.add(String.valueOf(o));
    }
    return ret;
  }

  public boolean getBoolean(String key) {
    return getBoolean(key, false);
  }

  public boolean getBoolean(String key, boolean fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (val instanceof Boolean) {
      return (Boolean) val;
    }
    if (val instanceof String) {
      return Boolean.parseBoolean((String) val);
    }
    return fallback;
  }

  public int getInt(String key) {
    return getInt(key, 0);
  }

  public int getInt(String key, int fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (val instanceof Integer) {
      return (Integer) val;
    }
    if (val instanceof String) {
      return toInt((String) val, fallback);
    }
    return fallback;
  }

  public long getLong(String key) {
    return getLong(key, 0);
  }

  public long getLong(String key, long fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (val instanceof Long) {
      return (Long) val;
    }
    if (val instanceof String) {
      return toLong((String) val, fallback);
    }
    return fallback;
  }

  public double getDouble(String key) {
    return getDouble(key, 0);
  }

  public double getDouble(String key, double fallback) {
    if (settingMap == null || !settingMap.containsKey(key)) {
      return fallback;
    }
    Object val = settingMap.get(key);
    if (val instanceof Double) {
      return (Double) val;
    }
    if (val instanceof String) {
      return toDouble((String) val, fallback);
    }
    return fallback;
  }

}
