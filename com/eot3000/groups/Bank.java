package com.eot3000.groups;

import com.eot3000.BasicsPlugin;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static ArrayList<Bank> banks = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private int balance;
    private OfflinePlayer owner;
    private ArrayList<AccountPlayer> players = new ArrayList<>();
    private String name;

    public ArrayList<AccountPlayer> getPlayers() {
        return new ArrayList<>(players);
    }

    public OfflinePlayer getOwner(){
        return owner;
    }

    public static Bank getBank(String name){
        for(Bank f:banks){
            if(f.name.equalsIgnoreCase(name)){
                return f;
            }
        }
        return null;
    }

    public void addPlayer(OfflinePlayer player){
        players.add(BasicsPlugin.getInstance().getAccount(player));
    }

    public int getBalance() {
        return balance;
    }

    public void delete(){
        this.balance = 0;
        this.name = null;
        this.owner = null;
        this.players = null;
        banks.remove(this);
    }

    public void withdraw(double d){
        balance -= d;
    }

    public void deposit(double d){
        balance += d;
    }

    public static List<Bank> getBanks(){
        return new ArrayList<>(banks);
    }

    public static List<String> getBankNames(){
        return new ArrayList<>(names);
    }

    public Bank(OfflinePlayer owner, String name){
        if(!(getBank(name) == null)){
            throw new ExceptionInInitializerError();
        }
        this.owner = owner;
        this.players.add(BasicsPlugin.getInstance().getAccount(owner));
        this.name = name;
        banks.add(this);
        names.add(name);
    }
}
