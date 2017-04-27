/*
 * This file is part of hilt, licensed under the MIT License (MIT)
 *
 * Copyright (c) 2016 Teal Cube Games <http://tealcubegames.com/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package se.ranzdo.bukkit.methodcommand;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.CommandSender;

public class Flag implements ExecutableArgument {

  private final String identifier;
  private final String description;
  private List<FlagArgument> arguments = new ArrayList<FlagArgument>();

  public Flag(String identifier, String description) {
    this.identifier = identifier;
    this.description = description;
  }

  public void addArgument(FlagArgument argument) {
    arguments.add(argument);
  }

  @Override
  public Object execute(CommandSender sender, Arguments args) {
    return args.flagExists(this);
  }

  public List<FlagArgument> getArguments() {
    return arguments;
  }

  public String getDescription() {
    return description;
  }

  public String getIdentifier() {
    return identifier;
  }

  @Override
  public int hashCode() {
    return identifier.hashCode();
  }
}
