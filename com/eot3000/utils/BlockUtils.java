package com.eot3000.utils;

import org.bukkit.Location;

public final class BlockUtils {
    private BlockUtils(){

    }
    public static boolean blockInRegion(Location block, Vec3Position start, Vec3Position end){
        for(int x = 0; x <= (start.x >= end.x ?start.x - end.x + 1 :end.x - start.x + 1); x++){
            for(int y = 0; y <= (start.y >= end.y ?start.y - end.y + 1 :end.y - start.y + 1); y++){
                for(int z = 0; z <= (start.z >= end.z ?start.z - end.z + 1 :end.z - start.z + 1); z++){
                    if(new Location(block.getWorld(), x, y, z).equals(block)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
