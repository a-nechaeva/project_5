package commands;

import exceptions.io.*;
import exceptions.receiver.*;
public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, CustomIOException;
}
