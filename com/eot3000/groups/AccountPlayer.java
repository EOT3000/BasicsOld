package com.eot3000.groups;

import com.eot3000.BasicsPlugin;

import com.eot3000.event.PlayerPermissionChangeEvent;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import static com.eot3000.BasicsPlugin.loadFromFile;

public class AccountPlayer extends Account implements HasPermissions, CanHaveMeta {
    private static final ArrayList<AccountPlayer> accounts = new ArrayList<>();
    private static final ArrayList<OfflinePlayer> playersWithAccounts = new ArrayList<>();
    private transient OfflinePlayer p;
    private final UUID playerID;
    private final ArrayList<String> permissionsAllowed = new ArrayList<>();
    private final ArrayList<String> permissionsNotAllowed = new ArrayList<>();

    public AccountPlayer(OfflinePlayer p){
        super(p);
        Validate.notNull(p);
        this.p = p;
        amount = 0D;
        playerID = p.getUniqueId();
        accounts.add(this);
        playersWithAccounts.add(p);
    }

    public OfflinePlayer getPlayer() {
        return p;
    }

    public void updatePerms(Player player){
        for(String s : permissionsAllowed){
            player.addAttachment(BasicsPlugin.getInstance()).setPermission(s, true);
        }
        for(String s : permissionsNotAllowed){
            player.addAttachment(BasicsPlugin.getInstance()).setPermission(s, false);
        }
        player.recalculatePermissions();
    }

    public void addPerm(String s, Boolean value){
        if(value){
            this.permissionsAllowed.add(s);
            this.permissionsNotAllowed.remove(s);
        }
        else {
            this.permissionsNotAllowed.add(s);
            this.permissionsAllowed.remove(s);
        }
        if(this.getPlayer().isOnline()){
            updatePerms((Player)this.getPlayer());
        }
        else {
            BasicsPlugin.getInstance().addToPermQuery(this.getPlayer());
        }
        Bukkit.getPluginManager().callEvent(new PlayerPermissionChangeEvent(this.getPlayer(), new Permission(s)));
    }

    public void addPerm(Permission p, Boolean value){
        addPerm(p.getName(), value);
    }

    public void clearPerms(){
        this.permissionsNotAllowed.clear();
        this.permissionsAllowed.clear();
        if(this.getPlayer().isOnline()){
            updatePerms((Player)this.getPlayer());
        }
        Bukkit.getPluginManager().callEvent(new PlayerPermissionChangeEvent(this.getPlayer(), null));
    }

    public static ArrayList<OfflinePlayer> getPlayersWithAccounts() {
        return new ArrayList<>(playersWithAccounts);
    }

    public static ArrayList<AccountPlayer> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public static void load(File file){
        try {
            AccountPlayer account = (AccountPlayer)loadFromFile(file.getName());
            account.p = Bukkit.getOfflinePlayer(account.playerID);
            accounts.add(account);
            playersWithAccounts.add(account.p);
        }catch (Exception e){
            if(!(e instanceof ClassCastException)) {
                e.printStackTrace();
            }
        }
    }
}
