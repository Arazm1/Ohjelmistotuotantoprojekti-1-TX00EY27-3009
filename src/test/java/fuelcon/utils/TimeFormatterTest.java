package fuelcon.utils;


import fuelcon.utils.TimeFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimeFormatterTest {

    @Test
    void testFormatNotNull() {
        String result = TimeFormatter.format(LocalDateTime.now(), "HH:mm:ss", Locale.ENGLISH);
        assertNotNull(result);
    }

    @Test
    void testFormatMatchesPattern() {
        LocalDateTime fixed = LocalDateTime.of(2024, 1, 1, 14, 30, 0);
        String result = TimeFormatter.format(fixed, "HH:mm:ss", Locale.ENGLISH);
        assertEquals("14:30:00", result);
    }

    @Test
    void testFormatWithDifferentLocale() {
        LocalDateTime fixed = LocalDateTime.of(2024, 1, 1, 14, 30, 0);
        String result = TimeFormatter.format(fixed, "HH:mm:ss", Locale.FRENCH);
        assertNotNull(result);
    }

}
