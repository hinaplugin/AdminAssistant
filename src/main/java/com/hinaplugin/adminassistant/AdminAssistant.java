package com.hinaplugin.adminassistant;

import com.hinaplugin.adminassistant.command.Commands;
import com.hinaplugin.adminassistant.config.Config;
import com.hinaplugin.adminassistant.config.Message;
import com.hinaplugin.adminassistant.config.PlayerUUID;
import com.hinaplugin.adminassistant.listener.*;
import com.hinaplugin.adminassistant.system.Warehouse;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public final class AdminAssistant extends JavaPlugin {
    public static AdminAssistant plugin;
    public static Logger logger;
    public static Config config;
    public static Message message;
    public static PlayerUUID uuid;
    public static Warehouse warehouse;

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            plugin = this;
            logger = this.getLogger();
            config = new Config(this, "config.yml", "config.yml", 1);
            config.load();
            config.update();
            message = new Message(this, "message.yml", "message.yml", 1);
            message.load();
            message.update();
            uuid = new PlayerUUID(this, "uuid.yml", "uuid.yml");
            uuid.load();
            warehouse = new Warehouse();

            this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
            this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(),this);
            this.getServer().getPluginManager().registerEvents(new PlayerDamageByEntityListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerDamageByBlockListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

            final PluginCommand command = this.getCommand("admin");
            if (command != null){
                command.setExecutor(new Commands());
            }
            logger.info("AdminAssistant is Enabled!");
        }catch (final Exception exception){
            exception.printStackTrace(new PrintWriter(new StringWriter()));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            HandlerList.unregisterAll(this);
            logger.info("AdminAssistant is Disabled!");
        }catch (final Exception exception){
            exception.printStackTrace(new PrintWriter(new StringWriter()));
        }
    }
}
