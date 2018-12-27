package com.eot3000.event;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.permissions.Permission;

public class PlayerPermissionChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Permission permission;
    private final OfflinePlayer player;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    public Permission getPermission(){
        return permission;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public PlayerPermissionChangeEvent(OfflinePlayer player, Permission permission){
        this.permission = permission;
        this.player = player;
    }
}
