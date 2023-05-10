package models;

import exceptions.io.WrongArgumentException;
import receiver.MusicBandCollection;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static models.helpers.MusicBandArgumentChecker.*;

public class MusicBand implements Comparable<MusicBand>{
    private static Long nextID = 1L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private Integer singlesCount; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.LocalDate establishmentDate; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Studio studio; //Поле может быть null

    public MusicBand(Long id, String name, Coordinates coordinates, int numberOfParticipants, Integer singlesCount,
                     java.time.LocalDate establishmentDate, MusicGenre genre, Studio studio) throws WrongArgumentException {
        checkArguments(id, name, coordinates, numberOfParticipants, singlesCount, establishmentDate, genre, studio);

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.studio = studio;
        this.creationDate = ZonedDateTime.now();
    }


    public MusicBand(String name, Coordinates coordinates, int numberOfParticipants, Integer singlesCount,
                     java.time.LocalDate establishmentDate, MusicGenre genre, Studio studio) throws WrongArgumentException{
        checkArguments(name, coordinates, numberOfParticipants, singlesCount, establishmentDate, genre, studio);
        this.name = name;
        this.coordinates = coordinates;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.studio = studio;
        this.creationDate = ZonedDateTime.now();
    }
    public static void updateNextId(MusicBandCollection musicBandCollection) {
        Long maxID = musicBandCollection
                .getMusicBandHashtable().values().
                stream().filter(Objects::nonNull)
                .map(MusicBand::getId)
                .mapToLong(Long::longValue).max().orElse(0);
        nextID = maxID + 1;
    }

    public String getName() { return name; }

    public void setName(String name) throws WrongArgumentException {
        checkName(name);
        this.name = name;
    }
    public Long getId(){return id;}
    public void setId(){
         this.id = nextID;
         nextID += 1;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkCoordinates(coordinates);
        this.coordinates = coordinates;

    }
    public java.time.ZonedDateTime getCreationDate() {return creationDate;}

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfParticipants() {return numberOfParticipants;}
    public void setNumberOfParticipants(int numberOfParticipants) throws WrongArgumentException{
        checkNumberOfParticipants(numberOfParticipants);
        this.numberOfParticipants = numberOfParticipants;    }

    public Integer getSinglesCount() {return singlesCount;}
    public void setSinglesCount(Integer singlesCount) throws WrongArgumentException {
        checkSinglesCount(singlesCount);
        this.singlesCount = singlesCount;
    }

    public java.time.LocalDate getEstablishmentDate() {return establishmentDate;}
    public void setEstablishmentDate(java.time.LocalDate establishmentDate) throws WrongArgumentException {
       // checkEstablishmentDate(establishmentDate);
        this.establishmentDate = establishmentDate;
    }

    public MusicGenre getGenre() {return genre;}
    public void setGenre(MusicGenre genre) throws WrongArgumentException {
      //  checkGenre(genre);
        this.genre = genre; }

    public Studio getStudio() {return studio;}
    public void setStudio(Studio studio) throws WrongArgumentException{
       // checkStudio(studio);
        this.studio = studio;
    }

    @Override
    public String toString() {

        return "id = " + this.id +
                ", name = " + this.name +
                ", coordinates = " + this.coordinates +
                ", creationDate = " + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
                ", number of participants = " + this.numberOfParticipants +
                ", singles = " + this.singlesCount +
                ", establishment date = " + this.establishmentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                ", genre = " + this.genre +
                ", studio = " + this.studio;
    }

    @Override
    public int compareTo(MusicBand other) {
        int nameCompare = this.name.toLowerCase().compareTo(other.name.toLowerCase());
        if (nameCompare == 0)
            return (int) (this.getNumberOfParticipants() - other.getNumberOfParticipants());
        return this.name.toLowerCase().compareTo(other.name.toLowerCase());
    }
}
