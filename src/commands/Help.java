package commands;

import clientSide.Client;
import receiver.Receiver;

public class Help extends AbstractCommand{
    public Help(Client client, Receiver receiver) {
        super("help", client, receiver);
    }

    @Override
    public void execute() {
        client.help();
    }
}
