package com.eot3000.groups;

import java.io.File;
import java.util.ArrayList;

import static com.eot3000.BasicsPlugin.loadFromFile;

public class AccountString extends Account{
    private static ArrayList<AccountString> accounts = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private String name;

    public AccountString(String name) {
        super(name);
        this.name = name;
        amount = 0d;
        accounts.add(this);
        names.add(name);
    }

    public String getName() {
        return name;
    }

    public static ArrayList<AccountString> getAccounts() {
        return accounts;
    }

    public static ArrayList<String> getNames() {
        return names;
    }

    public static void load(File file){
        try {
            AccountString account = (AccountString)loadFromFile(file.getName());
            accounts.add(account);
            names.add(account.name);
        }catch (Exception e){
            if(!(e instanceof ClassCastException)) {
                e.printStackTrace();
            }
        }
    }
}
