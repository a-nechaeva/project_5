package commands;

import clientSide.Client;
import receiver.Receiver;

import java.util.Hashtable;

public class GroupCountingByName extends AbstractCommandWithResult<Hashtable<String, Integer>>{
    private Hashtable<String, Integer> result = null;

    public GroupCountingByName(Client client, Receiver receiver) {
        super("group_counting_by_name", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.groupCountingByName();
    }

    @Override
    public Hashtable<String, Integer> getResult() {
        return result;
    }
}
