package fuelcon.service;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocalizationServiceTest {

    @BeforeEach
    public void clearCache() {
        // Load english first to reset state between tests
        LocalizationService.loadStrings("reset");
    }

    @Test
    public void testGetLocalizedStringsEnglish() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testGetLocalizedStringsFrench() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("fr", "FR"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testGetLocalizedStringsJapanese() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("ja", "JP"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testGetLocalizedStringsPersian() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("fa", "IR"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testCacheWorks() {
        // Call twice — second should come from cache
        Map<String, String> first = LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        Map<String, String> second = LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        assertSame(first, second);
    }

    @Test
    public void testLoadStringsDirectly() {
        Map<String, String> strings = LocalizationService.loadStrings("en");
        assertNotNull(strings);
    }

    @Test
    public void testGetStrings() {
        LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        String value = LocalizationService.getStrings("en", "app.title");
        assertNotNull(value);
    }

    @Test
    public void testUnknownLanguageFallsBackToEnglish() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("xx", "XX"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testEnglishLocalization() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

    @Test
    public void testFrenchLocalization() {
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("fr", "FR"));
        assertNotNull(strings);
    }
}
