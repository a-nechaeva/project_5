package IOHandlers.receiver;

import exceptions.io.*;
import receiver.MusicBandCollection;

import java.io.FileNotFoundException;

public interface MusicBandCollectionFileWriter {
    void write(MusicBandCollection musicBandCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}
