package commands;

import clientSide.Client;
import receiver.Receiver;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Client client, Receiver receiver) {
        super(name, client, receiver);
    }
}
