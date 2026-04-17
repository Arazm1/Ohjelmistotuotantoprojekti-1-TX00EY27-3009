package fuelcon.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {
    private static final Logger logger = Logger.getLogger("FuelConApp");

    public static void info(String message){
        logger.log(Level.INFO, message);
    }

    public static void error(String message, Throwable throwable){
        logger.log(Level.SEVERE, message, throwable);
    }

    public static void warn(String message){
        logger.log(Level.WARNING, message);
    }
}
