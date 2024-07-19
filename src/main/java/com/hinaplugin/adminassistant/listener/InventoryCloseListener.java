package com.hinaplugin.adminassistant.listener;

import com.hinaplugin.adminassistant.AdminAssistant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event){
        final Player player = (Player) event.getPlayer();
        AdminAssistant.warehouse.getInventoryMap().remove(player);
        AdminAssistant.warehouse.getPlayerListMap().remove(player);
    }
}
