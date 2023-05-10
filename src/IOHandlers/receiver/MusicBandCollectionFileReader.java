package IOHandlers.receiver;

import exceptions.io.*;
import receiver.MusicBandCollection;
import java.io.FileNotFoundException;

public interface MusicBandCollectionFileReader {
    MusicBandCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException;
}
