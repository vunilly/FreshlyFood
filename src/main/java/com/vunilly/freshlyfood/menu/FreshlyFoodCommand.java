package com.vunilly.freshlyfood.menu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.vunilly.freshlyfood.utils.Lang;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class FreshlyFoodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Lang.get("msg.cmd.onlyPlayersAllowed").getFirst());
            return true;
        }

        if (args.length > 0) {
            sender.sendMessage(Lang.get("msg.cmd.tooManyParamsOkay", Placeholder.parsed("command", command.getName())).getFirst());
        }

        FreshlyFoodMenu menu = new FreshlyFoodMenu(player, 0);
        player.openInventory(menu.getInventory());

        return true;
    }

}
