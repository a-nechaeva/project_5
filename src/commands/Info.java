package commands;

import clientSide.Client;
import receiver.Receiver;

public class Info extends AbstractCommandWithResult<String>{
    private String result = null;

    public Info(Client client, Receiver receiver) {
        super("info", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
