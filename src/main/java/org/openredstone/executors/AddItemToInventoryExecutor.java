package org.openredstone.executors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.openredstone.FungeeCommandsExecutor;
import org.openredstone.messaging.Message;

public class AddItemToInventoryExecutor implements Executor {
    @Override
    public void execute(Message message) throws Exception {
        Player player = FungeeCommandsExecutor.plugin.getServer().getPlayer(message.getUuid());

        if (player.equals(null)) {
            throw new Exception("Player not found.");
        }

        player.getInventory().addItem(new ItemStack(Material.valueOf(message.getArguments()[0])));
    }
}
