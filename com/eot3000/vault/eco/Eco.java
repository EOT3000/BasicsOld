package com.eot3000.vault.eco;

import com.eot3000.groups.AccountPlayer;
import com.eot3000.groups.AccountString;
import com.eot3000.groups.Bank;
import com.eot3000.BasicsPlugin;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

public final class Eco extends AbstractEconomy {
    private static Eco instance;
    private BasicsPlugin main = BasicsPlugin.getInstance();

    @Override
    public boolean isEnabled() {
        return main.isEnabled();
    }

    @Override
    public String getName() {
        return "Basics";
    }

    @Override
    public boolean hasBankSupport() {
        return true;
    }

    @Override
    public int fractionalDigits() {
        return main.getFractionalDigits();
    }

    @Override
    public String format(double v) {
        String s;
        if(v == 1) {
            s = this.currencyNameSingular();
        }
        else{
            s = this.currencyNamePlural();
        }
        return ((Double)v).toString() + " " + s;
    }

    @Override
    public String currencyNamePlural() {
        return main.getCurrencies();
    }

    @Override
    public String currencyNameSingular() {
        return main.getCurrency();
    }

    @Override
    public boolean hasAccount(String s) {
        return AccountString.getNames().contains(s) || AccountPlayer.getPlayersWithAccounts().contains(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return hasAccount(s);
    }

    @Override
    public double getBalance(String s) {
        return getBalance(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return getAccount(player).getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return this.getBalance(player);
    }

    @Override
    public double getBalance(String s, String s1) {
        return this.getBalance(s);
    }

    @Override
    public boolean has(String s, double v) {
        return has(Bukkit.getOfflinePlayer(s), v);
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return has(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(s), v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return withdrawPlayer(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return depositPlayer(Bukkit.getOfflinePlayer(s), v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return depositPlayer(s, v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return createBank(s, Bukkit.getOfflinePlayer(s1));
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        Bank.getBank(s).delete();
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, Bank.getBank(s).getBalance(), EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        Bank.getBank(s).withdraw(v);
        return new EconomyResponse(v, Bank.getBank(s).getBalance() - v, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        Bank.getBank(s).deposit(v);
        return new EconomyResponse(v, Bank.getBank(s).getBalance() - v, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return isBankOwner(s, Bukkit.getOfflinePlayer(s1));
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return isBankMember(s, Bukkit.getOfflinePlayer(s1));
    }

    @Override
    public List<String> getBanks() {
        return Bank.getBankNames();
    }

    @Override
    public boolean createPlayerAccount(String s) {
        try {
            if (Bukkit.getOfflinePlayer(s) != null) {
                new AccountPlayer(Bukkit.getOfflinePlayer(s));
                return true;
            }
            new AccountString(s);
        }
        catch (ExceptionInInitializerError e){
            return false;
        }
        return true;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return createPlayerAccount(s);
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        if(Bank.getBank(s).getOwner().equals(offlinePlayer)) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "");
        }
        else{
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Not owner!");
        }
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        try{
            new AccountPlayer(player.getPlayer());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        createPlayerAccount(offlinePlayer);
        return true;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        if(Bank.getBank(s).getPlayers().contains(offlinePlayer)) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "");
        }
        else{
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Not member!");
        }
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return getAccount(offlinePlayer).getBalance() >= v;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return getAccount(offlinePlayer) != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return hasAccount(offlinePlayer);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return has(offlinePlayer, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        getAccount(offlinePlayer).remove(v);
        return new EconomyResponse(v, getAccount(offlinePlayer).getBalance() - v, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return withdrawPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        getAccount(offlinePlayer).add(v);
        return new EconomyResponse(v, getAccount(offlinePlayer).getBalance() + v, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return depositPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        new Bank(offlinePlayer, s);
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    private AccountPlayer getAccount(OfflinePlayer player){
        return main.getAccount(player);
    }

    public boolean hasBank(OfflinePlayer player){
        return main.hasBank(player);
    }

    public void addPlayerToBank(OfflinePlayer player, String string){
        Bank.getBank(string).addPlayer(player);
    }

    public static Eco getInstance(){
        if (instance == null) {
            instance = new Eco();
        }
        return instance;
    }

    private Eco(){

    }

}