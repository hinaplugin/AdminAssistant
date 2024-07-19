package com.hinaplugin.adminassistant.command;

import org.bukkit.entity.Player;

public class Permissions {

    public boolean check(final Player player, final int mode){
        return switch (mode){
            case 1 -> player.hasPermission("admin.op");
            case 2 -> player.hasPermission("admin.deop");
            case 3 -> player.hasPermission("admin.tp");
            case 4 -> player.hasPermission("admin.god");
            case 5 -> player.hasPermission("admin.silent");
            case 6 -> player.hasPermission("admin.health");
            case 7 -> player.hasPermission("admin.food");
            case 8 -> player.hasPermission("admin.invisible");
            case 9 -> player.hasPermission("admin.nightvision");
            case 10 -> player.hasPermission("admin.spectator");
            case 11 -> player.hasPermission("admin.silent.notice");
            default -> false;
        };
    }
}
