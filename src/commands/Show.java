package commands;

import clientSide.Client;
import objects.MusicBand;
import receiver.Receiver;

import java.util.Hashtable;

public class Show extends AbstractCommandWithResult<Hashtable<Long, MusicBand>>{
    private Hashtable<Long, MusicBand> result = null;

    public Show(Client client, Receiver receiver) {
        super("show", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.show();
    }

    @Override
    public Hashtable<Long, MusicBand> getResult() {
        return result;
    }
}
