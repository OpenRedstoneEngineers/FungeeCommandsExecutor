package org.openredstone.messaging;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Message {

    Action action;
    UUID uuid;
    List<String> arguments;

    public Message(Action action, UUID uuid, List<String> values) {
        this.action = action;
        this.uuid = uuid;
        this.arguments = values;
    }

    public Message(String serializedMessage) throws Exception {
        List<String> raw = Arrays.asList(serializedMessage.split(":"));

        if (raw.size()<2) {
            throw new Exception("Not enough arguments provided in serialized message.");
        }

        this.action = parseAction(raw);

        if (this.action == null) {
            throw new Exception("Invalid action.");
        }

        this.uuid = parseUniqueId(raw);

        if (this.uuid == null) {
            throw new Exception("Invalid UUID.");
        }

        raw.remove(0);
        raw.remove(1);

        this.arguments = raw;

    }

    public String getSerializedMessage() {
        String actionString = action.name();
        String uniqueId = uuid.toString();
        String arguments = String.join(":", this.arguments);
        return actionString + ":" + uniqueId + ":" + arguments;
    }

    public static Action parseAction(List<String> values) {
        return Action.valueOf(values.get(0));
    }

    public static UUID parseUniqueId(List<String> values) {
        return UUID.fromString(values.get(1));
    }

    public Action getAction() {
        return action;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
