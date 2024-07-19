package com.hinaplugin.adminassistant.listener;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends Permissions implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){
        final Player player = event.getPlayer();
        if (AdminAssistant.uuid.containsUUID(player) == null){
            AdminAssistant.uuid.addUUID(player);
        }
        if (AdminAssistant.config.getSilentList().contains(player.getUniqueId().toString())){
            for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                if (this.check(target, 11)){
                    target.sendMessage(AdminAssistant.message.getMessagePlayer("silent-user-join", player.getName()));
                }
            }
            event.joinMessage(null);
        }
    }
}
