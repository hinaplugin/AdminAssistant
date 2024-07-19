package com.hinaplugin.adminassistant.system;

import com.google.common.collect.Lists;
import com.hinaplugin.adminassistant.AdminAssistant;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CreatePanel {

    public Inventory create(final Player player, final int page){
        final Inventory inventory = Bukkit.createInventory(null, 54, Component.text().content("Admin Teleport Panel").build());
        final List<Player> players = AdminAssistant.warehouse.getPlayerListMap().get(player);
        AdminAssistant.warehouse.getPlayerListMap().put(player, players);
        final int count = 45 * (page - 1);

        for (int i = count; i < 45 * page; i++){
            if (i == players.size()){
                break;
            }
            final ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
            final ItemMeta itemMeta = itemStack.getItemMeta();
            final SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setOwningPlayer(players.get(i));
            skullMeta.displayName(Component.text().content(players.get(i).getName()).build());
            final List<Component> lore = Lists.newArrayList();
            lore.add(Component.text().content("x: " + players.get(i).getLocation().getBlockX()).build());
            lore.add(Component.text().content("y: " + players.get(i).getLocation().getBlockY()).build());
            lore.add(Component.text().content("z: " + players.get(i).getLocation().getBlockZ()).build());
            lore.add(Component.text().content("world: " + players.get(i).getWorld().getName()).build());
            skullMeta.lore(lore);
            final PersistentDataContainer container = skullMeta.getPersistentDataContainer();
            final NamespacedKey key = new NamespacedKey(AdminAssistant.plugin, "assistant");
            container.set(key, PersistentDataType.STRING, "skull");
            itemStack.setItemMeta(skullMeta);
            inventory.setItem(i, itemStack);
        }

        inventory.setItem(45, this.createBack());
        inventory.setItem(49, this.createClose());
        inventory.setItem(53, this.createNext());

        return inventory;
    }

    private ItemStack createNext(){
        final ItemStack itemStack = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        final NamespacedKey key = new NamespacedKey(AdminAssistant.plugin, "assistant");
        container.set(key, PersistentDataType.STRING, "next");
        itemMeta.displayName(Component.text().content("次のページ").build());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack createBack(){
        final ItemStack itemStack = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        final NamespacedKey key = new NamespacedKey(AdminAssistant.plugin, "assistant");
        container.set(key, PersistentDataType.STRING, "back");
        itemMeta.displayName(Component.text().content("前のページ").build());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack createClose(){
        final ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        final NamespacedKey key = new NamespacedKey(AdminAssistant.plugin, "assistant");
        container.set(key, PersistentDataType.STRING, "close");
        itemMeta.displayName(Component.text().content("パネルを閉じる").build());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
