package org.openredstone.executors;

import org.openredstone.FungeeCommandsExecutor;
import org.openredstone.messaging.Message;

public class RawExecutor implements Executor {
    @Override
    public void execute(Message message) {
        FungeeCommandsExecutor.plugin.getServer().dispatchCommand(FungeeCommandsExecutor.plugin.getServer().getConsoleSender(), String.join(" ", message.getArguments()));
    }
}
