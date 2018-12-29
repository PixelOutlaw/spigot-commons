package se.ranzdo.bukkit.methodcommand.handlers;

import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import se.ranzdo.bukkit.methodcommand.ArgumentHandler;
import se.ranzdo.bukkit.methodcommand.CommandArgument;
import se.ranzdo.bukkit.methodcommand.TransformError;

public class EnchantmentArgumentHandler extends ArgumentHandler<Enchantment> {

  public EnchantmentArgumentHandler() {
    setMessage("parse_error", "There is no Enchantment named %1");
    setMessage("include_error", "There is no Enchantment named %1");
    setMessage("exclude_error", "There is no Enchantment named %1");
  }

  @Override
  public Enchantment transform(CommandSender commandSender, CommandArgument commandArgument, String s)
      throws TransformError {
    Enchantment e = Enchantment.getByKey(NamespacedKey.minecraft(s));
    if (e == null) {
      throw new TransformError(commandArgument.getMessage("parse_error", s));
    }
    return e;
  }

}

