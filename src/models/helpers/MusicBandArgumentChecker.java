package models.helpers;

import exceptions.io.WrongArgumentException;
import models.Coordinates;
import models.MusicGenre;
import models.Studio;

import java.util.Objects;

public class MusicBandArgumentChecker extends ArgumentChecker {
    public static void checkArguments(String name, Coordinates coordinates, int numberOfParticipants, Integer singlesCount,
                                      java.time.LocalDate establishmentDate, MusicGenre genre, Studio studio) throws WrongArgumentException {
        MusicBandArgumentChecker.checkName(name);
        MusicBandArgumentChecker.checkCoordinates(coordinates);
        MusicBandArgumentChecker.checkNumberOfParticipants(numberOfParticipants);
        MusicBandArgumentChecker.checkSinglesCount(singlesCount);
       // MusicBandArgumentChecker.checkEstablishmentDate(establishmentDate);
       // MusicBandArgumentChecker.checkGenre(genre);
      //  MusicBandArgumentChecker.checkStudio(studio);
    }

    public static void checkArguments(Long id, String name, Coordinates coordinates, int numberOfParticipants, Integer singlesCount,
                                      java.time.LocalDate establishmentDate, MusicGenre genre, Studio studio) throws WrongArgumentException {
        MusicBandArgumentChecker.checkId(id);
        MusicBandArgumentChecker.checkName(name);
        MusicBandArgumentChecker.checkCoordinates(coordinates);
        MusicBandArgumentChecker.checkNumberOfParticipants(numberOfParticipants);
        MusicBandArgumentChecker.checkSinglesCount(singlesCount);
       // MusicBandArgumentChecker.checkEstablishmentDate(establishmentDate);
       // MusicBandArgumentChecker.checkGenre(genre);
       // MusicBandArgumentChecker.checkStudio(studio);
    }

    public static void checkId(Long id) throws WrongArgumentException {
        checkNull(id, "id");
        checkArgument(id > 0, "argument id cannot be <= 0");
    }

    public static void checkKey(Long key) throws WrongArgumentException {
        checkNull(key, "key");
        checkArgument(key > 0, "argument key cannot be <= 0");
    }

    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!Objects.equals(name, ""), "argument name can't be empty");
    }

    public static void checkCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkNull(coordinates, "coordinates");
        checkArgument((coordinates.getX() > -97), "X must be greater -97");
        checkArgument((coordinates.getY() > -480), "Y must be greater -480");
    }
    public static void checkX(Float x) throws WrongArgumentException {
        checkArgument((x > -97), "X must be greater -97");
    }
    public static void checkY(Double y) throws WrongArgumentException {
        checkArgument((y > -480), "Y must be greater -480");
    }

    public static void checkNumberOfParticipants(int numberOfParticipants) throws WrongArgumentException {
        checkArgument(numberOfParticipants > 0, "argument numberOfParticipants can't be <= 0");
    }
    public static void checkSinglesCount(Integer singlesCount) throws WrongArgumentException {
        checkArgument((singlesCount == null) || (singlesCount > 0), "argument singlesCount can't be <= 0");
    }

   /* public static void checkEstablishmentDate(java.time.LocalDate genre) throws WrongArgumentException {
        checkArgument();
    }



    public static void checkGenre(MusicGenre genre) throws WrongArgumentException {
        checkNull(mpaaRating, "mpaaRating");
    }

    public static void checkDirector(Person director) throws WrongArgumentException {
        checkNull(director, "director");
    }

    */
}
