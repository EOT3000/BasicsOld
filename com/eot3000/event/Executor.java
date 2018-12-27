package com.eot3000.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Executor implements EventExecutor {
    @Override
    public void execute(Listener listener, Event event) {
        new Thread(() ->{
            for(Method method : listener.getClass().getMethods()){
                if(Arrays.asList(method.getParameterTypes()).contains(event.getClass()) && method.getParameterTypes().length == 1){
                    if(method.isAnnotationPresent(EventHandler.class)){
                        try {
                            method.invoke(listener, event);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
