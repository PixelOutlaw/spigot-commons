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

import org.bukkit.command.CommandSender;

import se.ranzdo.bukkit.methodcommand.ArgumentHandler;
import se.ranzdo.bukkit.methodcommand.ArgumentVerifier;
import se.ranzdo.bukkit.methodcommand.CommandArgument;
import se.ranzdo.bukkit.methodcommand.InvalidVerifyArgument;
import se.ranzdo.bukkit.methodcommand.TransformError;
import se.ranzdo.bukkit.methodcommand.VerifyError;


public class StringArgumentHandler extends ArgumentHandler<String> {
	public StringArgumentHandler() {
		setMessage("min_error", "The parameter [%p] must be more than %1 characters.");
		setMessage("max_error", "The parameter [%p] can't be more than %1 characters.");
		
		addVerifier("min", new ArgumentVerifier<String>() {
			@Override
			public void verify(CommandSender sender, CommandArgument argument, String verifyName, String[] verifyArgs, String value, String valueRaw) throws VerifyError {
				if(verifyArgs.length != 1)
					throw new InvalidVerifyArgument(argument.getName());
				
				try {
					int min = Integer.parseInt(verifyArgs[0]);
					if(value.length() < min)
						throw new VerifyError(argument.getMessage("min_error", verifyArgs[0]));
				}
				catch (NumberFormatException e) {
					throw new InvalidVerifyArgument(argument.getName());
				}
			}
		});
		
		
		addVerifier("max", new ArgumentVerifier<String>() {
			@Override
			public void verify(CommandSender sender, CommandArgument argument, String verifyName, String[] verifyArgs, String value, String valueRaw) throws VerifyError {
				if(verifyArgs.length != 1)
					throw new InvalidVerifyArgument(argument.getName());
				
				try {
					int max = Integer.parseInt(verifyArgs[0]);
					if(value.length() > max)
						throw new VerifyError(argument.getMessage("max_error", verifyArgs[0]));
				}
				catch (NumberFormatException e) {
					throw new InvalidVerifyArgument(argument.getName());
				}
			}
		});
	}

	@Override
	public String transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
		return value;
	}
}
