package receiver;

import models.MusicBand;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * The MusicBandCollection class represents a collection of music bands stored in a Hashtable with Long keys.
 * It provides methods for adding, removing, and retrieving music bands from the collection.
 */
public class MusicBandCollection {
    private Hashtable<Long, MusicBand> musicBandHashtable = new Hashtable<>();
    private java.time.ZonedDateTime creationDate;

    /**
     * Constructs an empty MusicBandCollection object and sets the creation date to the current time.
     */
    public MusicBandCollection() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Adds a music band to the collection with the specified key.
     *
     * @param key the key to associate with the music band
     * @param musicBand the music band to add to the collection
     */
    public void put(Long key, MusicBand musicBand) {
        musicBandHashtable.put(key, musicBand);
    }

    /**
     * Returns the music band associated with the specified key, or null if the key is not present in the collection.
     *
     * @param key the key of the music band to retrieve
     * @return the movie associated with the key, or null if the key is not present in the collection
     */
    public MusicBand getElementByKey(Long key) {
        return musicBandHashtable.get(key);
    }

    /**
     * Returns the music band with the specified ID, or null if no music band with the ID is present in the collection.
     *
     * @param id the ID of the music band to retrieve
     * @return the music band with the specified ID, or null if no movie with the ID is present in the collection
     */
    public MusicBand getElementByID(Long id) {
        for (MusicBand musicBand : musicBandHashtable.values()) {
            if (Objects.equals(musicBand.getId(), id))
                return musicBand;
        }
        return null;
    }

    /**
     * Removes the music band associated with the specified key from the collection.
     *
     * @param key the key of the music band to remove
     */
    public void remove(Long key) {
        musicBandHashtable.remove(key);
    }

    /**
     * Removes all music bands from the collection.
     */
    public void clear() {
        musicBandHashtable.clear();
    }

    /**
     * Returns the Hashtable containing the music bands in the collection.
     *
     * @return the Hashtable containing the music bands in the collection
     */
    public Hashtable<Long, MusicBand> getMusicBandHashtable() {
        return musicBandHashtable;
    }

    /**
     * Returns the number of music bands in the collection.
     *
     * @return the number of music bands in the collection
     */
    public int length() {
        return musicBandHashtable.size();
    }

    /**
     * Returns the date and time the collection was created.
     *
     * @return the date and time the collection was created
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the date and time the collection was created.
     *
     * @param creationDate the new creation date and time
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Removes all music bands with a rating greater than the specified music band from the collection.
     *
     * @param musicBand the music band to compare against
     * @return the number of music bands removed from the collection
     */
    public int removeGreater(MusicBand musicBand) {
        Hashtable<Long, MusicBand> newMusicBandHashtable = new Hashtable<>(musicBandHashtable);
        int count = 0;
        for (Long key : musicBandHashtable.keySet()) {
            if (getElementByKey(key).compareTo(musicBand) > 0) {
                newMusicBandHashtable.remove(key);
                count += 1;
            }
        }
        musicBandHashtable = newMusicBandHashtable;
        return count;
    }

    /**
     * Removes all music bands with a rating lower than the specified music band from the collection.
     *
     * @param musicBand the music band to compare against
     * @return the number of music bands removed from the collection
     */
    public int removeLower(MusicBand musicBand) {
        Hashtable<Long, MusicBand> newMusicBandHashtable = new Hashtable<>(musicBandHashtable);
        int count = 0;
        for (Long key : musicBandHashtable.keySet()) {
            if (getElementByKey(key).compareTo(musicBand) < 0) {
                newMusicBandHashtable.remove(key);
                count += 1;
            }
        }
        musicBandHashtable = newMusicBandHashtable;
        return count;
    }

    /**
     * Removes all the entries with keys greater than the specified key from this table.
     *
     * @param key the key that serves as the greater bound for keys to be removed
     * @return the number of entries removed from the table
     */
    public int removeGreaterKey(Long key) {
        Hashtable<Long, MusicBand> newMusicBandHashtable = new Hashtable<>(musicBandHashtable);
        int count = 0;
        for (Long i : musicBandHashtable.keySet()) {
            if (i > key) {
                newMusicBandHashtable.remove(i);
                count += 1;
            }
        }
        musicBandHashtable = newMusicBandHashtable;
        return count;
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
        int count = 0;
        for (Long i : musicBandHashtable.keySet()) {
            if (musicBandHashtable.get(i).getNumberOfParticipants() == numberOfParticipants){
                count += 1;
            }
        }
        return count;
    }

    /**
     * Returns a table of values name contained in this collection
     * and the number of elements in the collection corresponding to each name.
     *
     * @return a table of values name contained in this collection
     * and the number of elements in the collection corresponding to each name.
     */
    public Hashtable<String, Integer> groupCountingByName() {
        Hashtable<String, Integer> musicBandNameList = new Hashtable<>();
        for (Long key : musicBandHashtable.keySet()) {
            if (musicBandNameList.containsKey(musicBandHashtable.get(key).getName())) {
                int el = musicBandNameList.get(musicBandHashtable.get(key).getName());
                el++;
                musicBandNameList.put(musicBandHashtable.get(key).getName(), el);
            } else {
                musicBandNameList.put(musicBandHashtable.get(key).getName(), 1);
            }
        }
        return musicBandNameList;
    }


    /**
     * Returns a list of the values contained in this table, sorted in descending
     * order based on the number of singles.
     *
     * @return a list of the values contained in this table, sorted in descending
     * order based on the number of singles.
     */
    public List<MusicBand> printFieldDescendingSinglesCount() {
        List<MusicBand> musicBandList = new ArrayList<>(musicBandHashtable.values());
        musicBandList.sort(new Comparator<>() {
            public int compare(MusicBand o1, MusicBand o2) {
                if((o1.getSinglesCount() != null) && (o2.getSinglesCount() != null)) {
                    return (int) (o2.getSinglesCount() - o1.getSinglesCount());
                } else {
                    if ((o1.getSinglesCount() != null) && (o2.getSinglesCount() == null)) {
                        return -1;
                    }
                    if ((o1.getSinglesCount() == null) && (o2.getSinglesCount() != null)) {
                        return 1;
                    }
                    if((o1.getSinglesCount() == null) && (o2.getSinglesCount() == null)) {
                        return 0;
                    }
                }
                return 0;
            }
        });
        return musicBandList;
    }

}
