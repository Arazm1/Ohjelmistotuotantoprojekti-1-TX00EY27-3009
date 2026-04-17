package fuelcon;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import fuelcon.service.CalculationService;
import fuelcon.service.LocalizationService;
import fuelcon.utils.AppLogger;
import fuelcon.utils.TimeFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FuelConsumptionController {

    private FuelCalculator calculator = new FuelCalculator();
    private final CalculationService calculationService = new CalculationService();

    private String INVALID_INPUT_KEY = "invalid.input";

    @FXML
    private VBox rootVBox;

    @FXML
    private Label lblTitle;

    //Center Labels + TextFields
    @FXML
    private Label lblDistance;

    @FXML
    private Label lblConsumption;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblLocalTime;

    @FXML
    private TextField txtDistance;

    @FXML
    private TextField txtConsumption;

    @FXML
    private TextField txtPrice;


    @FXML
    private Button btnCalculate;

    @FXML
    private Label lblResultOne;

    @FXML
    private Label lblResultTwo;

    private Locale currentLocale = new Locale("en", "US");
    private Map<String, String> localizedStrings;


    @FXML
    public void initialize(){
        //Set initial language
        setLanguage(currentLocale);

        //Add listeners to clear result when input changes
        txtDistance.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResultOne.setText("");
            lblResultTwo.setText("");
        });
        txtConsumption.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResultOne.setText("");
            lblResultTwo.setText("");
        });
        txtPrice.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResultOne.setText("");
            lblResultTwo.setText("");
        });
    }


    /**
     * Language button handlers
     */
    @FXML
    public void onENClick(ActionEvent e) { setLanguage(new Locale("en", "US")); }

    @FXML
    public void onFRClick(ActionEvent e) { setLanguage(new Locale("fr", "FR")); }

    @FXML
    public void onJPClick(ActionEvent e) { setLanguage(new Locale("ja", "JP")); }

    @FXML
    public void onIRClick(ActionEvent e) { setLanguage(new Locale("fa", "IR")); }


    /**
     * Calculate Fuel Consumption and Total Cost button handler
     */
    @FXML
    public void onCalculateClick(ActionEvent e) {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double consumption = Double.parseDouble(txtConsumption.getText());
            double cost = Double.parseDouble(txtPrice.getText());

            
            if (distance <= 0 || consumption <= 0 || cost <= 0) {
                lblResultOne.setText(localizedStrings.getOrDefault("invalid.input", "Please enter valid numbers"));
                lblResultTwo.setText("");
                return;
            }

            double totalFuel = calculator.calculateTotalFuel(distance, consumption);
            double totalCost = calculator.calculateTotalCost(totalFuel, cost);

            String result1 = String.format(currentLocale,
                    localizedStrings.getOrDefault("result1.label", "Total Fuel: %.2f L"), totalFuel);
            String result2 = String.format(currentLocale,
                localizedStrings.getOrDefault("result2.label", "Total Cost: %.2f"), totalCost);
            lblResultOne.setText(result1);
            lblResultTwo.setText(result2);



            boolean success = calculationService.saveCalculation(distance, consumption, cost, totalFuel, totalCost, currentLocale.getLanguage());
            if(success){
                AppLogger.info("Successfully saved to DB!");
            }
            else{
                AppLogger.warn("Failed to save into DB");
            }

             
        } catch (NumberFormatException ex) {
            lblResultOne.setText(localizedStrings.getOrDefault("invalid.input", "Please enter valid numbers"));
            lblResultTwo.setText(localizedStrings.getOrDefault("invalid.input", "Please enter valid numbers"));
        }
    }




    /**
     * Set the application language
     * @param locale to be set.
     */
    private void setLanguage(Locale locale){
        currentLocale = locale;
         //Clear previous results
        lblResultOne.setText("");
        lblResultTwo.setText("");

        localizedStrings = LocalizationService.getLocalizedStrings(locale);

        if (locale.getLanguage().equals("ja")) {
            rootVBox.setStyle("-fx-font-family: 'Noto Sans CJK JP';");
        } else {
            rootVBox.setStyle(""); // reset for other languages
        }
        //Update all UI labels
        lblTitle.setText(localizedStrings.getOrDefault("app.title", "Fuel Consumption and Total Cost Calculator"));
        lblDistance.setText(localizedStrings.getOrDefault("distance.label", "Distance (km):"));
        lblConsumption.setText(localizedStrings.getOrDefault("consumption.label", "Fuel Consumption (L/100 km):"));
        lblPrice.setText(localizedStrings.getOrDefault("price.label", "Fuel Price (per liter)"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate.button", "Calculate total fuel and cost"));

        //Textfield prompt text
        txtDistance.setPromptText(localizedStrings.getOrDefault("distance.prompt", "Enter distance"));
        txtConsumption.setPromptText(localizedStrings.getOrDefault("consumption.prompt", "Enter fuel consumption"));
        txtPrice.setPromptText(localizedStrings.getOrDefault("price.prompt", "Enter fuel price"));

        // Update time display with new locale
        displayLocalTime(locale);

        // Apply text direction based on language
        applyTextDirection(locale);
    }




    /**
     * Apply LTR or RTL layout direction
     */
    private void applyTextDirection(Locale locale) {
        // Step 1: Detect if the language is RTL
        String lang = locale.getLanguage();
        boolean isRTL = lang.equals("fa")  // Persian
                || lang.equals("ur")   // Urdu
                || lang.equals("ar")   // Arabic
                || lang.equals("he");  // Hebrew

        // Step 2: Wrap UI changes in Platform.runLater() for thread safety
        Platform.runLater(() -> {
            // Step 3: Set NodeOrientation on the root VBox
            if (rootVBox != null) {
                rootVBox.setNodeOrientation(
                        isRTL ? NodeOrientation.RIGHT_TO_LEFT
                                : NodeOrientation.LEFT_TO_RIGHT
                );
            }

            // Step 4: Align text inside TextFields
            String alignment = isRTL ? "-fx-text-alignment: right; -fx-alignment: center-right;"
                    : "-fx-text-alignment: left; -fx-alignment: center-left;";
            txtDistance.setStyle(alignment);
            txtConsumption.setStyle(alignment);
            txtPrice.setStyle(alignment);
        });
    }

    /**
     * Display local time formatted for the current locale
     */
    private void displayLocalTime(Locale locale) {
        String pattern = localizedStrings.getOrDefault("time_format", "HH:mm:ss");
        String formatted = TimeFormatter.format(LocalDateTime.now(), pattern, locale);
        String timeStr = String.format(
                localizedStrings.getOrDefault("current_time", "Current Time: %s"),
                formatted
        );
        lblLocalTime.setText(timeStr);
    }
    
}
