package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spectator extends Permissions {

    public void command(final Player player){
        if (this.check(player, 10)){
            if (player.getGameMode() != GameMode.SPECTATOR){
                final BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(AdminAssistant.message.getMessage("spectator"));
                    }
                };
                runnable.runTaskLater(AdminAssistant.plugin, 0L);
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }

    public void command(final Player player, final String targetName){
        if (this.check(player, 10)){
            boolean mode = false;
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }

            if (player.getGameMode() != GameMode.SPECTATOR){
                mode = true;
            }

            boolean finalMode = mode;
            final BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if (finalMode){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(AdminAssistant.message.getMessage("spectator"));
                    }
                    player.setSpectatorTarget(target);
                    player.sendMessage(AdminAssistant.message.getMessagePlayer("spectator-target", targetName));
                }
            };
            runnable.runTaskLater(AdminAssistant.plugin, 0L);

        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
