package IOHandlers.receiver;

import exceptions.io.*;
import models.Coordinates;
import models.MusicBand;
import models.MusicGenre;
import models.Studio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import receiver.MusicBandCollection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MusicBandCollectionXMLFileReader implements MusicBandCollectionFileReader{
    private final String path;

    public MusicBandCollectionXMLFileReader(String path) {
        this.path = path;
    }

    @Override
    public MusicBandCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException {
        checkFile();
        String attributeName = null;
        int i = -1;
        try {
            MusicBandCollection musicBandCollection = new MusicBandCollection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Empty file
            File file = new File(path);
            if (file.length() == 0) {
                System.out.println("Created empty music band collection.");
                musicBandCollection.setCreationDate(ZonedDateTime.now());
                return musicBandCollection;
            }

            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            attributeName = "collectionCreationDate";
            String collectionCreationDateInput = document.getDocumentElement().getAttribute(attributeName);
            ZonedDateTime collectionCreationDate = ZonedDateTime.parse(collectionCreationDateInput);

            attributeName = "musicBand";
            NodeList musicBandElements = document.getDocumentElement().getElementsByTagName(attributeName);
            for (i = 0; i < musicBandElements.getLength(); i++) {
                Element musicBandElement = (Element) musicBandElements.item(i);

                attributeName = "id";
                String idInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Long id = Long.parseLong(idInput);

                attributeName = "musicBandName";
                String musicBandName = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();

                attributeName = "coordinates";
                Element coordinatesInput = (Element) musicBandElement.getElementsByTagName(attributeName).item(0);
                if (coordinatesInput == null)
                    throw new NullPointerException();
                attributeName = "x";
                String xInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Float x = Float.parseFloat(xInput);
                attributeName = "y";
                String yInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Double y = Double.parseDouble(yInput);
                Coordinates coordinates = new Coordinates(x, y);

                attributeName = "musicBandCreationDate";
                String creationDateInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                ZonedDateTime creationDate = ZonedDateTime.parse(creationDateInput);

                attributeName = "numberOfParticipants";
                String numberOfParticipantsInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int numberOfParticipants = Integer.parseInt(numberOfParticipantsInput);

                attributeName = "singlesCount";
                String singlesCountInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Integer singlesCount = null;
                if (!singlesCountInput.equals("")) {
                    singlesCount = Integer.parseInt(singlesCountInput);
                }

                attributeName = "establishmentDate";
                String establishmentDateInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                LocalDate establishmentDate = null;
                if (!establishmentDateInput.equals("")) {
                    establishmentDate = LocalDate.parse(establishmentDateInput);
                }

                attributeName = "genre";
                String genreInput = musicBandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                MusicGenre genre = null;
                if (!genreInput.equals("")) {
                    try {
                        genre = MusicGenre.valueOf(genreInput);
                    } catch (IllegalArgumentException e) {
                        throw new WrongArgumentException("wrong music genre");
                    }
                }

                attributeName = "studio";
                Element studioInput = (Element) musicBandElement.getElementsByTagName(attributeName).item(0);


                attributeName = "studioName";
                String studioNameInput = studioInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();


                Studio studio = new Studio(studioNameInput);

                if (musicBandCollection.getElementByID(id) != null)
                    throw new WrongArgumentException("music band id must be unique");

                MusicBand musicBand = new MusicBand(id, musicBandName, coordinates, numberOfParticipants, singlesCount,
                        establishmentDate, genre, studio);
                musicBand.setCreationDate(creationDate);
                musicBandCollection.put(id, musicBand);
            }
            musicBandCollection.setCreationDate(collectionCreationDate);
            MusicBand.updateNextId(musicBandCollection);
            return musicBandCollection;
        } catch (NullPointerException e) {
            throw new InvalidFileDataException(path, "music band №" + (i + 1) + ": " + attributeName + " is null");
        } catch (DateTimeParseException e) {
            if (i < 0) {
                throw new InvalidFileDataException(path, attributeName + " is invalid or null");
            } else {
                throw new InvalidFileDataException(path, "music band №" + (i + 1) + ": " + attributeName + " is invalid or null");
            }
        } catch (UnsupportedEncodingException e) {
            throw new InvalidFileDataException(path, "unsupported encoding: " + e.getMessage());
        } catch (SAXParseException e) {
            throw new InvalidFileDataException(path, "XML parse error: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidFileDataException(path, "music band №" + (i + 1) + ": " + attributeName + " must be an integer");
        } catch (WrongArgumentException e) {
            StringBuilder errorMessage = new StringBuilder(e.getMessage());
            errorMessage.delete(0, 2);
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new InvalidFileDataException(path, "music band №" + (i + 1) + ": " + errorMessage);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getClass());
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new InvalidFileDataException(path, e.getMessage());
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read permission for file " + path + "  !");
    }
}
