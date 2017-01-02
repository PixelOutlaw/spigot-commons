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
package se.ranzdo.bukkit.methodcommand.handlers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import se.ranzdo.bukkit.methodcommand.ArgumentHandler;
import se.ranzdo.bukkit.methodcommand.ArgumentVariable;
import se.ranzdo.bukkit.methodcommand.CommandArgument;
import se.ranzdo.bukkit.methodcommand.CommandError;
import se.ranzdo.bukkit.methodcommand.TransformError;


public class PlayerArgumentHandler extends ArgumentHandler<Player> {
	public PlayerArgumentHandler() {
		setMessage("player_not_online", "The player %1 is not online");
		
		addVariable("sender", "The command executor", new ArgumentVariable<Player>() {
			@Override
			public Player var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
				if(!(sender instanceof Player))
					throw new CommandError(argument.getMessage("cant_as_console"));
				
				return ((Player)sender);
			}
		});
	}

	@Override
	public Player transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
		Player p = Bukkit.getPlayer(value);
		if(p == null)
			throw new TransformError(argument.getMessage("player_not_online", value));
		
		return p;
	}
}
