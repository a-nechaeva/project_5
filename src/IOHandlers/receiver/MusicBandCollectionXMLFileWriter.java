package IOHandlers.receiver;

import exceptions.io.*;
import models.MusicBand;
import receiver.*;

import java.io.*;
import java.util.Hashtable;

public class MusicBandCollectionXMLFileWriter implements MusicBandCollectionFileWriter{
    private final String path;

    public MusicBandCollectionXMLFileWriter(String path) {
        this.path = path;
    }

    @Override
    public void write(MusicBandCollection musicBandCollection) throws FileNotFoundException, FilePermissionException, CustomIOException {
        checkFile();
        Hashtable<Long, MusicBand> musicBandHashtable = musicBandCollection.getMusicBandHashtable();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bufferedWriter.write("<musicBandCollection collectionCreationDate=\"" + musicBandCollection.getCreationDate() + "\">\n");
            for (MusicBand musicBand : musicBandHashtable.values()) {
                String indent = "    ";
                bufferedWriter.write(indent.repeat(1) + "<musicBand>\n");
                bufferedWriter.write(indent.repeat(2) + "<id>" + musicBand.getId() + "</id>\n");
                bufferedWriter.write(indent.repeat(2) + "<musicBandName>" + musicBand.getName() + "</musicBandName>\n");
                bufferedWriter.write(indent.repeat(2) + "<coordinates>\n");
                bufferedWriter.write(indent.repeat(3) + "<x>" + musicBand.getCoordinates().getX() + "</x>\n");
                bufferedWriter.write(indent.repeat(3) + "<y>" + musicBand.getCoordinates().getY() + "</y>\n");
                bufferedWriter.write(indent.repeat(2) + "</coordinates>\n");
                bufferedWriter.write(indent.repeat(2) + "<musicBandCreationDate>" + musicBand.getCreationDate() + "</musicBandCreationDate>\n");
                bufferedWriter.write(indent.repeat(2) + "<numberOfParticipants>" + musicBand.getNumberOfParticipants() + "</numberOfParticipants>\n");
                if (musicBand.getSinglesCount() == null) {
                    bufferedWriter.write(indent.repeat(2) + "<singlesCount/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(2) + "<singlesCount>" + musicBand.getSinglesCount() + "</singlesCount>\n");
                }
                if (musicBand.getEstablishmentDate() == null) {
                    bufferedWriter.write(indent.repeat(2) + "<establishmentDate/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(2) + "<establishmentDate>" + musicBand.getEstablishmentDate() + "</establishmentDate>\n");
                }
                if (musicBand.getGenre() == null) {
                    bufferedWriter.write(indent.repeat(2) + "<genre/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(2) + "<genre>" + musicBand.getGenre() + "</genre>\n");
                }
                if (musicBand.getStudio() == null) {
                    bufferedWriter.write(indent.repeat(2) + "<studio/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(2) + "<studio>\n");
                    bufferedWriter.write(indent.repeat(3) + "<studioName>" + musicBand.getStudio().getName() + "</studioName>\n");
                    bufferedWriter.write(indent.repeat(2) + "</studio>\n");
                }
                bufferedWriter.write(indent.repeat(1) + "</musicBand>\n");
            }
            bufferedWriter.write("</musicBandCollection>");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new CustomIOException("! " + this.getClass().getName() + ": IOException when writing to " + path + " !");
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canWrite())
            throw new FilePermissionException("! no write permission for file " + path + "  !");
    }
}
