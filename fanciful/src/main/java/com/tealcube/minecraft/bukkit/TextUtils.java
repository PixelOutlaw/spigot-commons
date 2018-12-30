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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.ChatColor;

/**
 * A class containing a few useful methods for dealing with text.
 */
public final class TextUtils {

  private static final Map<String, ChatColor> COLOR_MAP = new CaselessMap<>();

  static {
    for (ChatColor cc : ChatColor.values()) {
      String name = cc.name();
      COLOR_MAP.put("<" + name + ">", cc);
      COLOR_MAP.put("<" + name.replace("_", " ") + ">", cc);
      COLOR_MAP.put("<" + name.replace("_", "") + ">", cc);
      COLOR_MAP.put(cc.toString().replace('\u00A7', '&'), cc);
    }
  }

  private TextUtils() {
    // do nothing
  }

  public static ChatColor convertTag(String string) {
    for (Map.Entry<String, ChatColor> entry : COLOR_MAP.entrySet()) {
      if (entry.getKey().equals(string)) {
        return entry.getValue();
      }
    }
    return null;
  }

  public static String convertTag(ChatColor chatColor) {
    for (Map.Entry<String, ChatColor> entry : COLOR_MAP.entrySet()) {
      if (entry.getValue() == chatColor) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static ChatColor findFirstColor(String pString) {
    Validate.notNull(pString, "pString cannot be null");
    char[] c = pString.toCharArray();
    for (int i = 0; i < c.length; i++) {
      if (c[i] == '\u00A7' && i + 1 < c.length) {
        return ChatColor.getByChar(c[i + 1]);
      }
    }
    return null;
  }

  public static String removeFirstColors(String pString) {
    Validate.notNull(pString, "pString cannot be null");
    char[] c = pString.toCharArray();
    int index = 0;
    for (int i = 0; i < c.length; i += 2) {
      if (c[i] == '\u00A7' && i + 1 < c.length) {
        index = i + 2;
      }
    }
    char[] retArray = new char[c.length - index];
    System.arraycopy(c, index, retArray, 0, retArray.length);
    return new String(retArray);
  }

  public static String keepFirstColors(String pString) {
    Validate.notNull(pString, "pString cannot be null");
    char[] c = pString.toCharArray();
    int index = 0;
    for (int i = 0; i < c.length; i += 2) {
      if (c[i] == '\u00A7' && i + 1 < c.length) {
        index = i + 2;
      }
    }
    char[] retArray = new char[index];
    System.arraycopy(c, 0, retArray, 0, index);
    return new String(retArray);
  }

  /**
   * Returns a colored copy of the passed-in {@code List<String>}.
   *
   * @param pList List to color
   * @return colored copy of passed-in List
   */
  public static List<String> color(List<String> pList) {
    Validate.notNull(pList, "pList cannot be null");
    List<String> rList = new ArrayList<>();
    for (String s : pList) {
      rList.add(color(s));
    }
    return rList;
  }

  /**
   * Returns a colored copy of the passed-in String.
   *
   * @param pString String to color
   * @return colored copy of passed-in String
   */
  public static String color(String pString) {
    Validate.notNull(pString, "pString cannot be null");
    String ret = pString;
    for (Map.Entry<String, ChatColor> entry : COLOR_MAP.entrySet()) {
      ret = StringUtils.replace(ret, entry.getKey(), entry.getValue() + "");
    }
    return ret;
  }

  /**
   * Returns a copy of the passed-in List with arguments replaced.
   *
   * @param pList List to replace arguments
   * @param args Arguments to replace in List
   * @return copy of the passed-in List with arguments replaced
   */
  public static List<String> args(List<String> pList, String[][] args) {
    Validate.notNull(pList, "pList cannot be null");
    Validate.notNull(args, "args cannot be null");
    List<String> ret = new ArrayList<>();
    for (String s : pList) {
      ret.add(args(s, args));
    }
    return ret;
  }

  /**
   * Returns a copy of the passed-in String with arguments replaced. The below example will return "I like apples".
   * <pre>
   * <code>String val = TextUtils.args("I like %fruit%", new String[][]{{"%fruit%", "apples"}});</code>
   * </pre>
   *
   * @param pString String to replace arguments
   * @param args Arguments to replace in String
   * @return copy of the passed-in String with arguments replaced
   */
  public static String args(String pString, String[][] args) {
    Validate.notNull(pString, "pString cannot be null");
    Validate.notNull(args, "args cannot be null");
    String ret = pString;
    for (String[] arg : args) {
      if (arg.length < 2) {
        continue;
      }
      ret = StringUtils.replace(ret, arg[0], arg[1]);
    }
    return ret;
  }

  /**
   * Returns the Levenshtein Distance between the two given Strings.
   *
   * @param str1 First String
   * @param str2 Second String
   * @return Levenshtein Distance
   */
  public static int levenshteinDistance(String str1, String str2) {
    Validate.notNull(str1, "str1 cannot be null");
    Validate.notNull(str2, "str2 cannot be null");

    int[][] distance = new int[str1.length() + 1][str2.length() + 1];

    for (int i = 0; i <= str1.length(); i++) {
      distance[i][0] = i;
    }
    for (int j = 1; j <= str2.length(); j++) {
      distance[0][j] = j;
    }

    for (int i = 1; i <= str1.length(); i++) {
      for (int j = 1; j <= str2.length(); j++) {
        distance[i][j] = NumberUtils.min(
            distance[i - 1][j] + 1,
            distance[i][j - 1] + 1,
            distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
      }
    }

    return distance[str1.length()][str2.length()];
  }

}

