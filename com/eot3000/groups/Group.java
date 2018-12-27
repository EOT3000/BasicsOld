package com.eot3000.groups;

import com.eot3000.BasicsPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public static final Group DEFAULT = new Group("default");
    private BasicsPlugin main = BasicsPlugin.getInstance();
    private static ArrayList<Group> groups = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private ArrayList<AccountPlayer> players = new ArrayList<>();
    private String name;
    private ArrayList<Permission> permissionsAllowed = new ArrayList<>();
    private ArrayList<Permission> permissionsNotAllowed = new ArrayList<>();

    public ArrayList<AccountPlayer> getPlayers() {
        return new ArrayList<>(players);
    }

    public static Group getGroup(String name){
        for(Group b: groups){
            if(b.name.equalsIgnoreCase(name)){
                return b;
            }
        }
        return null;
    }

    public void addPlayer(OfflinePlayer player){
        players.add(main.getAccount(player));
    }

    public void removePlayer(OfflinePlayer player){
        players.remove(main.getAccount(player));
    }

    public void delete(){
        groups.remove(this);
        names.remove(this.name);
        this.name = null;
        this.players = null;
    }

    public boolean hasPerm(String s){
        return this.permissionsAllowed.contains(new Permission(s)) && !this.permissionsNotAllowed.contains(new Permission(s));
    }

    public void updatePerms(){
        for(Permission p : permissionsAllowed){
            for(AccountPlayer a:this.getPlayers()) {
                a.addPerm(p, true);
                if(a.getPlayer().isOnline()) {
                    a.getPlayer().getPlayer().recalculatePermissions();
                }
            }
        }
        for(Permission p : permissionsNotAllowed){
            for(AccountPlayer a:this.getPlayers()) {
                a.addPerm(p, false);
                if(a.getPlayer().isOnline()) {
                    a.getPlayer().getPlayer().recalculatePermissions();
                }
                else {
                    main.addToPermQuery(a.getPlayer());
                }
            }
        }
    }

    public void addPerm(String s, Boolean value){
        addPerm(new Permission(s), value);
    }

    public void addPerm(Permission p, Boolean value){
        if(value){
            this.permissionsAllowed.add(p);
            this.permissionsNotAllowed.remove(p);
        }
        else {
            this.permissionsNotAllowed.add(p);
            this.permissionsAllowed.remove(p);
        }
        updatePerms();
    }

    public void clearPerms(){
        this.permissionsNotAllowed.clear();
        this.permissionsAllowed.clear();
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Group> getGroups(){
        return new ArrayList<>(groups);
    }

    public static List<String> getGroupNames(){
        return new ArrayList<>(names);
    }

    public Group(String name){
        if(!(getGroup(name.toLowerCase()) == null)){
            throw new ExceptionInInitializerError();
        }
        this.name = name.toLowerCase();
        groups.add(this);
        names.add(name.toLowerCase());
    }
}
