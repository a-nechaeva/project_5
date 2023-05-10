package commands;

import client.Client;
import receiver.Receiver;

public class RemoveGreaterKey extends AbstractCommand{
    private final Long key;

    public RemoveGreaterKey(Client client, Receiver receiver, Long key) {
        super("remove_greater_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() {
        receiver.removeGreaterKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
