package commands;

import clientSide.Client;
import exceptions.receiver.CollectionKeyException;
import receiver.Receiver;

public class RemoveKey extends AbstractCommand{
    private final Long key;

    public RemoveKey(Client client, Receiver receiver, Long key) {
        super("remove_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
        receiver.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
