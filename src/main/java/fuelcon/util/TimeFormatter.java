package fuelcon.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeFormatter {
    public static String format(LocalDateTime dateTime, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withLocale(locale);
        return dateTime.format(formatter);
    }
}