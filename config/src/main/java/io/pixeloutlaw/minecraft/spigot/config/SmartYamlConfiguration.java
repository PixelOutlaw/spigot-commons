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

import java.io.File;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An extension of {@link YamlConfiguration} that can load and save itself.
 */
public class SmartYamlConfiguration extends YamlConfiguration implements SmartConfiguration {

  private File file;

  /**
   * Instantiates a new SmartYamlConfiguration with a selected {@link File} to load/save from/to and
   * automatically loads the file.
   *
   * @param file file to load/save from/to
   */
  public SmartYamlConfiguration(File file) {
    this(file, '.');
  }

  /**
   * Instantiates a new SmartYamlConfiguration with a selected {@link File} to load/save from/to and
   * automatically loads the file.
   *
   * @param file file to load/save from/to
   * @param separator separator char
   */
  public SmartYamlConfiguration(File file, char separator) {
    super();
    this.file = file;
    options().pathSeparator(separator);
    load();
  }


  public SmartYamlConfiguration(Configuration configuration) {
    super();
    this.file = null;
    options().pathSeparator(configuration.options().pathSeparator());
  }

  public SmartYamlConfiguration() {
    super();
    this.file = null;
  }

  /**
   * Loads from the file passed into the constructor.
   *
   * Equivalent of using {@link #load(File)} on a {@link File}.
   */
  @Override
  public void load() {
    try {
      load(this.file);
    } catch (Exception e) {
      // do nothing
    }
  }

  /**
   * Saves to the file passed into the constructor.
   *
   * Equivalent of using {@link #save(File)} on a {@link File}.
   */
  @Override
  public void save() {
    try {
      save(this.file);
    } catch (Exception e) {
      // do nothing
    }
  }

  @Override
  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public String getFileName() {
    return file != null ? file.getName() : "";
  }

}
