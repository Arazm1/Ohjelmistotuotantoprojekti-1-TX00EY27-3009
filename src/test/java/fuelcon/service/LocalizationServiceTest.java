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
        LocalizationService.clearCache();
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
    public void testGetStringsUnknownKey() {
        LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        String value = LocalizationService.getStrings("en", "nonexistent.key");
        assertEquals("nonexistent.key", value);
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

    @Test
    public void testLoadStringsJapanese() {
        Map<String, String> strings = LocalizationService.loadStrings("ja");
        assertNotNull(strings);
    }

    @Test
    public void testLoadStringsPersian() {
        Map<String, String> strings = LocalizationService.loadStrings("fa");
        assertNotNull(strings);
    }

    @Test
    public void testGetHardcodedDefaultsNotEmpty() {
        Map<String, String> defaults = LocalizationService.getHardcodedDefaults();
        assertNotNull(defaults);
        assertFalse(defaults.isEmpty());
    }

    @Test
    public void testGetHardcodedDefaultsContainsKeys() {
        Map<String, String> defaults = LocalizationService.getHardcodedDefaults();
        assertTrue(defaults.containsKey("app.title"));
        assertTrue(defaults.containsKey("result1.label"));
        assertTrue(defaults.containsKey("invalid.input"));
    }

    @Test
    public void testClearCache() {
        LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        LocalizationService.clearCache();
        // After clearing, loading again should hit DB again
        Map<String, String> strings = LocalizationService.getLocalizedStrings(new Locale("en", "US"));
        assertNotNull(strings);
    }
}
