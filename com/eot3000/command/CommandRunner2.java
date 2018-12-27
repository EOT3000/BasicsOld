package com.eot3000.command;

import org.bukkit.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class CommandRunner2 implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("basics.effects.walkspeed")) {
                if (command.getName().equalsIgnoreCase("walkspeed")) {
                    return commandWalkSpeed(sender, args[0]);
                }
            }
            if (sender.hasPermission("basics.effects.walkspeed.other")) {
                if (command.getName().equalsIgnoreCase("walkspeed")) {
                    return commandWalkSpeed(sender, args[0], args[1]);
                }
            }
        }
        sender.sendMessage("You must be a player to run this command!");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }

    private boolean commandWalkSpeed(CommandSender sender, String speed) {
        try {
            ((Player) sender).setWalkSpeed(Float.parseFloat(speed) / 100f);
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage("Use a number! " + speed + " is not");
        } catch (IllegalArgumentException e) {
            sender.sendMessage("Speed to high or to low! Use 0 through 100");
        }
        return false;
    }
    private boolean commandWalkSpeed(CommandSender sender, String speed, String player){
        try {
            Bukkit.getPlayer(player).setWalkSpeed(Float.parseFloat(speed) / 100f);
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage("Use a number! " + speed + " is not");
        } catch (IllegalArgumentException e) {
            sender.sendMessage("Speed to high or to low! Use 0 through 100");
        } catch (NullPointerException e){
            sender.sendMessage(player + " is not a player!");
        }
        return false;
    }
}
