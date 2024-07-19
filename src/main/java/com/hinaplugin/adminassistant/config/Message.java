package com.hinaplugin.adminassistant.config;

import org.bukkit.plugin.java.JavaPlugin;

public class Message extends ConfigDriver{
    public Message(JavaPlugin plugin, String fileName, String resourceFileName, int version) {
        super(plugin, fileName, resourceFileName, version);
    }

    public String getMessage(final String key){
        final String message = this.config.getString(key, null);
        if (message != null){
            return message;
        }
        return "§cメッセージ取得エラー";
    }

    public String getMessagePlayer(final String key, final String playerName){
        final String message = this.config.getString(key, null);
        if (message != null){
            return message.replace("$player", playerName);
        }
        return "§cメッセージ取得エラー";
    }

    public String getMessageValueAndPlayer(final String key,final String playerName, final String value){
        final String message = this.config.getString(key, null);
        if (message != null){
            return message.replace("$player", playerName).replace("$value", value);
        }
        return "§cメッセージ取得エラー";
    }


}
