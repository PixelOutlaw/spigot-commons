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

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;


public class CommandArgument implements ExecutableArgument {
	private final String name;
	private final String description;
	private final String def;
	private final Map<String, String[]> verifyArguments;
	private final ArgumentHandler<?> handler;
	private final Class<?> argumentClass;
	
	private Map<String, String> overrideMessages = new HashMap<String, String>();
	
	public CommandArgument(Arg commandArgAnnotation, Class<?> argumentClass, ArgumentHandler<?> argumentHandler) {
		this(commandArgAnnotation.name(), commandArgAnnotation.description(), commandArgAnnotation.def(), commandArgAnnotation.verifiers(), argumentClass, argumentHandler);
	}
	
	public CommandArgument(String name, String description, String def, String verifiers, Class<?> argumentClass, ArgumentHandler<?> handler) {
		this.name = name;
		this.description = description;
		this.def = def;
		this.verifyArguments = CommandUtil.parseVerifiers(verifiers);
		this.handler = handler;
		this.argumentClass = argumentClass;
	}

	@Override
	public Object execute(CommandSender sender, Arguments args) throws CommandError {
		String arg;
		if(!args.hasNext()) {
			if(def.equals(" "))
				throw new CommandError("The argument ["+name+"] is not defined (it has no default value)", true);
			
			arg = def;
		}
		else
			arg = CommandUtil.escapeArgumentVariable(args.nextArgument());
		
		return handler.handle(sender, this, arg);
	}
	
	private String formatMessage(String msg, String[] vars) {
		msg = msg.replace("%p", name);
		
		for(int i = 1; i <= vars.length;i++) {
			msg = msg.replace("%"+i, vars[i-1]);
		}
		
		return msg.replaceAll("%\\d+", "<variable not available>");
	}
	
	public Class<?> getArgumentClass() {
		return argumentClass;
	}

	public String getDefault() {
		return def;
	}
	
	public String getDescription() {
		return description;
	}

	public ArgumentHandler<?> getHandler() {
		return handler;
	}
	
	public String getMessage(String node) {
		return getMessage(node, new String[0]);
	}
	
	public String getMessage(String node, String...vars) {
		String msg = overrideMessages.get(node);
		
		if(msg != null)
			return formatMessage(msg, vars);
		
		msg = handler.getMessage(node);
		
		if(msg != null)
			return formatMessage(msg, vars);
		
		throw new IllegalArgumentException("The node \""+node+"\" is not available.");
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String[]> getVerifyArguments() {
		return verifyArguments;
	}
}
