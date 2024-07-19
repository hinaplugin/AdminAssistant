package com.hinaplugin.adminassistant.listener;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends Permissions implements Listener {

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event){
        final Player player = event.getPlayer();
        if (AdminAssistant.config.getSilentList().contains(player.getUniqueId().toString())){
            for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                if (this.check(target, 11)){
                    target.sendMessage(AdminAssistant.message.getMessagePlayer("silent-user-leave", player.getName()));
                }
            }
            event.quitMessage(null);
        }
    }
}
