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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {	
	private static Pattern verifyArgumentsPattern = Pattern.compile("^(.*?)\\[(.*?)\\]$");
	public static String escapeArgumentVariable(String var) {
		if(var == null)
			return null;
		
		if(var.matches("^\\\\*\\?.*$"))
			return "\\"+var;
		
		return var;
	}
	
	public static Map<String, String[]> parseVerifiers(String verifiers) {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		
		if(verifiers.equals(""))
			return map;
		
		String[] arguments = verifiers.split("\\|");
		
		for(String arg : arguments) {
			Matcher matcher = verifyArgumentsPattern.matcher(arg);
			if(!matcher.matches())
				throw new IllegalArgumentException("The argrument \""+arg+"\" is in invalid form.");
			
			List<String> parameters =  new ArrayList<String>();
			
			String sparameters = matcher.group(2);
			if(sparameters != null) {		
				for(String parameter : sparameters.split(","))
					parameters.add(parameter.trim());
			}
			
			String argName = matcher.group(1).trim();
			
			map.put(argName, parameters.toArray(new String[0]));
		}
		
		return map;
	}
}
