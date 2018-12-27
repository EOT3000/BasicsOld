package com.eot3000.vault.chat;

import com.eot3000.BasicsPlugin;
import com.eot3000.vault.permission.Perms;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ChatFormatter extends Chat {
    private static ChatFormatter instance = null;
    private BasicsPlugin main = BasicsPlugin.getInstance();
    private Permission perms = Perms.getInstance();

    private ChatFormatter(){
        super(Perms.getInstance());
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPlayerPrefix(String s, String s1) {
        return null;
    }

    @Override
    public void setPlayerPrefix(String s, String s1, String s2) {

    }

    @Override
    public String getPlayerSuffix(String s, String s1) {
        return null;
    }

    @Override
    public void setPlayerSuffix(String s, String s1, String s2) {

    }

    @Override
    public String getGroupPrefix(String s, String s1) {
        return null;
    }

    @Override
    public void setGroupPrefix(String s, String s1, String s2) {

    }

    @Override
    public String getGroupSuffix(String s, String s1) {
        return null;
    }

    @Override
    public void setGroupSuffix(String s, String s1, String s2) {

    }

    @Override
    public int getPlayerInfoInteger(String s, String s1, String s2, int i) {
        return 0;
    }

    @Override
    public void setPlayerInfoInteger(String s, String s1, String s2, int i) {

    }

    @Override
    public int getGroupInfoInteger(String s, String s1, String s2, int i) {
        return 0;
    }

    @Override
    public void setGroupInfoInteger(String s, String s1, String s2, int i) {

    }

    @Override
    public double getPlayerInfoDouble(String s, String s1, String s2, double v) {
        return 0;
    }

    @Override
    public void setPlayerInfoDouble(String s, String s1, String s2, double v) {

    }

    @Override
    public double getGroupInfoDouble(String s, String s1, String s2, double v) {
        return 0;
    }

    @Override
    public void setGroupInfoDouble(String s, String s1, String s2, double v) {

    }

    @Override
    public boolean getPlayerInfoBoolean(String s, String s1, String s2, boolean b) {
        return false;
    }

    @Override
    public void setPlayerInfoBoolean(String s, String s1, String s2, boolean b) {

    }

    @Override
    public boolean getGroupInfoBoolean(String s, String s1, String s2, boolean b) {
        return false;
    }

    @Override
    public void setGroupInfoBoolean(String s, String s1, String s2, boolean b) {

    }

    @Override
    public String getPlayerInfoString(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public void setPlayerInfoString(String s, String s1, String s2, String s3) {

    }

    @Override
    public String getGroupInfoString(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public void setGroupInfoString(String s, String s1, String s2, String s3) {

    }

    public String getPlayerPrefix(String world, OfflinePlayer player) {
        return this.getPlayerPrefix(world, player.getName());
    }

    public String getPlayerPrefix(World world, String player) {
        return this.getPlayerPrefix(world.getName(), player);
    }

    public String getPlayerPrefix(Player player) {
        return this.getPlayerPrefix((String)player.getWorld().getName(), (OfflinePlayer)player);
    }

    public void setPlayerPrefix(String world, OfflinePlayer player, String prefix) {
        this.setPlayerPrefix(world, player.getName(), prefix);
    }

    public void setPlayerPrefix(World world, String player, String prefix) {
        this.setPlayerPrefix(world.getName(), player, prefix);
    }

    public void setPlayerPrefix(Player player, String prefix) {
        this.setPlayerPrefix((String)player.getWorld().getName(), (OfflinePlayer)player, prefix);
    }

    public String getPlayerSuffix(String world, OfflinePlayer player) {
        return this.getPlayerSuffix(world, player.getName());
    }

    public String getPlayerSuffix(World world, String player) {
        return this.getPlayerSuffix(world.getName(), player);
    }

    public String getPlayerSuffix(Player player) {
        return this.getPlayerSuffix((String)player.getWorld().getName(), (OfflinePlayer)player);
    }

    public void setPlayerSuffix(String world, OfflinePlayer player, String suffix) {
        this.setPlayerSuffix(world, player.getName(), suffix);
    }

    public void setPlayerSuffix(World world, String player, String suffix) {
        this.setPlayerSuffix(world.getName(), player, suffix);
    }

    public void setPlayerSuffix(Player player, String suffix) {
        this.setPlayerSuffix((String)player.getWorld().getName(), (OfflinePlayer)player, suffix);
    }

    public String getGroupPrefix(World world, String group) {
        return this.getGroupPrefix(world.getName(), group);
    }

    public void setGroupPrefix(World world, String group, String prefix) {
        this.setGroupPrefix(world.getName(), group, prefix);
    }

    public String getGroupSuffix(World world, String group) {
        return this.getGroupSuffix(world.getName(), group);
    }

    public void setGroupSuffix(World world, String group, String suffix) {
        this.setGroupSuffix(world.getName(), group, suffix);
    }

    public int getPlayerInfoInteger(String world, OfflinePlayer player, String node, int defaultValue) {
        return this.getPlayerInfoInteger(world, player.getName(), node, defaultValue);
    }

    public int getPlayerInfoInteger(World world, String player, String node, int defaultValue) {
        return this.getPlayerInfoInteger(world.getName(), player, node, defaultValue);
    }

    public int getPlayerInfoInteger(Player player, String node, int defaultValue) {
        return this.getPlayerInfoInteger((String)player.getWorld().getName(), (OfflinePlayer)player, node, defaultValue);
    }

    public void setPlayerInfoInteger(String world, OfflinePlayer player, String node, int value) {
        this.setPlayerInfoInteger(world, player.getName(), node, value);
    }

    public void setPlayerInfoInteger(World world, String player, String node, int value) {
        this.setPlayerInfoInteger(world.getName(), player, node, value);
    }

    public void setPlayerInfoInteger(Player player, String node, int value) {
        this.setPlayerInfoInteger((String)player.getWorld().getName(), (OfflinePlayer)player, node, value);
    }

    public int getGroupInfoInteger(World world, String group, String node, int defaultValue) {
        return this.getGroupInfoInteger(world.getName(), group, node, defaultValue);
    }

    public void setGroupInfoInteger(World world, String group, String node, int value) {
        this.setGroupInfoInteger(world.getName(), group, node, value);
    }

    public double getPlayerInfoDouble(String world, OfflinePlayer player, String node, double defaultValue) {
        return this.getPlayerInfoDouble(world, player.getName(), node, defaultValue);
    }

    public double getPlayerInfoDouble(World world, String player, String node, double defaultValue) {
        return this.getPlayerInfoDouble(world.getName(), player, node, defaultValue);
    }

    public double getPlayerInfoDouble(Player player, String node, double defaultValue) {
        return this.getPlayerInfoDouble((String)player.getWorld().getName(), (OfflinePlayer)player, node, defaultValue);
    }

    public void setPlayerInfoDouble(String world, OfflinePlayer player, String node, double value) {
        this.setPlayerInfoDouble(world, player.getName(), node, value);
    }

    public void setPlayerInfoDouble(World world, String player, String node, double value) {
        this.setPlayerInfoDouble(world.getName(), player, node, value);
    }

    public void setPlayerInfoDouble(Player player, String node, double value) {
        this.setPlayerInfoDouble((String)player.getWorld().getName(), (OfflinePlayer)player, node, value);
    }

    public double getGroupInfoDouble(World world, String group, String node, double defaultValue) {
        return this.getGroupInfoDouble(world.getName(), group, node, defaultValue);
    }

    public void setGroupInfoDouble(World world, String group, String node, double value) {
        this.setGroupInfoDouble(world.getName(), group, node, value);
    }

    public boolean getPlayerInfoBoolean(String world, OfflinePlayer player, String node, boolean defaultValue) {
        return this.getPlayerInfoBoolean(world, player.getName(), node, defaultValue);
    }

    public boolean getPlayerInfoBoolean(World world, String player, String node, boolean defaultValue) {
        return this.getPlayerInfoBoolean(world.getName(), player, node, defaultValue);
    }

    public boolean getPlayerInfoBoolean(Player player, String node, boolean defaultValue) {
        return this.getPlayerInfoBoolean((String)player.getWorld().getName(), (OfflinePlayer)player, node, defaultValue);
    }

    public void setPlayerInfoBoolean(String world, OfflinePlayer player, String node, boolean value) {
        this.setPlayerInfoBoolean(world, player.getName(), node, value);
    }

    public void setPlayerInfoBoolean(World world, String player, String node, boolean value) {
        this.setPlayerInfoBoolean(world.getName(), player, node, value);
    }

    public void setPlayerInfoBoolean(Player player, String node, boolean value) {
        this.setPlayerInfoBoolean((String)player.getWorld().getName(), (OfflinePlayer)player, node, value);
    }

    public boolean getGroupInfoBoolean(World world, String group, String node, boolean defaultValue) {
        return this.getGroupInfoBoolean(world.getName(), group, node, defaultValue);
    }

    public void setGroupInfoBoolean(World world, String group, String node, boolean value) {
        this.setGroupInfoBoolean(world.getName(), group, node, value);
    }

    public String getPlayerInfoString(String world, OfflinePlayer player, String node, String defaultValue) {
        return this.getPlayerInfoString(world, player.getName(), node, defaultValue);
    }

    public String getPlayerInfoString(World world, String player, String node, String defaultValue) {
        return this.getPlayerInfoString(world.getName(), player, node, defaultValue);
    }

    public String getPlayerInfoString(Player player, String node, String defaultValue) {
        return this.getPlayerInfoString((String)player.getWorld().getName(), (OfflinePlayer)player, node, defaultValue);
    }

    public void setPlayerInfoString(String world, OfflinePlayer player, String node, String value) {
        this.setPlayerInfoString(world, player.getName(), node, value);
    }

    public void setPlayerInfoString(World world, String player, String node, String value) {
        this.setPlayerInfoString(world.getName(), player, node, value);
    }

    public void setPlayerInfoString(Player player, String node, String value) {
        this.setPlayerInfoString((String)player.getWorld().getName(), (OfflinePlayer)player, node, value);
    }

    public String getGroupInfoString(World world, String group, String node, String defaultValue) {
        return this.getGroupInfoString(world.getName(), group, node, defaultValue);
    }

    public void setGroupInfoString(World world, String group, String node, String value) {
        this.setGroupInfoString(world.getName(), group, node, value);
    }

    public boolean playerInGroup(String world, OfflinePlayer player, String group) {
        return this.perms.playerInGroup(world, player, group);
    }

    public boolean playerInGroup(String world, String player, String group) {
        return this.perms.playerInGroup(world, player, group);
    }

    public boolean playerInGroup(World world, String player, String group) {
        return this.playerInGroup(world.getName(), player, group);
    }

    public boolean playerInGroup(Player player, String group) {
        return this.playerInGroup((String)player.getWorld().getName(), (OfflinePlayer)player, group);
    }

    public String[] getPlayerGroups(String world, OfflinePlayer player) {
        return this.perms.getPlayerGroups(world, player);
    }

    public String[] getPlayerGroups(String world, String player) {
        return this.perms.getPlayerGroups(world, player);
    }

    public String[] getPlayerGroups(World world, String player) {
        return this.getPlayerGroups(world.getName(), player);
    }

    public String[] getPlayerGroups(Player player) {
        return this.getPlayerGroups((String)player.getWorld().getName(), (OfflinePlayer)player);
    }

    public String getPrimaryGroup(String world, OfflinePlayer player) {
        return this.perms.getPrimaryGroup(world, player);
    }

    public String getPrimaryGroup(String world, String player) {
        return this.perms.getPrimaryGroup(world, player);
    }

    public String getPrimaryGroup(World world, String player) {
        return this.getPrimaryGroup(world.getName(), player);
    }

    public String getPrimaryGroup(Player player) {
        return this.getPrimaryGroup((String)player.getWorld().getName(), (OfflinePlayer)player);
    }

    public String[] getGroups() {
        return this.perms.getGroups();
    }

    public static ChatFormatter getInstance(){
        if (instance == null) {
            instance = new ChatFormatter();
        }
        return instance;
    }
}
