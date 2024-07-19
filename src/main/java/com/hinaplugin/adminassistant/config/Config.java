package com.hinaplugin.adminassistant.config;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Config extends ConfigDriver{

    public Config(JavaPlugin plugin, String fileName, String resourceFileName, int version) {
        super(plugin, fileName, resourceFileName, version);
    }

    public List<String> getOpList(){
        return this.config.getStringList("op");
    }

    public void addOp(final String uuid){
        final List<String> opList = this.getOpList();
        opList.add(uuid);
        this.config.set("op", opList);
        this.save();
    }

    public void removeOp(final String uuid){
        final List<String> opList = this.getOpList();
        opList.remove(uuid);
        this.config.set("op", opList);
        this.save();
    }

    public boolean containsOp(final String uuid){
        return this.getOpList().contains(uuid);
    }

    public List<String> getGodList(){
        return this.config.getStringList("god");
    }

    public void addGod(final String uuid){
        final List<String> godList = this.getGodList();
        godList.add(uuid);
        this.config.set("god", godList);
        this.save();
    }

    public void removeGod(final String uuid){
        final List<String> godList = this.getGodList();
        godList.remove(uuid);
        this.config.set("god", this.getGodList().remove(uuid));
        this.save();
    }

    public boolean containsGod(final String uuid){
        return this.getGodList().contains(uuid);
    }

    public List<String> getSilentList(){
        return this.config.getStringList("silent-uuid");
    }

    public void addSilent(final String uuid){
        final List<String> silentList = this.getSilentList();
        silentList.add(uuid);
        this.config.set("silent-uuid", silentList);
        this.save();
    }

    public void removeSilent(final String uuid){
        final List<String> silentList = this.getSilentList();
        silentList.remove(uuid);
        this.config.set("silent-uuid", silentList);
        this.save();
    }

    public List<String> getSilentNameList(){ return this.config.getStringList("silent-name"); }

    public void addSilentName(final String name){
        final List<String> silentList = this.getSilentNameList();
        silentList.add(name);
        this.config.set("silent-name", silentList);
        this.save();
    }

    public void removeSilentName(final String name){
        final List<String> silentList = this.getSilentNameList();
        silentList.remove(name);
        this.config.set("silent-name", silentList);
        this.save();
    }

    public boolean containsSilentName(final String name){
        return this.getSilentNameList().contains(name);
    }

    public boolean containsSilent(final String uuid){
        return this.getSilentList().contains(uuid);
    }
}
