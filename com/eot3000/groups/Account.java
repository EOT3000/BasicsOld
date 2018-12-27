package com.eot3000.groups;

import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class Account implements Serializable {
    protected Double amount;
    private static LinkedHashMap<Object, Account> accounts = new LinkedHashMap<>();

    public void add(Double f){
        amount+=f;
    }

    public void remove(Double f){
        amount-=f;
    }

    public Double getBalance(){
        return amount;
    }

    protected Account(Object key){
        if(accounts.containsKey(key)){
            throw new ExceptionInInitializerError("Account Already Exists With Key" + key.toString());
        }
        accounts.put(key, this);
    }
    public Account get(Object key){
        return accounts.get(key);
    }
}
