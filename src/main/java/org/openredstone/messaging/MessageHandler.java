package org.openredstone.messaging;

import org.openredstone.executors.AddItemToInventoryExecutor;
import org.openredstone.executors.KillExecutor;
import org.openredstone.executors.RawExecutor;

public class MessageHandler {

    static KillExecutor killExecutor = new KillExecutor();
    static RawExecutor rawExecutor = new RawExecutor();
    static AddItemToInventoryExecutor addItemToInventoryExecutor = new AddItemToInventoryExecutor();

    public static void execute(Message message) throws Exception {
        switch (message.getAction()) {
            case KILL:
                killExecutor.execute(message);
                return;
            case EXECUTE:
                rawExecutor.execute(message);
                return;
            case ADD_ITEM_TO_INVENTORY:
                addItemToInventoryExecutor.execute(message);
                return;
        }
    }
}
