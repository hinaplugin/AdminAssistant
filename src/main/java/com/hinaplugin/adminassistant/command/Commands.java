package com.hinaplugin.adminassistant.command;

import com.google.common.collect.Lists;
import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.command.sub.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Commands extends Permissions implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            final Player player = (Player) commandSender;
            if (strings.length >= 1){
                switch (strings[0]){
                    case "op" -> {
                        if (strings.length >= 3){
                            new Op().command(player, strings[2], strings[1]);
                        }else {
                            new Op().command(player);
                        }
                    }
                    case "deop" -> new Deop().command(player);
                    case "tp" -> new Tp().command(player);
                    case "god" -> new God().command(player);
                    case "silent" -> {
                        if (strings.length >= 3){
                            new Silent().command(player, strings[1], strings[2]);
                        }else {
                            new Silent().command(player);
                        }
                    }
                    case "health" -> {
                        if (strings.length == 3){
                            new Health().command(player, strings[1], strings[2]);
                        }else if (strings.length == 4){
                            new Health().command(player, strings[1], strings[2], strings[3]);
                        }else if (strings.length == 5){
                            new Health().command(player, strings[1], strings[2], strings[3], strings[4]);
                        }
                    }
                    case "food" -> {
                        if (strings.length >= 3){
                            new Food().command(player, strings[1], strings[2]);
                        }
                    }
                    case "invisible" -> {
                        if (strings.length >= 2){
                            new Invisible().command(player, strings[1]);
                        }
                    }
                    case "nightvision" -> {
                        if (strings.length >= 2){
                            new NightVision().command(player, strings[1]);
                        }
                    }
                    case "spectator" -> {
                        if (strings.length == 1){
                            new Spectator().command(player);
                        }else {
                            new Spectator().command(player, strings[1]);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> complete = Lists.newArrayList();
        if (commandSender instanceof Player){
            final Player player = (Player) commandSender;
            if (command.getName().equalsIgnoreCase("admin")){
                if (strings.length == 1){
                    if (strings[0].isEmpty()){
                        if (this.check(player, 1)){
                            complete.add("op");
                        }
                        if (this.check(player, 2)){
                            complete.add("deop");
                        }
                        if (this.check(player, 3)){
                            complete.add("tp");
                        }
                        if (this.check(player, 4)){
                            complete.add("god");
                        }
                        if (this.check(player, 5)){
                            complete.add("silent");
                        }
                        if (this.check(player, 6)){
                            complete.add("health");
                        }
                        if (this.check(player, 7)){
                            complete.add("food");
                        }
                        if (this.check(player, 8)){
                            complete.add("invisible");
                        }
                        if (this.check(player, 9)){
                            complete.add("nightvision");
                        }
                        if (this.check(player, 10)){
                            complete.add("spectator");
                        }
                    }else {
                        if (strings[0].startsWith("d")){
                            complete.add("deop");
                        }else if (strings[0].startsWith("f")){
                            complete.add("food");
                        }else if (strings[0].startsWith("g")){
                            complete.add("god");
                        }else if (strings[0].startsWith("h")){
                            complete.add("health");
                        }else if (strings[0].startsWith("i")){
                            complete.add("invisible");
                        }else if (strings[0].startsWith("n")){
                            complete.add("nightvision");
                        }else if (strings[0].startsWith("o")){
                            complete.add("op");
                        }else if (strings[0].startsWith("s")){
                            if (strings[0].length() == 1){
                                complete.add("silent");
                                complete.add("spectator");
                            }else {
                                if (strings[0].startsWith("si")){
                                    complete.add("silent");
                                }else if (strings[0].startsWith("sp")){
                                    complete.add("spectator");
                                }
                            }
                        }else if (strings[0].startsWith("t")){
                            complete.add("tp");
                        }
                    }
                }else if (strings.length == 2){
                    if (strings[0].equalsIgnoreCase("op")){
                        if (strings[1].isEmpty()){
                            complete.add("add");
                            complete.add("remove");
                        }else {
                            if (strings[1].startsWith("a")){
                                complete.add("add");
                            }else if (strings[1].startsWith("r")){
                                complete.add("remove");
                            }
                        }
                    }else if (strings[0].equalsIgnoreCase("silent")){
                        if (strings[1].isEmpty()){
                            complete.add("add");
                            complete.add("remove");
                        }else {
                            if (strings[1].startsWith("a")){
                                complete.add("add");
                            }else if (strings[1].startsWith("r")){
                                complete.add("remove");
                            }
                        }
                    }else if (strings[0].equalsIgnoreCase("health")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("food")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("invisible")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("nightvision")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("spectator")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }
                }else if (strings.length == 3){
                    if (strings[0].equalsIgnoreCase("op")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("silent")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }else if (strings[0].equalsIgnoreCase("spectator")){
                        for (final Player target : AdminAssistant.plugin.getServer().getOnlinePlayers()){
                            complete.add(target.getName());
                        }
                    }
                }else if (strings.length == 4){
                    if (strings[0].equalsIgnoreCase("health")){
                        if (strings[3].isEmpty()){
                            complete.add("true");
                            complete.add("false");
                        }else {
                            if (strings[3].startsWith("t")){
                                complete.add("true");
                            }else if (strings[3].startsWith("f")){
                                complete.add("false");
                            }
                        }
                    }
                }else if (strings.length == 5){
                    if (strings[0].equalsIgnoreCase("health")){
                        if (strings[4].isEmpty()){
                            complete.add("true");
                            complete.add("false");
                        }else {
                            if (strings[4].startsWith("t")){
                                complete.add("true");
                            }else if (strings[4].startsWith("f")){
                                complete.add("false");
                            }
                        }
                    }
                }
            }
        }
        return complete;
    }
}
