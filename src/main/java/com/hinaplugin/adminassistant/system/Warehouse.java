package com.hinaplugin.adminassistant.system;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class Warehouse {

    private final Map<Player, Integer> inventoryMap = Maps.newHashMap();
    public Map<Player, Integer> getInventoryMap(){ return inventoryMap; }

    private final Map<Player, List<Player>> playerListMap = Maps.newHashMap();
    public Map<Player, List<Player>> getPlayerListMap(){ return playerListMap; }
}
