package commands;

import clientSide.Client;
import exceptions.io.WrongArgumentException;
import receiver.Receiver;

public class CountByNumberOfParticipants extends AbstractCommand {
    private final int numberOfParticipants;
    public CountByNumberOfParticipants(Client client, Receiver receiver, Integer numberOfParticipants) {
        super("count_by_number_of_participants", client, receiver);
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public void execute() throws WrongArgumentException {
        receiver.countByNumberOfParticipants(numberOfParticipants);
    }
    @Override
    public String toString() {
        return name + " {" +
                "number_of_participants=" + numberOfParticipants +
                '}';
    }
}
