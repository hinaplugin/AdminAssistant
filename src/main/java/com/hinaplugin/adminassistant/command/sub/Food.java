package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.entity.Player;

public class Food extends Permissions {

    public void command(final Player player, final String targetName, final String food){
        if (this.check(player, 7)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }
            if (this.isNumber(food)){
                player.sendMessage("§cusage: /admin food <playerName> <food>");
                return;
            }

            target.setFoodLevel(this.parseInt(food));
            player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("food-set", targetName, food));
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }

    private boolean isNumber(final String number){
        return !number.chars().allMatch(Character::isDigit);
    }

    private int parseInt(final String food){
        return Integer.parseInt(food);
    }
}
