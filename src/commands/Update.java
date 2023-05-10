package commands;

import exceptions.io.WrongArgumentException;
import exceptions.receiver.CollectionKeyException;
import models.MusicGenre;

import java.time.LocalDate;
import client.*;
import receiver.*;

public class Update extends AbstractCommand{

    private final Long id;
    private final String musicBandName;
    private final Float x;
    private final Double y;
    private final int numberOfParticipants;
    private final Integer singlesCount;
    private final LocalDate establishmentDate;
    private final MusicGenre genre;
    private final String studioName;

    public Update(Client client, Receiver receiver, Long id, String musicBandName, Float x,
                  Double y, int numberOfParticipants, Integer singlesCount, LocalDate establishmentDate,
                  MusicGenre genre, String studioName) {
        super("update", client, receiver);
        this.id = id;
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
    public void execute() throws CollectionKeyException, WrongArgumentException {
        receiver.update(id, musicBandName, x, y, numberOfParticipants, singlesCount,
                establishmentDate, genre, studioName);
    }

    @Override
    public String toString() {
        return name + " {" +
                "id=" + id +
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
