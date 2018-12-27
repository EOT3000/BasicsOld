package com.eot3000.vault.permission;

import com.eot3000.BasicsPlugin;
import com.eot3000.groups.AccountPlayer;
import com.eot3000.groups.Group;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Perms extends Permission {
    private static Perms instance = null;
    private BasicsPlugin main = BasicsPlugin.getInstance();

    @Override
    public String getName() {
        return "Basics";
    }

    @Override
    public boolean isEnabled() {
        return main.isEnabled();
    }

    @Override
    public boolean hasSuperPermsCompat() {
        return false;
    }

    @Override
    public boolean playerHas(String s, String s1, String s2) {
        return playerHas(Bukkit.getPlayer(s1), s2);
    }

    @Override
    public boolean playerAdd(String s, String s1, String s2) {
        return playerAdd(Bukkit.getPlayer(s1), s2);
    }

    @Override
    public boolean playerRemove(String s, String s1, String s2) {
        return playerRemove(Bukkit.getPlayer(s1), s2);
    }

    @Override
    public boolean groupHas(String s, String s1, String s2) {
        return groupHas(s1, s2);
    }

    private boolean groupHas(String s1, String s2) {
        return Group.getGroup(s1).hasPerm(s2);
    }

    @Override
    public boolean groupAdd(String s, String s1, String s2) {
        Group.getGroup(s1).addPerm(s2, true);
        return true;
    }

    @Override
    public boolean groupRemove(String s, String s1, String s2) {
        Group.getGroup(s1).addPerm(s2, false);
        return true;
    }

    @Override
    public boolean playerInGroup(String s, String s1, String s2) {
        return playerInGroup(s, Bukkit.getPlayer(s2), s1);
    }

    @Override
    public boolean playerAddGroup(String s, String s1, String s2) {
        return playerAddGroup(Bukkit.getPlayer(s1), s2);
    }

    @Override
    public boolean playerRemoveGroup(String s, String s1, String s2) {
        return playerRemoveGroup(Bukkit.getPlayer(s1), s2);
    }

    @Override
    public String[] getPlayerGroups(String s, String s1) {
        return getPlayerGroups(s, Bukkit.getOfflinePlayer(s1));
    }

    @Override
    public String getPrimaryGroup(String s, String s1) {
        return "Default";
    }

    @Override
    public String[] getGroups() {
        return Group.getGroupNames().toArray(new String[0]);
    }

    @Override
    public boolean hasGroupSupport() {
        return true;
    }

    @Override
    public boolean has(String world, String player, String permission) {
        return has(Bukkit.getPlayer(player), permission);
    }

    @Override
    public boolean has(World world, String player, String permission) {
        return has(Bukkit.getPlayer(player), permission);
    }

    @Override
    public boolean has(CommandSender sender, String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public boolean has(Player player, String permission) {
        return player.hasPermission(permission);
    }

    @Override
    public boolean playerHas(World world, String player, String permission) {
        return playerHas(Bukkit.getPlayer(player), permission);
    }

    @Override
    public boolean playerHas(String world, OfflinePlayer player, String permission) {
        return playerHas(player.getPlayer(), permission);
    }

    @Override
    public boolean playerHas(Player player, String permission) {
        return player.getPlayer().hasPermission(permission);
    }

    @Override
    public boolean playerAdd(World world, String player, String permission) {
        return playerAdd(Bukkit.getOfflinePlayer(player).getPlayer(), permission);
    }

    @Override
    public boolean playerAdd(String world, OfflinePlayer player, String permission) {
        getAccount(player).addPerm(permission, true);
        return true;
    }

    @Override
    public boolean playerAdd(Player player, String permission) {
        getAccount(player).addPerm(permission, true);
        return true;
    }

    @Override
    public boolean playerAddTransient(OfflinePlayer player, String permission){
        return playerAdd(null, player, permission);
    }

    @Override
    public boolean playerAddTransient(Player player, String permission) {
        return playerAdd(player, permission);
    }

    @Override
    public boolean playerAddTransient(String worldName, OfflinePlayer player, String permission) {
        return this.playerAddTransient(player, permission);
    }

    @Override
    public boolean playerAddTransient(String worldName, Player player, String permission) {
        return this.playerAddTransient(player, permission);
    }

    @Override
    public boolean playerRemoveTransient(String worldName, String player, String permission) {
        return playerRemoveTransient(Bukkit.getPlayer(player), permission);
    }

    @Override
    public boolean playerRemoveTransient(String worldName, OfflinePlayer player, String permission) {
        return this.playerRemoveTransient(player, permission);
    }

    @Override
    public boolean playerRemoveTransient(String worldName, Player player, String permission) {
        return this.playerRemoveTransient(player, permission);
    }

    @Override
    public boolean playerRemove(String world, OfflinePlayer player, String permission) {
        getAccount(player).addPerm(permission, false);
        return true;
    }

    @Override
    public boolean playerRemove(World world, String player, String permission) {
        return playerRemove(world.getName(), Bukkit.getOfflinePlayer(player), permission);
    }

    @Override
    public boolean playerRemove(Player player, String permission) {
        getAccount(player).addPerm(permission, false);
        return true;
    }

    @Override
    public boolean playerRemoveTransient(OfflinePlayer player, String permission) {
        return playerRemove(null, player, permission);
    }

    @Override
    public boolean playerRemoveTransient(Player player, String permission) {
        return playerRemove(null, player, permission);
    }

    @Override
    public boolean groupHas(World world, String group, String permission) {
        return Group.getGroup(group).hasPerm(permission);
    }

    @Override
    public boolean groupAdd(World world, String group, String permission) {
        Group.getGroup(group).addPerm(permission, true);
        return true;
    }

    @Override
    public boolean groupRemove(World world, String group, String permission) {
        Group.getGroup(group).addPerm(permission, false);
        return true;
    }

    @Override
    public boolean playerInGroup(World world, String player, String group) {
        return playerInGroup(null, Bukkit.getOfflinePlayer(player), group);
    }

    @Override
    public boolean playerInGroup(String world, OfflinePlayer player, String group) {
        return Group.getGroup(group).getPlayers().contains(getAccount(player));
    }

    @Override
    public boolean playerInGroup(Player player, String group) {
        return playerInGroup(null, player, group);
    }

    @Override
    public boolean playerAddGroup(World world, String player, String group) {
        return playerAddGroup(null, Bukkit.getOfflinePlayer(player), group);
    }

    @Override
    public boolean playerAddGroup(String world, OfflinePlayer player, String group) {
        Group.getGroup(group).addPlayer(player);
        return true;
    }

    @Override
    public boolean playerAddGroup(Player player, String group) {
        return playerAddGroup(null, player, group);
    }

    @Override
    public boolean playerRemoveGroup(World world, String player, String group) {
        return playerRemoveGroup(null, Bukkit.getOfflinePlayer(player), group);
    }

    @Override
    public boolean playerRemoveGroup(String world, OfflinePlayer player, String group) {
        Group.getGroup(group).removePlayer(player);
        return true;
    }

    @Override
    public boolean playerRemoveGroup(Player player, String group) {
        return playerRemoveGroup(null, player, group);
    }

    @Override
    public String[] getPlayerGroups(World world, String player) {
        return getPlayerGroups(null, Bukkit.getOfflinePlayer(player));
    }

    @Override
    public String[] getPlayerGroups(String world, OfflinePlayer player) {
        return main.getGroups(player, null).toArray(new String[]{});
    }

    @Override
    public String[] getPlayerGroups(Player player) {
        return getPlayerGroups(null, player);
    }

    @Override
    public String getPrimaryGroup(World world, String player) {
        return getPrimaryGroup(null, Bukkit.getOfflinePlayer(player));
    }

    @Override
    public String getPrimaryGroup(String world, OfflinePlayer player) {
        return Group.DEFAULT.toString();
    }

    @Override
    public String getPrimaryGroup(Player player) {
        return getPrimaryGroup(null, player);
    }

    public AccountPlayer getAccount(OfflinePlayer player){
        return main.getAccount(player);
    }

    public static Perms getInstance(){
        if (instance == null) {
            instance = new Perms();
        }
        return instance;
    }
}
