package commands;

import clientSide.Client;
import receiver.Receiver;

public class Exit extends AbstractCommand{
    public Exit(Client client, Receiver receiver) {
        super("exit", client, receiver);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
