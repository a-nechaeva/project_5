package models;

import exceptions.io.WrongArgumentException;

import static models.helpers.MusicBandArgumentChecker.checkName;

public class Studio {
    private String name; //Поле может быть null

    public Studio() {

    }

    public Studio(String name) {
        this.name = name;
    }

    public void setName(String name) throws WrongArgumentException
    {
        checkName(name);
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
