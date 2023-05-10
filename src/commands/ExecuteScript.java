package commands;

import client.Client;
import exceptions.io.CustomIOException;
import receiver.Receiver;

public class ExecuteScript extends AbstractCommand{
    private final String path;

    public ExecuteScript(Client client, Receiver receiver, String path) {
        super("execute_script", client, receiver);
        this.path = path;
    }

    @Override
    public void execute() throws CustomIOException {
        client.executeScript(path);
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
