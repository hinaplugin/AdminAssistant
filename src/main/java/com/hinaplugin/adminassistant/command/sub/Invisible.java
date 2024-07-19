package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Invisible extends Permissions {

    public void command(final Player player, final String targetName){
        if (this.check(player, 8)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }
            if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)){
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                player.sendMessage(AdminAssistant.message.getMessagePlayer("invisible-false", targetName));
            }else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, false, false, true));
                player.sendMessage(AdminAssistant.message.getMessagePlayer("invisible-true", targetName));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
