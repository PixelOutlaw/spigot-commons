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
import se.ranzdo.bukkit.methodcommand.VerifyError;


public abstract class NumberArgumentHandler<T extends Number> extends ArgumentHandler<T> {
	
	public NumberArgumentHandler() {
		setMessage("min_error", "The parameter [%p] must be equal or greater than %1");
		setMessage("max_error", "The parameter [%p] must be equal or less than %1");
		setMessage("range_error", "The parameter [%p] must be equal or greater than %1 and less than or equal to %2");
		
		addVerifier("min", new ArgumentVerifier<T>() {
			@Override
			public void verify(CommandSender sender, CommandArgument argument, String verifyName, String[] verifyArgs, T value, String valueRaw) throws VerifyError {
				if(verifyArgs.length != 1)
					throw new InvalidVerifyArgument(argument.getName());
				
				try {
					double min = Double.parseDouble(verifyArgs[0]);
					if(value.doubleValue() < min)
						throw new VerifyError(argument.getMessage("min_error", verifyArgs[0]));
				}
				catch (NumberFormatException e) {
					throw new InvalidVerifyArgument(argument.getName());
				}
			}
		});
		
		addVerifier("max", new ArgumentVerifier<T>() {
			@Override
			public void verify(CommandSender sender, CommandArgument argument, String verifyName, String[] verifyArgs, T value, String valueRaw) throws VerifyError {
				if(verifyArgs.length != 1)
					throw new InvalidVerifyArgument(argument.getName());
				
				try {
					double max = Double.parseDouble(verifyArgs[0]);
					if(value.doubleValue() > max)
						throw new VerifyError(argument.getMessage("max_error", verifyArgs[0]));
				}
				catch (NumberFormatException e) {
					throw new InvalidVerifyArgument(argument.getName());
				}
			}	
		});
		
		addVerifier("range", new ArgumentVerifier<T>() {
			@Override
			public void verify(CommandSender sender, CommandArgument argument, String verifyName, String[] verifyArgs, T value, String valueRaw) throws VerifyError {
				if(verifyArgs.length != 2)
					throw new InvalidVerifyArgument(argument.getName());
				
				try {
					double min = Double.parseDouble(verifyArgs[0]);
					double max = Double.parseDouble(verifyArgs[1]);
					double dvalue = value.doubleValue();
					if(dvalue < min || dvalue > max)
						throw new VerifyError(argument.getMessage("range_error", verifyArgs[0], verifyArgs[1]));
				}
				catch (NumberFormatException e) {
					throw new InvalidVerifyArgument(argument.getName());
				}
			}
		});
	}
}
