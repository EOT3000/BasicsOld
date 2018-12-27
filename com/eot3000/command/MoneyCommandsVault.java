package com.eot3000.command;

import com.eot3000.vault.eco.Eco;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyCommandsVault implements TabExecutor {
    private Eco econ = Eco.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("eco")) {
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("take")) {
                    if (sender.hasPermission("basics.eco.take")) {
                        try {
                            econ.withdrawPlayer(Bukkit.getOfflinePlayer(args[1]), Double.parseDouble(args[2]));
                            sender.sendMessage(ChatColor.YELLOW + "Success!");
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Not a number! Use like this: /eco take <player> <amount>");
                            return false;
                        } catch (NullPointerException e) {
                            sender.sendMessage("Not a player! Use like this: /eco take <player> <amount>");
                            return false;
                        }
                    } else {
                        sender.sendMessage("No permission!");
                        return false;
                    }
                }
                if (args[0].equalsIgnoreCase("give")) {
                    if (sender.hasPermission("basics.eco.give")) {
                        try {
                            econ.depositPlayer(Bukkit.getOfflinePlayer(args[1]), Double.parseDouble(args[2]));
                            sender.sendMessage(ChatColor.YELLOW + "Success!");
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Not a number! Use like this: /eco give <player> <amount>");
                            return false;
                        } catch (NullPointerException e) {
                            sender.sendMessage("Not a player! Use like this: /eco give <player> <amount>");
                            return false;
                        }
                    } else {
                        sender.sendMessage("No permission!");
                        return false;
                    }
                }
            }
            if(sender instanceof Player) {
                if(sender.hasPermission("basics.eco.show")) {
                    if(args.length == 1) {
                        if (args[0].equalsIgnoreCase("show")) {
                            if (econ.getBalance((Player) sender) == 1) {
                                sender.sendMessage(ChatColor.YELLOW + "You have " + Double.toString(econ.getBalance((Player) sender)) + " " + econ.currencyNameSingular());
                                return true;
                            }
                            sender.sendMessage(ChatColor.YELLOW + "You have " + Double.toString(econ.getBalance((Player) sender)) + " " + econ.currencyNamePlural());
                            return true;
                        }
                    }
                    if(args.length >= 2){
                        if (args[0].equalsIgnoreCase("show")) {
                            try {
                                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                                if (econ.getBalance(player) == 1) {
                                    sender.sendMessage(ChatColor.YELLOW + "You have " + Double.toString(econ.getBalance(player)) + " " + econ.currencyNameSingular());
                                    return true;
                                }
                                sender.sendMessage(ChatColor.YELLOW + "You have " + Double.toString(econ.getBalance(player)) + " " + econ.currencyNamePlural());
                                return true;
                            } catch (NullPointerException e){
                                sender.sendMessage(args[1] + " is not a player! Use like this: /eco show <player>");
                                return false;
                            }
                        }
                    }
                }
                else {
                    sender.sendMessage("No permission!");
                    return false;
                }
            }
            else {
                sender.sendMessage("You must be a player to run this command!");
                return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        ArrayList<String> a = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("eco")) {
            if(args.length == 1) {
                if (commandSender.hasPermission("basics.eco.take")) {
                    a.add("show");
                }
                if (commandSender.hasPermission("basics.eco.take")) {
                    a.add("take");
                }
                if (commandSender.hasPermission("basics.eco.give")) {
                    a.add("give");
                }
            }
            if(args.length == 2) {
                for(Player p : Bukkit.getOnlinePlayers()){
                    a.add(p.getName());
                }
            }
            if(args.length == 3) {
                if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take")) {
                    a.add("1");
                    a.add("10");
                    a.add("100");
                    a.add("1000");
                }
            }
        }
        return a;
    }
}
