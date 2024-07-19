package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;

public class Silent extends Permissions {

    public void command(final Player player){
        if (this.check(player, 5)){
            final String uuid = player.getUniqueId().toString();
            if (AdminAssistant.config.containsSilent(uuid)){
                AdminAssistant.config.removeSilent(uuid);
                player.sendMessage(AdminAssistant.message.getMessage("silent-true"));
            }else {
                AdminAssistant.config.addSilent(uuid);
                player.sendMessage(AdminAssistant.message.getMessage("silent-false"));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }

    public void command(final Player player, final String type, final String targetName){
        if (this.check(player, 5)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target != null){
                final String uuid = target.getUniqueId().toString();
                switch (type){
                    case "add" -> {
                        if (AdminAssistant.config.containsSilent(uuid)){
                            player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-already-add", targetName));
                        }else {
                            AdminAssistant.config.addSilent(uuid);
                            player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-false", targetName));
                        }
                    }
                    case "remove" -> {
                        if (AdminAssistant.config.containsSilent(uuid)){
                            AdminAssistant.config.removeSilent(uuid);
                            player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-true", targetName));
                        }else {
                            player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-none-add", targetName));
                        }
                    }
                }
            }else {
                switch (type){
                    case "add" -> {
                        final String uuid = AdminAssistant.uuid.getUUID(targetName);
                        if (uuid != null){
                            if (AdminAssistant.config.containsSilent(uuid)){
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-already-add", targetName));
                            }else {
                                AdminAssistant.config.addSilent(uuid);
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-false", targetName));
                            }
                        }else {
                            if (AdminAssistant.config.containsSilentName(targetName)){
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-already-add", targetName));
                            }else {
                                AdminAssistant.config.addSilentName(targetName);
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-false", targetName));
                            }
                        }
                    }
                    case "remove" -> {
                        final String uuid = AdminAssistant.uuid.getUUID(targetName);
                        if (uuid != null){
                            if (AdminAssistant.config.containsSilent(uuid)){
                                AdminAssistant.config.removeSilent(uuid);
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-true", targetName));
                            }else {
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-none-add", targetName));
                            }
                        }else {
                            if (AdminAssistant.config.containsSilentName(targetName)){
                                AdminAssistant.config.removeSilentName(targetName);
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-other-true", targetName));
                            }else {
                                player.sendMessage(AdminAssistant.message.getMessagePlayer("silent-none-add", targetName));
                            }
                        }
                    }
                }
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
