package com.hinaplugin.adminassistant.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerUUID extends ConfigDriver{
    public PlayerUUID(JavaPlugin plugin, String fileName, String resourceFileName) {
        super(plugin, fileName, resourceFileName);
    }

    public String containsUUID(final Player player){
        return this.config.getString(player.getUniqueId().toString(), null);
    }

    public void addUUID(final Player player){
        final String uuid = player.getUniqueId().toString();
        this.config.set("uuid." + uuid + ".name", player.getName());
        this.save();
    }

    public String getUUID(final String targetName){
        final ConfigurationSection section = this.config.getConfigurationSection("uuid");
        if (section != null){
            for (final String uuid : section.getKeys(false)){
                final String name = this.config.getString("uuid." + uuid + ".name", null);
                if (name == null){
                    continue;
                }
                if (name.equalsIgnoreCase(targetName)){
                    return uuid;
                }
            }
        }
        return null;
    }
}
