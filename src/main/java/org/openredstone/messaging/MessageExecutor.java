package org.openredstone.messaging;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.openredstone.FungeeCommandsExecutor;

public class MessageExecutor {
    public static void execute(Message message) throws Exception {
        switch (message.getAction()) {
            case KILL:
                executeKill(message);
                return;
            case EXECUTE:
                executeRaw(message);
                return;
            case ADD_ITEM_TO_INVENTORY:
                executeAddItemToInventory(message);
                return;
        }
    }

    public static void executeKill(Message message) throws Exception {
        Player player = FungeeCommandsExecutor.plugin.getServer().getPlayer(message.getUuid());

        if (player.equals(null)) {
            throw new Exception("Player not found.");
        }

        player.setHealth(0);

    }

    public static void executeRaw(Message message) {
        FungeeCommandsExecutor.plugin.getServer().dispatchCommand(FungeeCommandsExecutor.plugin.getServer().getConsoleSender(), String.join(" ", message.getArguments()));
    }

    public static void executeAddItemToInventory(Message message) throws Exception{
        Player player = FungeeCommandsExecutor.plugin.getServer().getPlayer(message.getUuid());

        if (player.equals(null)) {
            throw new Exception("Player not found.");
        }

        player.getInventory().addItem(new ItemStack(Material.valueOf(message.getArguments()[0])));
    }
}
