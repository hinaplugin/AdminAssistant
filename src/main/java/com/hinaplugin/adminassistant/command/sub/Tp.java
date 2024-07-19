package com.hinaplugin.adminassistant.command.sub;

import com.google.common.collect.Lists;
import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import com.hinaplugin.adminassistant.system.CreatePanel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Tp extends Permissions {

    public void command(final Player player){
        if (this.check(player, 3)){
            AdminAssistant.warehouse.getPlayerListMap().put(player, Lists.newArrayList(AdminAssistant.plugin.getServer().getOnlinePlayers()));
            AdminAssistant.warehouse.getPlayerListMap().get(player).remove(player);
            final Inventory inventory = new CreatePanel().create(player, 1);
            player.openInventory(inventory);
            AdminAssistant.warehouse.getInventoryMap().put(player, 1);
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
