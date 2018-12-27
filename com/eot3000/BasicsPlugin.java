package com.eot3000;

import com.eot3000.command.CommandRunner;
import com.eot3000.command.CommandRunner2;
import com.eot3000.command.MoneyCommandsVault;
import com.eot3000.groups.*;
import com.eot3000.vault.eco.Eco;
import com.eot3000.event.Executor;
import com.eot3000.event.PlayerPermissionChangeEvent;
import com.eot3000.vault.permission.Perms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public final class BasicsPlugin extends JavaPlugin implements Listener {
    private static BasicsPlugin instance = null;
    private LinkedHashMap<File, LinkedHashMap<String, String>> usersLoaded = new LinkedHashMap<>();
    private Eco eco;
    private Perms perms;
    private String currency;
    private String currencies;
    private int fractionalDigits;
    private MoneyCommandsVault moneyCommandsVault = null;
    private CommandRunner2 commandRunner2 = null;
    private CommandRunner commandRunner = null;
    private Executor executor = new Executor();
    private PluginManager pm = getServer().getPluginManager();
    private ServicesManager sm = Bukkit.getServicesManager();
    private ArrayList<UUID> permQueryUUID = new ArrayList<>();

    @Override
    public void onEnable() {
        pm.registerEvents(this, this);
        pm.registerEvent(PlayerPermissionChangeEvent.class, this, EventPriority.HIGHEST, executor, this);
        moneyCommandsVault = new MoneyCommandsVault();
        commandRunner = new CommandRunner();
        commandRunner2 = new CommandRunner2();
        this.getCommand("heal").setExecutor(commandRunner);
        this.getCommand("killme").setExecutor(commandRunner);
        this.getCommand("killall").setExecutor(commandRunner);
        this.getCommand("walkspeed").setExecutor(commandRunner2);
        this.getCommand("eco").setExecutor(moneyCommandsVault);
        this.getCommand("eco").setTabCompleter(moneyCommandsVault);
        this.getDataFolder().mkdir();
        new Thread(() ->{
            for(File file : BasicsPlugin.getInstance().getDataFolder().listFiles()){
                try{
                    AccountPlayer.load(file);
                    AccountString.load(file);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(ChatColor.GREEN + "[" + ChatColor.RED + "Basics" + ChatColor.GREEN + "]" + ChatColor.GOLD + "Enabled Basics!");
    }

    @Override
    public void onDisable(){
        for(AccountPlayer account : AccountPlayer.getAccounts()){
            try {
                saveToFile(new File(this.getDataFolder() + "\\" + account.getPlayer().getUniqueId().toString()), account);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addToPermQuery(OfflinePlayer player){
        if(permQueryUUID.contains(player.getUniqueId())){
            return;
        }
        permQueryUUID.add(player.getUniqueId());
    }

    public boolean hasBank(OfflinePlayer player){
        for(Bank b:Bank.getBanks()){
            if(b.getPlayers().contains(getAccount(player))){
                return true;
            }
        }
        return false;
    }

    public boolean isBankOwner(OfflinePlayer player){
        for(Bank b:Bank.getBanks()){
            if(b.getOwner().equals(player)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Bank> getBanks(OfflinePlayer player){
        ArrayList<Bank> a = new ArrayList<>();
        if(hasBank(player)){
            for(Bank b:Bank.getBanks()){
                if(b.getPlayers().contains(getAccount(player))){
                    a.add(b);
                }
            }
        }
        return a;
    }

    public ArrayList<Group> getGroups(OfflinePlayer player){
        ArrayList<Group> a = new ArrayList<>();
        if(hasGroup(player)){
            for(Group g:Group.getGroups()){
                if(g.getPlayers().contains(getAccount(player))){
                    a.add(g);
                }
            }
        }
        return a;
    }

    public ArrayList<String> getGroups(OfflinePlayer player, String Null){
        ArrayList<String> a = new ArrayList<>();
        if(hasGroup(player)){
            for(Group g:Group.getGroups()){
                if(g.getPlayers().contains(getAccount(player))){
                    a.add(g.toString());
                }
            }
        }
        return a;
    }

    public AccountPlayer getAccount(OfflinePlayer player){
        for(AccountPlayer a:AccountPlayer.getAccounts()){
            if(a.getPlayer().equals(player)){
                return a;
            }
        }
        return null;
    }

    public boolean hasGroup(OfflinePlayer player){
        for(Group g:Group.getGroups()){
            if(g.getPlayers().contains(getAccount(player))){
                return true;
            }
        }
        return false;
    }

    public boolean hasAccount(OfflinePlayer player){
        return AccountPlayer.getPlayersWithAccounts().contains(player);
    }

    public int getFractionalDigits(){
        return fractionalDigits;
    }

    public String getCurrencies() {
        return currencies;
    }

    public String getCurrency() {
        return currency;
    }

    public static BasicsPlugin getInstance(){
        return instance;
    }

    public static Object loadFromFile(String name) throws IOException, ClassNotFoundException{
        Object b;
        File a = new File(BasicsPlugin.getInstance().getDataFolder().getAbsolutePath() + "\\" + name);
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(a));
        b = stream.readObject();
        stream.close();
        return b;
    }

    private static void saveToFile(File file, Object print) throws IOException {
        System.out.println(file.createNewFile());
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(print);
        stream.flush();
        stream.close();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event){
    }

    public void onBlockDamage(BlockDamageEvent event){
        event.getBlock().getLocation();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!AccountPlayer.getPlayersWithAccounts().contains(event.getPlayer())) {
            eco.createPlayerAccount(event.getPlayer());
        }
        if(permQueryUUID.contains(event.getPlayer().getUniqueId())){
            getAccount(event.getPlayer()).updatePerms(event.getPlayer());
        }
    }

    //public static void main(String[] args){
    //}
    public BasicsPlugin(){
        instance = this;
        eco = Eco.getInstance();
        perms = Perms.getInstance();
        fractionalDigits = 3;
        currency = "dollar";
        currencies = "dollars";
        Vault vault = (Vault) JavaPlugin.getProvidingPlugin(Vault.class);
        sm.register(Economy.class, eco, vault, ServicePriority.Highest);
        //sm.register(Permission.class, perms, vault, ServicePriority.Highest);
        System.out.println(ChatColor.DARK_BLUE + "[" + ChatColor.DARK_AQUA + "Basics" + ChatColor.DARK_BLUE + "]" + ChatColor.YELLOW + "Instantiated Basics!");
    }
}
