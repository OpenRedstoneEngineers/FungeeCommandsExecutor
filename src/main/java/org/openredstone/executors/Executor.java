package org.openredstone.executors;

import org.openredstone.messaging.Message;

public interface Executor {
    public void execute(Message message) throws Exception;
}
