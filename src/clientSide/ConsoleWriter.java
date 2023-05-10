package clientSide;

import objects.MusicBand;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

/**
 * The PrettyPrinter class contains methods for printing movie data in a user-friendly way.
 */
public class ConsoleWriter {
    static final String dateTimeFormat = "dd.MM.yy HH:mm";
    static final String dateOnlyFormat = "dd.MM.yy";
    static final String keyFormat = "%5s |";
    static final String musicBandIDFormat = "%5s |";
    static final String musicBandNameFormat = "%15s |";
    static final String XFormat = "%7s |";
    static final String YFormat = "%7s |";
    static final String dateFormat = "%15s |";
    static final String numberOfParticipantsFormat = "%20s |";
    static final String genreFormat = "%9s |";
    static final String singlesCountFormat = "%15s |";
    static final String studioNameFormat = "%15s |";

    /**
     * Prints the music band data stored in a HashMap in a user-friendly way.
     *
     * @param hashTable the Hashtable containing the music band data
     */
    static void printMusicBandHashtable(Hashtable<Long, MusicBand> hashTable) {
        System.out.printf(keyFormat, "Key");
        printMusicBandParamNames();
        System.out.println("-".repeat(165));

        for (Long key : hashTable.keySet()) {
            MusicBand musicBand = hashTable.get(key);
            System.out.printf(keyFormat, key);
            printMusicBandParams(musicBand);
        }
    }
    /**
     * Prints the name of music band and the number of elements with this one.
     *
     * @param nameAndCount the HashMap containing the name of music bands and the number
     * of bands with this name.
     */
    static void printGroupCountingByName(Hashtable<String, Integer> nameAndCount) {

        for (String key : nameAndCount.keySet()) {
            System.out.println(key + " : " + nameAndCount.get(key).toString());
        }
    }

    /**
     * Prints the music band data stored in a List in a user-friendly way.
     *
     * @param musicBandList the List containing the music band data
     */
    public static void printMusicBandList(List<MusicBand> musicBandList) {
        printMusicBandParamNames();
        System.out.println("-".repeat(158));

        for (MusicBand musicBand : musicBandList) {
            printMusicBandParams(musicBand);
        }
    }

    /**
     * Prints the music band data stored in a List in a user-friendly way,
     * including the number Of singles each music band has won.
     *
     * @param musicBandList the List containing the music band data
     */
    public static void printMusicBandListSingles(List<MusicBand> musicBandList) {
        System.out.printf(musicBandIDFormat, "ID");
        System.out.printf(musicBandNameFormat, "musicBandName");
        System.out.printf(singlesCountFormat, "singles count");
        System.out.println();
        System.out.println("-".repeat(33));

        for (MusicBand musicBand : musicBandList) {
            System.out.printf(musicBandIDFormat, musicBand.getId());
            System.out.printf(musicBandNameFormat, musicBand.getName());
            System.out.printf(singlesCountFormat, musicBand.getSinglesCount());
            System.out.println();
        }
    }


    private static void printMusicBandParamNames() {
        System.out.printf(musicBandIDFormat, "ID");
        System.out.printf(musicBandNameFormat, "musicBandName");
        System.out.printf(XFormat, "X");
        System.out.printf(YFormat, "Y");
        System.out.printf(dateFormat, "creationDate");
        System.out.printf(numberOfParticipantsFormat, "number of participants");
        System.out.printf(singlesCountFormat, "singles count");
        System.out.printf(dateFormat, "establishmentDate");
        System.out.printf(genreFormat, "genre");
        System.out.printf(studioNameFormat, "studio name");
        System.out.println();
    }

    private static void printMusicBandParams(MusicBand musicBand) {
        System.out.printf(musicBandIDFormat, musicBand.getId());
        System.out.printf(musicBandNameFormat, musicBand.getName());
        System.out.printf(XFormat, musicBand.getCoordinates().getX());
        System.out.printf(YFormat, musicBand.getCoordinates().getY());
        System.out.printf(dateFormat, musicBand.getCreationDate().format(DateTimeFormatter.ofPattern(dateTimeFormat)));
        System.out.printf(numberOfParticipantsFormat, musicBand.getNumberOfParticipants());
        System.out.printf(singlesCountFormat, musicBand.getSinglesCount());
        if (musicBand.getEstablishmentDate() != null) {
            System.out.printf(dateFormat, musicBand.getEstablishmentDate().format(DateTimeFormatter.ofPattern(dateOnlyFormat)));
        } else {
            System.out.printf(dateFormat, (Object) null);
        }
        System.out.printf(genreFormat, musicBand.getGenre());
        System.out.printf(studioNameFormat, musicBand.getStudio().getName());
        System.out.println();
    }
}
