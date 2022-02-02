package com.example.RestaurantOrderInfo.exceptions;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class NoOrderInDB extends RuntimeException {

    final transient Logger logger = Logger.getLogger(NoOrderInDB.class.getName());
    final FileHandler fh;

    public NoOrderInDB(String message) throws IOException {
        super(message);
        fh = new FileHandler("C:\\FinalProject\\RestaurantOrderInfo\\src\\main\\resources\\log4jFile.log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        System.err.println("404 NOT FOUND");
        logger.log(Level.WARNING, "not found");
    }
}
