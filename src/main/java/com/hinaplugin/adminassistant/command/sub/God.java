package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;

public class God extends Permissions {

    public void command(final Player player){
        final String uuid = player.getUniqueId().toString();
        if (this.check(player, 4)){
            if (AdminAssistant.config.containsGod(uuid)){
                AdminAssistant.config.removeGod(uuid);
                player.sendMessage(AdminAssistant.message.getMessage("god-false"));
            }else {
                AdminAssistant.config.addGod(uuid);
                player.sendMessage(AdminAssistant.message.getMessage("god-true"));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
