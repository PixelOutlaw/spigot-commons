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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bukkit.command.CommandSender;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
	/**The description of this command
	 */
	String description() default "";
	
	/**The identifier describes what command definition this will bind to. Spliced by spaces, you can define as many sub commands as you want, as long as the first command (the root) is defined in the plugin.yml file.<br><br>
	 * Example: {@code @Command(identifier="root sub1 sub2")}<br>
	 * The first command "root" needs to be defined in the plugin.yml. The user will be able to access the command by writing (if the root command does not choose an alias instead):<br>
	 * {@code /root sub1 sub2}<br>
	 */
	String identifier();
	
	/**If this command can only be executed by players (default true).<br>
	 * If you turn this to false, the first parameter in the method must be the {@link CommandSender} to avoid {@link ClassCastException}
	 */
	boolean onlyPlayers() default true;
	
	/**The permissions to check if the user have before execution. If it is empty the command does not require any permission.<br><br>
	 * If the user don't have one of the permissions, they will get an error message stating that they do not have permission to use the command.
	 */
	String[] permissions() default {};
}
