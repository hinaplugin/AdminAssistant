package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends Permissions {

    public void command(final Player player, final String targetName){
        if (this.check(player, 9)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(AdminAssistant.message.getMessagePlayer("night_vision-false", targetName));
            }else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 255, false, false, true));
                player.sendMessage(AdminAssistant.message.getMessagePlayer("night_vision-true", targetName));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }
}
