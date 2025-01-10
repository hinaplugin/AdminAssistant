package com.hinaplugin.adminassistant.listener;

import com.hinaplugin.adminassistant.AdminAssistant;
import com.hinaplugin.adminassistant.system.CreatePanel;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event){
        final Player player = (Player) event.getWhoClicked();
        final ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null){
            return;
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null){
            return;
        }

        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        final NamespacedKey key = new NamespacedKey(AdminAssistant.plugin, "assistant");
        final String data = container.get(key, PersistentDataType.STRING);
        if (data == null){
            return;
        }

        event.setResult(Event.Result.DENY);
        event.setCancelled(true);

        switch (data){
            case "skull" -> {
                final Component targetNameComponent = itemMeta.displayName();
                final Player target = AdminAssistant.plugin.getServer().getPlayer(PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(targetNameComponent)));
                if (target == null){
                    player.sendMessage("§c" + targetNameComponent + "が見つかりませんでした．");
                    return;
                }
                final BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.teleport(target);
                    }
                };
                runnable.runTaskLater(AdminAssistant.plugin, 0L);
                player.sendMessage("§a" + target.getName() + "にテレポートしました．");
                player.closeInventory();
            }
            case "next" -> {
                if (!AdminAssistant.warehouse.getInventoryMap().containsKey(player)){
                    return;
                }
                final int page = AdminAssistant.warehouse.getInventoryMap().get(player);
                final List<Player> players = AdminAssistant.warehouse.getPlayerListMap().get(player);
                if (players.size() < 46){
                    return;
                }
                final int next = page + 1;
                player.openInventory(new CreatePanel().create(player, next));
                AdminAssistant.warehouse.getInventoryMap().replace(player, next);
            }
            case "back" -> {
                if (!AdminAssistant.warehouse.getInventoryMap().containsKey(player)){
                    return;
                }
                final int page = AdminAssistant.warehouse.getInventoryMap().get(player);
                if (page == 1){
                    return;
                }
                final int next = page - 1;
                player.openInventory(new CreatePanel().create(player, next));
                AdminAssistant.warehouse.getInventoryMap().replace(player, next);
            }
            case "close" -> {
                if (!AdminAssistant.warehouse.getInventoryMap().containsKey(player)){
                    return;
                }
                player.closeInventory();
            }
        }
    }
}
