package commands;

import clientSide.Client;
import objects.MusicGenre;
import receiver.Receiver;

import java.time.LocalDate;
import exceptions.io.*;

public class RemoveGreater extends AbstractCommand{
    private final String musicBandName;
    private final Float x;
    private final Double y;
    private final int numberOfParticipants;
    private final Integer singlesCount;
    private final LocalDate establishmentDate;
    private final MusicGenre genre;
    private final String studioName;
    public RemoveGreater(Client client, Receiver receiver,String musicBandName, Float x,
                         Double y, int numberOfParticipants, Integer singlesCount, LocalDate establishmentDate,
                         MusicGenre genre, String studioName) {
        super("remove_greater", client, receiver);
        this.musicBandName = musicBandName;
        this.x = x;
        this.y = y;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.studioName = studioName;
    }

    @Override
    public void execute() throws WrongArgumentException {
        receiver.removeGreater( musicBandName, x, y, numberOfParticipants, singlesCount,
                establishmentDate, genre, studioName);
    }

    @Override
    public String toString() {
        return name + " {" +
                ", musicBandName='" + musicBandName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", establishmentDate=" + establishmentDate +
                ", genre='" + genre + '\'' +
                ", studioName=" + studioName +
                '\'' +
                '}';
    }
}
