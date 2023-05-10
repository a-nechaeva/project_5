package commands;

import client.Client;
import receiver.Receiver;

public class Clear extends AbstractCommand{
    public Clear(Client client, Receiver receiver) {
        super("clear", client, receiver);
    }

    @Override
    public void execute() {
        receiver.clear();
    }
}
