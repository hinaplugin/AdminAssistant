package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;

public class Op extends Permissions {

    public void command(final Player player){
        if (this.check(player, 1) || AdminAssistant.config.containsOp(player.getUniqueId().toString())){
            if (player.isOp()){
                player.sendMessage(AdminAssistant.message.getMessage("op-error"));
            }else {
                player.setOp(true);
                player.sendMessage(AdminAssistant.message.getMessage("op-true"));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }

    public void command(final Player player, final String targetName, final String type){
        if (this.check(player, 1)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }
            final String uuid = target.getUniqueId().toString();
            switch (type){
                case "add" -> {
                    if (AdminAssistant.config.containsOp(uuid)){
                        player.sendMessage(AdminAssistant.message.getMessagePlayer("op-already-add", targetName));
                    }else {
                        AdminAssistant.config.addOp(uuid);
                        player.sendMessage(AdminAssistant.message.getMessagePlayer("op-list-add", targetName));
                    }
                }
                case "remove" -> {
                    if (AdminAssistant.config.containsOp(uuid)){
                        AdminAssistant.config.removeOp(uuid);
                        player.sendMessage(AdminAssistant.message.getMessagePlayer("op-list-remove", targetName));
                    }else {
                        player.sendMessage(AdminAssistant.message.getMessagePlayer("op-none-add", targetName));
                    }
                }
                default -> player.sendMessage("§cusage: /admin op <add|remove> <playerName>");
            }
        }
    }
}
