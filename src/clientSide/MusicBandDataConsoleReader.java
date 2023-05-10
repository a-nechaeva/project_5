package clientSide;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exceptions.io.*;
import exceptions.client.*;
import objects.MusicGenre;
import objects.checkers.*;
import fileUtilities.client.*;

import static objects.checkers.MusicBandArgumentChecker.checkX;
import static objects.checkers.MusicBandArgumentChecker.checkY;

/**
 The MusicBandDataConsoleReader class provides static methods for reading music band data from console input.
 The class provides methods for reading music band name, coordinates, numberOfParticipants, singlesCount,
 establishmentDate, genre and studio name from console input.
 */
public class MusicBandDataConsoleReader {
    /**
     * Reads the music band name from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The movie name entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static String readMusicBandName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String musicBandName = null;
        while (musicBandName == null) {
            try {
                String input = basicReader.readLine("Enter music band name");
                musicBandName = input.trim();
                MusicBandArgumentChecker.checkName(musicBandName);
            } catch (WrongArgumentException e) {
                musicBandName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return musicBandName;
    }

    /**
     * Reads the X coordinate from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The X coordinate entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Float readX(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Float x = null;
        boolean xSuccess = false;
        while (!xSuccess) {
            try {
                String input = basicReader.readLine("Enter X coordinate");
                String xInput = input.trim();
                x = Float.parseFloat(xInput);
                checkX(x);
                xSuccess = true;
            } catch (NumberFormatException | WrongArgumentException e) {
                String errorMessage = "! x must be Float and greater -97 !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return x;
    }

    /**
     * Reads the Y coordinate from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The Y coordinate entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Double readY(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Double y = null;
        boolean ySuccess = false;
        while (!ySuccess) {
            try {
                String input = basicReader.readLine("Enter Y coordinate");
                String yInput = input.trim();
                y = Double.parseDouble(yInput);
                checkY(y);
                ySuccess = true;
            } catch (NumberFormatException | WrongArgumentException e) {
                String errorMessage = "! y must be Double and greater -480 !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return y;
    }

    /**
     * Reads the numberOfParticipants from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The numberOfParticipants entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static int readNumberOfParticipants(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        int numberOfParticipants = 0;
        boolean numberOfParticipantsSuccess = false;
        while (!numberOfParticipantsSuccess) {
            try {
                String input = basicReader.readLine("Enter number of participants");
                String numberOfParticipantsInput = input.trim();
                numberOfParticipants = Integer.parseInt(numberOfParticipantsInput);
                MusicBandArgumentChecker.checkNumberOfParticipants(numberOfParticipants);
                numberOfParticipantsSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                String errorMessage = e.getMessage();
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return numberOfParticipants;
    }

    /**
     * Reads the singlesCount from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The numberOfParticipants entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static Integer readSinglesCount(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer singlesCount = null;
        boolean singlesCountSuccess = false;
        while (!singlesCountSuccess) {
            String input = basicReader.readLine("Enter singles count or leave this string empty");
            if (input.equals("")) {
                singlesCountSuccess = true;
            } else {
                try {
                    String singlesCountInput = input.trim();
                    singlesCount = Integer.parseInt(singlesCountInput);
                    MusicBandArgumentChecker.checkSinglesCount(singlesCount);
                    singlesCountSuccess = true;

                } catch (NumberFormatException e) {
                    String errorMessage = "! not an Integer !";
                    if (inScriptMode) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                } catch (WrongArgumentException e) {
                    String errorMessage = e.getMessage();
                    if (inScriptMode) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
        }
        return singlesCount;
    }

    /**
     * Reads the establishmentDate from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The numberOfParticipants entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static LocalDate readEstablishmentDate(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        LocalDate establishmentDate = null;
        boolean establishmentDateSuccess = false;
       // String input = basicReader.readLine("Enter establishment date in DD.MM.YYYY format or leave this string empty");

            while (!establishmentDateSuccess) {
                String input = basicReader.readLine("Enter establishment date in DD.MM.YYYY format or leave this string empty");
                if (input.equals("")){
                    establishmentDateSuccess = true;
                } else {
                    try {
                        String establishmentDateInput = input.trim();
                        //establishmentDate = LocalDate.from(LocalDate.parse(establishmentDateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay());
                        establishmentDate = LocalDate.parse(establishmentDateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        establishmentDateSuccess = true;
                    } catch (DateTimeParseException e) {
                        String errorMessage = "! wrong date format !";
                        if (inScriptMode) {
                            throw new InvalidScriptException(errorMessage);
                        } else {
                            System.out.println(errorMessage);
                        }
                    }
                }
            }
        return establishmentDate;
    }
    /**
     * Reads the music genre from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The movie genre entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static MusicGenre readMusicGenre(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MusicGenre musicGenre = null;
        boolean musicGenreSuccess = false;

        StringBuilder message = new StringBuilder("Enter music genre (");
        for (MusicGenre genre : MusicGenre.values()) {
            message.append(genre.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(") or leave this string empty");

        //String input = basicReader.readLine(String.valueOf(message));

        while (!musicGenreSuccess) {
            String input = basicReader.readLine(String.valueOf(message));
            if (input.equals("")) {
                musicGenreSuccess = true;
            } else {
                String musicGenreInput = input.trim();
                try {
                    musicGenre = MusicGenre.valueOf(musicGenreInput.toUpperCase());
                    musicGenreSuccess = true;
                } catch (IllegalArgumentException e) {
                    String errorMessage = "! wrong music genre !";
                    if (inScriptMode) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
        }

        return musicGenre;
    }


    /**
     * Reads the studio name from console input using a BasicReader instance.
     *
     * @param basicReader The BasicReader instance used for reading input from the console.
     * @param inScriptMode Boolean flag indicating whether the method is running in script mode.
     * @return The director name entered by the user.
     * @throws InvalidScriptException If the method is running in script mode and the input is invalid.
     */
    public static String readStudioName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String studioName = null;
        boolean studioNameSuccess = false;
        //String input = basicReader.readLine("Enter studio name or leave this string empty");

            while (!studioNameSuccess) {
                String input = basicReader.readLine("Enter studio name or leave this string empty");
                if (input.equals("")) {
                    studioNameSuccess = true;
                } else {
                try {
                    studioName = input.trim();
                    MusicBandArgumentChecker.checkName(studioName);
                    studioNameSuccess = true;
                } catch (WrongArgumentException e) {
                    studioName = null;
                    if (inScriptMode) {
                        throw new InvalidScriptException(e.getMessage());
                    } else {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return studioName;
    }

}
