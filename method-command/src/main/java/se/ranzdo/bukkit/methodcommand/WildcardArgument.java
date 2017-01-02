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

import java.lang.reflect.Array;

import org.bukkit.command.CommandSender;


public class WildcardArgument extends CommandArgument {
	private boolean join;

	public WildcardArgument(Arg commandArgAnnotation, Class<?> argumentClass, ArgumentHandler<?> argumentHandler, boolean join) {
		super(commandArgAnnotation, argumentClass, argumentHandler);
		this.join = join;
	}
	
	public WildcardArgument(String name, String description, String def, String verifiers, Class<?> argumentClass, ArgumentHandler<?> handler, boolean join) {
		super(name, description, def, verifiers, argumentClass, handler);
		this.join = join;
	}
	
	@Override
	public Object execute(CommandSender sender, Arguments args) throws CommandError {
		if(!args.hasNext()) {
			Object o = getHandler().handle(sender, this, getDefault().equals(" ") ? "" : getDefault());
			if(join)
				return o;
			else {
				Object array = Array.newInstance(getArgumentClass(), 1);
				Array.set(array, 0, o);
				return array;
			}
		}
		
		if(join) {
			StringBuilder sb = new StringBuilder();
			
			while(args.hasNext()) {
				sb.append(args.nextArgument()).append(" ");
			}
			
			return getHandler().handle(sender, this, CommandUtil.escapeArgumentVariable(sb.toString().trim()));
		}
		else {
			Object array = Array.newInstance(getArgumentClass(), args.over());
			
			for(int i = 0; i < args.over();i++)
				Array.set(array, i, getHandler().handle(sender, this, CommandUtil.escapeArgumentVariable(args.nextArgument())));
			
			return array;
		}
	}
	
	public boolean willJoin() {
		return join;
	}
}
