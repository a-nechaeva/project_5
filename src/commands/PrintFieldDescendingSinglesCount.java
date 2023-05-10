package commands;

import clientSide.Client;
import objects.MusicBand;
import receiver.Receiver;

import java.util.List;

public class PrintFieldDescendingSinglesCount extends AbstractCommandWithResult<List<MusicBand>>{
    private List<MusicBand> result = null;

    public PrintFieldDescendingSinglesCount(Client client, Receiver receiver) {
        super("print_field_descending_oscars_count", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.printFieldDescendingSinglesCount();
    }

    @Override
    public List<MusicBand> getResult() {
        return result;
    }
}
