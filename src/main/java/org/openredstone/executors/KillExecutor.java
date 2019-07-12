package org.openredstone.executors;

import org.bukkit.entity.Player;
import org.openredstone.FungeeCommandsExecutor;
import org.openredstone.messaging.Message;

public class KillExecutor implements Executor {

    @Override
    public void execute(Message message) throws Exception {
        Player player = FungeeCommandsExecutor.plugin.getServer().getPlayer(message.getUuid());

        if (player.equals(null)) {
            throw new Exception("Player not found.");
        }

        player.setHealth(0);
    }
}
