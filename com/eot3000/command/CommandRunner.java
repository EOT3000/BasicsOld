package com.eot3000.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandRunner implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if (sender.hasPermission("basics.heal")) {
                if (command.getName().equalsIgnoreCase("heal")) {
                    return commandHeal(sender, args);
                }
            }
            if (sender.hasPermission("basics.killme")) {
                if (command.getName().equalsIgnoreCase("killme")) {
                    Bukkit.getPlayer(sender.getName()).setHealth(0);
                    return true;
                }
            }
            if (sender.hasPermission("basics.killall")) {
                if (command.getName().equalsIgnoreCase("killall")) {
                    return commandKillAll(sender, args);
                }
            }
        }
        sender.sendMessage("You must be a player to run this command!");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

    private Boolean commandHeal(CommandSender sender, String[] args) {
        if(args.length == 0) {
            Bukkit.getPlayer(sender.getName()).setHealth(20);
            return true;
        }
        if(args.length == 1){
            Integer health;
            try {
                health = Integer.parseInt(args[0]);
            }
            catch(Exception e){
                sender.sendMessage("Not a number!\nUse command like this: /heal or \n/heal <amount> or /heal <amount> <player>");
                return false;
            }
            if (health < 20 && health > 0) {
                Bukkit.getPlayer(sender.getName()).setHealth(health);
                return true;
            }
            return false;
        }
        if(args.length == 2){
            Player player;
            Integer health;
            try {
                player = Bukkit.getPlayer(args[1]);
            }
            catch(Exception e){
                sender.sendMessage("Not an online player!\nUse like this: /heal or \n/heal <amount> or /heal <amount> <player>");
                return false;
            }
            try {
                health = Integer.parseInt(args[0]);
            }
            catch(Exception e){
                sender.sendMessage("Not a number!\nUse like this: /heal or \n/heal <amount> or /heal <amount> <player>");
                return false;
            }
            if (health < 20 && health > player.getHealth()) {
                player.setHealth(health);
                return true;
            }
            sender.sendMessage("Health must be less than 20 and more than 0!");
            return false;
        }
        return false;
    }
    private Boolean commandKillAll(CommandSender sender, String[] args){
        if (args.length == 0){
            for (Player s : Bukkit.getOnlinePlayers()){
                if(!s.getName().equalsIgnoreCase(sender.getName())) {
                    s.setHealth(0);
                }
            }
            return true;
        }
        Boolean b;
        try{
            b = Boolean.getBoolean(args[0]);
        }
        catch(Exception e){
            sender.sendMessage("First argument must be a boolean(true or false)!");
            return false;
        }
        if (!b){
            for (Player s : Bukkit.getOnlinePlayers()){
                s.setHealth(0);
            }
            return true;
        }
        for (Player s : Bukkit.getOnlinePlayers()){
            if(!s.getName().equalsIgnoreCase(sender.getName())) {
                s.setHealth(0);
            }
        }
        return true;
    }
}
