package commands;

import exceptions.io.WrongArgumentException;
import exceptions.receiver.CollectionKeyException;
import models.MusicGenre;

import java.time.LocalDate;
import receiver.*;
import client.*;

public class Insert extends AbstractCommand{
    private final Long key;
    private final String musicBandName;
    private final Float x;
    private final Double y;
    private final int numberOfParticipants;
    private final Integer singlesCount;
    private final LocalDate establishmentDate;
    private final MusicGenre genre;
    private final String studioName;

    public Insert(Client client, Receiver receiver, Long key, String musicBandName, Float x,
                  Double y, int numberOfParticipants, Integer singlesCount, LocalDate establishmentDate,
                  MusicGenre genre, String studioName) {
        super("insert", client, receiver);
        this.key = key;
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
        receiver.insert(key, musicBandName, x, y, numberOfParticipants, singlesCount,
                establishmentDate, genre, studioName);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
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
