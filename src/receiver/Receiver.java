package receiver;

import fileUtilities.receiver.*;
import exceptions.io.*;
import exceptions.receiver.*;
import objects.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.List;

/**
 * The Receiver class is responsible for managing the music band collection. It uses MusicBandCollection
 * to store the music bands and MusicBandCollectionFileReader/MusicBandCollectionFileWriter to read/write the
 * music bands from/to the XML file.
 */
public class Receiver {
    private final MusicBandCollection musicBandCollection;
    MusicBandCollectionFileReader xmlFileReader;
    MusicBandCollectionFileWriter xmlFileWriter;

    /**
     * Creates a new Receiver instance and initializes the collection of music bands from a file.
     *
     * @throws InvalidFileDataException if the data in the file is invalid
     * @throws FileNotFoundException    if the file cannot be found
     * @throws FilePermissionException  if the program does not have permission to access the file
     */
    public Receiver() throws InvalidFileDataException, FileNotFoundException, FilePermissionException {
        String path = System.getenv("lab5");
        checkFile(path);

        this.xmlFileReader = new MusicBandCollectionXMLFileReader(path);
        this.xmlFileWriter = new MusicBandCollectionXMLFileWriter(path);

        this.musicBandCollection = xmlFileReader.read();
    }

    /**
     * Returns information about the collection.
     *
     * @return a String containing information about the collection
     */
    public String info() {
        return "*Collection info*\n" +
                "- Type of collection   : Hashtable of MusicBands\n" +
                "- Date of initializing : " + musicBandCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements   : " + musicBandCollection.length();
    }

    /**
     * Returns the Hashtable of music bands in the collection.
     *
     * @return the Hashtable of music bands in the collection
     */
    public Hashtable<Long, MusicBand> show() {
        return musicBandCollection.getMusicBandHashtable();
    }

    /**
     * Inserts a new music band into the collection.
     *
     * @param key the key of the new music band
     * @param musicBandName the name of the new music band
     * @param x the x coordinate of the new music band
     * @param y the y coordinate of the new music band
     * @param numberOfParticipants the number of participants in the new music band
     * @param singlesCount the number of singles of the new music band
     * @param establishmentDate the date when new music band was established
     * @param genre the genre of the new music band
     * @param studioName name of the studio of the new music band
     * @throws CollectionKeyException if the specified key is already in use
     * @throws WrongArgumentException if any of the arguments are invalid
     */
    public void insert(Long key, String musicBandName, Float x, Double y, int numberOfParticipants, Integer singlesCount,
                       LocalDate establishmentDate, MusicGenre genre, String studioName) throws CollectionKeyException, WrongArgumentException {
        if (musicBandCollection.getElementByKey(key) != null)
            throw new CollectionKeyException("key already exists");
        MusicBand musicBand = new MusicBand(musicBandName, new Coordinates(x, y), numberOfParticipants, singlesCount,
                establishmentDate, genre, new Studio(studioName));
        musicBand.setId();
        musicBandCollection.put(key, musicBand);
        System.out.println("*element added successfully*");
    }

    /**
     * Updates the information of the specified music band in the collection.
     *
     * @param id the ID of the music band to be updated
     * @param musicBandName the name of the new music band
     * @param x the x coordinate of the new music band
     * @param y the y coordinate of the new music band
     * @param numberOfParticipants the number of participants in the new music band
     * @param singlesCount the number of singles of the new music band
     * @param establishmentDate the date when new music band was established
     * @param genre the genre of the new music band
     * @param studioName name of the studio of the new music band
     * @throws CollectionKeyException if the specified ID is not found in the collection
     * @throws WrongArgumentException if any of the arguments are invalid
     */
    public void update(Long id, String musicBandName, Float x, Double y, int numberOfParticipants, Integer singlesCount,
                       LocalDate establishmentDate, MusicGenre genre, String studioName) throws CollectionKeyException, WrongArgumentException {
        MusicBand musicBand = musicBandCollection.getElementByID(id);
        if (musicBand == null)
            throw new CollectionKeyException("id does not exist");
        musicBand.setName(musicBandName);
        musicBand.setCoordinates(new Coordinates(x, y));
        musicBand.setNumberOfParticipants(numberOfParticipants);
        musicBand.setSinglesCount(singlesCount);
        musicBand.setEstablishmentDate(establishmentDate);
        musicBand.setGenre(genre);
        musicBand.setStudio(new Studio(studioName));
        System.out.println("*element updated successfully*");
    }

    /**
     * Removes the element with the specified key from the music band collection.
     *
     * @param key the key of the element to remove
     * @throws CollectionKeyException if the specified key does not exist in the collection
     */
    public void removeKey(Long key) throws CollectionKeyException {
        if (musicBandCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        musicBandCollection.remove(key);
        System.out.println("*element removed successfully*");
    }

    /**
     Clears the music band collection.
     */
    public void clear() {
        musicBandCollection.clear();
        System.out.println("*collection cleared successfully*");
    }

    /**
     Saves the music band collection to an XML file.
     */
    public void save() {
        try {
            xmlFileWriter.write(musicBandCollection);
            System.out.println("*collection saved successfully*");
        } catch (FileNotFoundException | FilePermissionException | CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     Removes all elements that are greater than the specified music band from the collection.

     @param musicBandName the name of the new music band to compare
     @param x the x coordinate of the new music band to compare
     @param y the y coordinate of the new music band to compare
     @param numberOfParticipants the number of participants in the new music band to compare
     @param singlesCount the number of singles of the new music band to compare
     @param establishmentDate the date when new music band was established to compare
     @param genre the genre of the new music band to compare
     @param studioName name of the studio of the new music band to compare
     @throws WrongArgumentException if any of the specified arguments are invalid
     */
    public void removeGreater(String musicBandName, Float x, Double y, int numberOfParticipants, Integer singlesCount,
                              LocalDate establishmentDate, MusicGenre genre, String studioName) throws WrongArgumentException {
        MusicBand musicBand = new MusicBand(musicBandName, new Coordinates(x, y), numberOfParticipants, singlesCount,
                establishmentDate, genre, new Studio(studioName));
        int count = musicBandCollection.removeGreater(musicBand);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }

    /**
     Removes all elements that are lower than the specified music band from the collection.

     @param musicBandName the name of the new music band to compare
     @param x the x coordinate of the new music band to compare
     @param y the y coordinate of the new music band to compare
     @param numberOfParticipants the number of participants in the new music band to compare
     @param singlesCount the number of singles of the new music band to compare
     @param establishmentDate the date when new music band was established to compare
     @param genre the genre of the new music band to compare
     @param studioName name of the studio of the new music band to compare
     @throws WrongArgumentException if any of the specified arguments are invalid
     */
    public void removeLower(String musicBandName, Float x, Double y, int numberOfParticipants, Integer singlesCount,
                              LocalDate establishmentDate, MusicGenre genre, String studioName) throws WrongArgumentException {
        MusicBand musicBand = new MusicBand(musicBandName, new Coordinates(x, y), numberOfParticipants, singlesCount,
                establishmentDate, genre, new Studio(studioName));
        int count = musicBandCollection.removeLower(musicBand);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }


    /**
     Removes all elements with keys greater than the specified key from the music band collection.

     @param key the key to compare with
     */

    public void removeGreaterKey(Long key) {
        int count = musicBandCollection.removeGreaterKey(key);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }

    /**
     Returns a list of music bands in descending order based on their singles count field in the music band collection.

     @return a list of music bands in descending order based on their singles count field
     */
    public List<MusicBand> printFieldDescendingSinglesCount() {
        return musicBandCollection.printFieldDescendingSinglesCount();
    }

    /**
     * Returns a list of values name contained in this collection
     * and the number of elements in the collection corresponding to each name.
     *
     * @return a table of values name contained in this collection
     * and the number of elements in the collection corresponding to each name.
     */
    public Hashtable<String, Integer> groupCountingByName() {
        return musicBandCollection.groupCountingByName();
    }
    /**
     * Return the number of elements in the collection, the number of participants
     * of which is equal to the specified.
     *
     * @param numberOfParticipants the number of participants
     * @return the number of elements in the collection, the number of participants
     * of which is equal to the specified.
     */
    public int countByNumberOfParticipants(int numberOfParticipants) {
       return musicBandCollection.countByNumberOfParticipants(numberOfParticipants);
    }


    /**
     Checks if the file at the specified path exists and can be read.

     @param path the path of the file to check
     @throws FileNotFoundException if the file does not exist
     @throws FilePermissionException if the file cannot be read
     */
    private void checkFile(String path) throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read and/or write permission for file " + path + "  !");
    }
}
