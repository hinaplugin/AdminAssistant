package com.hinaplugin.adminassistant.command.sub;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.Permissions;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class Health extends Permissions {

    public void command(final Player player, final String targetName, final String health, final String... strings){
        if (this.check(player, 6)){
            final Player target = AdminAssistant.plugin.getServer().getPlayer(targetName);
            if (target == null){
                player.sendMessage(AdminAssistant.message.getMessagePlayer("player-null", targetName));
                return;
            }
            if (this.isNumber(health)){
                player.sendMessage("§cusage: /admin health <playerName> <health> [attribute:true|false] [attribute-only:true|false]");
                return;
            }

            if (strings.length == 0){
                this.playerHealth(player, target, health, false, false);
            }else if (strings.length == 1){
                this.playerHealth(player, target, health, strings[0].equalsIgnoreCase("true"), false);
            }else {
                this.playerHealth(player, target, health, strings[0].equalsIgnoreCase("true"), strings[1].equalsIgnoreCase("true"));
            }
        }else {
            player.sendMessage(AdminAssistant.message.getMessage("permission-error"));
        }
    }

    private void playerHealth(final Player player, final Player target, final String health, final boolean attribute, final boolean only){
        final AttributeInstance instance = this.getPlayerAttribute(target);
        final double baseValue = instance.getBaseValue();
        final double updateHealth = this.parseDouble(health);
        if (attribute){
            if (only){
                if (updateHealth > 2048.0){
                    instance.setBaseValue(2048.0);
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-limit-set", target.getName(), this.parseString(2048.0)));
                    player.sendMessage(AdminAssistant.message.getMessage("health-max-value"));
                }else {
                    instance.setBaseValue(updateHealth);
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-limit-set", target.getName(), health));
                }
            }else {
                if (updateHealth > 2048.0){
                    instance.setBaseValue(2048.0);
                    target.setHealth(2048.0);
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-set", target.getName(), this.parseString(2048.0)));
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-limit-set", target.getName(), this.parseString(2048.0)));
                    player.sendMessage(AdminAssistant.message.getMessage("health-max-value"));
                }else {
                    instance.setBaseValue(updateHealth);
                    target.setHealth(updateHealth);
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-set", target.getName(), health));
                    player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-limit-set", target.getName(), health));
                }
            }
        }else {
            if (updateHealth > baseValue){
                target.setHealth(baseValue);
                player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-set", target.getName(), this.parseString(baseValue)));
                player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-value-error", target.getName(), this.parseString(baseValue)));
                player.sendMessage(AdminAssistant.message.getMessage("health-value-error2"));
                player.sendMessage(AdminAssistant.message.getMessage("health-value-error3"));

            }else {
                target.setHealth(updateHealth);
                player.sendMessage(AdminAssistant.message.getMessageValueAndPlayer("health-set", target.getName(), health));
            }
        }
    }

    private AttributeInstance getPlayerAttribute(final Player player){
        final Attribute attribute = Attribute.GENERIC_MAX_HEALTH;
        return player.getAttribute(attribute);
    }

    private boolean isNumber(final String number){
        return !number.chars().allMatch(Character::isDigit);
    }

    private double parseDouble(final String health){
        return Double.parseDouble(health);
    }

    private String parseString(final double health){
        return String.valueOf(health);
    }
}
