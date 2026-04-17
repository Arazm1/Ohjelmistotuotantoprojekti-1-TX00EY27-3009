package fuelcon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import fuelcon.datasource.MariaDbConnection;

public class LocalizationService {

    private static final Map<String, Map<String, String>> cache = new HashMap<>();

    /**
     * Get localized strings for a specific locale
     */
    public static Map<String, String> getLocalizedStrings(Locale locale) {

        String language = locale.getLanguage();

        if(cache.containsKey(language)){
            return cache.get(language);
        }

        //Map<String, String> strings = new HashMap<>();
        Map<String, String> strings = loadStrings(language);

        //Fallback to english
        if(strings.isEmpty() && !language.equals("en")){
            System.out.println("No strins found for: " + language);
            strings = loadStrings("en");
        }

        //Last resord, get hardcoded defaults
        if(strings.isEmpty()){
            strings = getHardcodedDefaults();
        }

        cache.put(language, strings);
        return strings;
    }

    /**
     * Query all key-value pairs for a given language from the DB.
     * 
     * @param language
     * @return
     */
    public static Map<String, String> loadStrings(String language) {
        Map<String, String> strings = new HashMap<>();

        String sql = "SELECT `key`, `value` FROM localization_strings WHERE language = ?";

        try (Connection conn = MariaDbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, language);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                strings.put(rs.getString("key"), rs.getString("value"));
            }
            System.out.println("Loaded " + strings.size() + " strings for language: " + language);
        } catch (SQLException e) {
            System.out.println("Failed to load localization strings for: " + language);
        }

        return strings;
    }

    public static String getStrings(String language, String key) {
        Map<String, String> strings = cache.getOrDefault(language, new HashMap<>());
        return strings.getOrDefault(key, key);
    }

    protected static void clearCache() {
        cache.clear();
    }

    protected static Map<String, String> getHardcodedDefaults() {
        Map<String, String> defaults = new HashMap<>();
        defaults.put("app.title", "Fuel Consumption and Total Cost Calculator");
        defaults.put("distance.label", "Distance (km):");
        defaults.put("consumption.label", "Fuel Consumption (L/100 km):");
        defaults.put("price.label", "Fuel Price (per liter):");
        defaults.put("calculate.button", "Calculate total fuel and cost");
        defaults.put("result1.label", "Total Fuel: %.2f L");
        defaults.put("result2.label", "Total Cost: %.2f");
        defaults.put("invalid.input", "Please enter valid numbers");
        defaults.put("time_format", "HH:mm:ss");
        defaults.put("current_time", "Current Time: %s");
        return defaults;
    }
}