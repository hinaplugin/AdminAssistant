package com.hinaplugin.adminassistant.listener;

import com.hinaplugin.adminassistant.AdminAssistant;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageByEntityListener implements Listener {

    @EventHandler
    public void onPlayerDamageByEntity(final EntityDamageByEntityEvent event){
        final Entity entity = event.getEntity();
        if (entity instanceof Player){
            final Player player = (Player) entity;
            if (AdminAssistant.config.getGodList().contains(player.getUniqueId().toString())){
                event.setDamage(0);
                event.setCancelled(true);
            }
        }
    }
}
