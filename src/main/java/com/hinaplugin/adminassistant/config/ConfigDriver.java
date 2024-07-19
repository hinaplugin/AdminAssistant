package com.hinaplugin.adminassistant.config;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * ・使用メソッド
 * load: FileConfigurationの読みこみ，なければ作成
 * save: FileConfigurationの変更を保存
 * update: FileConfigurationのバージョン変更によるアップデートとバックアップ
 */
public class ConfigDriver {
    private final JavaPlugin plugin;
    private final Logger logger;
    private String fileName = "config.yml";
    private String resourceFileName = "config.yml";
    public FileConfiguration config = null;
    private int version = 1;

    public ConfigDriver(final JavaPlugin plugin){
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public ConfigDriver(final JavaPlugin plugin, final String fileName, final String resourceFileName){
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.fileName = fileName;
        this.resourceFileName = resourceFileName;
    }

    public ConfigDriver(final JavaPlugin plugin, final String fileName, final String resourceFileName, final int version){
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.fileName = fileName;
        this.resourceFileName = resourceFileName;
        this.version = version;
    }

    public boolean load(){
        try {
            if (!plugin.getDataFolder().exists()){
                if (!plugin.getDataFolder().mkdir()){
                    return false;
                }
            }
            final File configFile = new File(plugin.getDataFolder(), fileName);
            if (!configFile.exists()){
                if (configFile.createNewFile()){
                    try (final InputStream inputStream = plugin.getResource(resourceFileName); final OutputStream outputStream = Files.newOutputStream(configFile.toPath())){
                        ByteStreams.copy(Objects.requireNonNull(inputStream), outputStream);
                    }catch (final Exception exception){
                        this.logTrace(exception);
                    }
                }
            }
            try (final InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(configFile.toPath()), Charsets.UTF_8)){
                this.config = YamlConfiguration.loadConfiguration(inputStreamReader);
            }catch (final Exception exception){
                this.logTrace(exception);
            }
            return true;
        }catch (final Exception exception){
            this.logTrace(exception);
            return false;
        }
    }

    public boolean save(){
        try {
            if (!plugin.getDataFolder().exists()){
                if (!plugin.getDataFolder().mkdir()){
                    return false;
                }
            }
            final File configFile = new File(plugin.getDataFolder(), fileName);
            if (!configFile.exists()){
                return false;
            }

            try (final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(configFile.toPath()), Charsets.UTF_8)){
                outputStreamWriter.write(config.saveToString());
            }catch (final Exception exception){
                this.logTrace(exception);
            }
            this.load();
            return true;
        }catch (final Exception exception){
            this.logTrace(exception);
            return false;
        }
    }

    public boolean update(){
        try {
            if (!plugin.getDataFolder().exists()){
                return false;
            }

            final File configFile = new File(plugin.getDataFolder(), fileName);
            if (!configFile.exists()){
                return false;
            }

            if (this.version == this.config.getInt("version", 1)){
                return false;
            }

            final String fileNameReplace = fileName.replace(".yml", "_old.yml");

            final File configOldFile = new File(plugin.getDataFolder(), fileNameReplace);
            if (configOldFile.exists()){
                if (!configOldFile.delete()){
                    return false;
                }
            }
            if (!configFile.renameTo(new File(plugin.getDataFolder(), fileNameReplace))){
                return false;
            }

            this.logger.warning(fileName + "がアップデートされました．");
            this.logger.warning("そのためこれまでの" + fileName + "は" + fileNameReplace + "に変更されました．");
            this.load();
            return true;
        }catch (final Exception exception){
            this.logTrace(exception);
            return false;
        }
    }

    private void logTrace(final Exception exception){
        logger.severe(exception.getClass().getName() + ": " + exception.getLocalizedMessage());
    }

}
