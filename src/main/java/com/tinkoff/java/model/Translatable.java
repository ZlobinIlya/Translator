package com.tinkoff.java.model;


import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;

public interface Translatable {
    public void translate() throws ParseException, IOException;
}
