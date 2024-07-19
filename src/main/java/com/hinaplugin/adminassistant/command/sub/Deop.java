package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;

public class Deop extends Permissions {

    public void command(final Player player){
        if (this.check(player, 2) || AdminAssistant.config.containsOp(player.getUniqueId().toString())){
            if (player.isOp()){
                player.setOp(false);
                player.sendMessage(AdminAssistant.message.getMessage("deop-true"));
            }else {
                player.sendMessage(AdminAssistant.message.getMessage("deop-error"));
            }
        }
    }
}
