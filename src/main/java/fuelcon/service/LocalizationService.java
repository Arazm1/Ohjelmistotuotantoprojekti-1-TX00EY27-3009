package fuelcon.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalizationService {
    /**
     * Get localized strings for a specific locale
     */
    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> strings = new HashMap<>();

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                    "fuelcon.i18n.MessagesBundle",
                    locale
            );

            // Extract all keys
            for (String key : bundle.keySet()) {
                strings.put(key, bundle.getString(key));
            }
        } catch (Exception e) {
            System.err.println("Failed to load resource bundle for locale: " + locale);
            // Fallback to English
            try {
                ResourceBundle fallback = ResourceBundle.getBundle(
                        "fuelcon.i18n.MessagesBundle",
                        new Locale("en", "UK")
                );
                for (String key : fallback.keySet()) {
                    strings.put(key, fallback.getString(key));
                }
            } catch (Exception ex) {
                // Use hardcoded defaults as last resort
                strings.put("app.title", "Fuel consumption and Total cost Calculator");
                strings.put("distance.label", "Distance (km):");
                strings.put("consumption.label", "Fuel Consumption (L/100 km):");
                strings.put("price.label", "Fuel Price (per liter)");
                strings.put("calculate.button", "Calculate total fuel and cost");
                //strings.put("time_format", "HH:mm:ss");
                strings.put("result.label", "Result: %.1f - %s");
                strings.put("invalid.input", "Please enter valid numbers");
            }
        }

        return strings;
    }
}