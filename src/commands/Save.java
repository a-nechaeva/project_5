package commands;

import clientSide.Client;
import receiver.Receiver;

public class Save extends AbstractCommand{
    public Save(Client client, Receiver receiver) {
        super("save", client, receiver);
    }

    @Override
    public void execute() {
        receiver.save();
    }
}
