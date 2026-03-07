
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML private TextField number1Field;
    @FXML private TextField number2Field;
    @FXML private Label resultLabel;

    @FXML
    private void onCalculateClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());

            double sum = add(num1, num2);
            double product = product(num1, num2);
            double subtract = subtract(num1, num2);
            double division;
            if(num2 != 0){
                division = division(num1, num2);
            }
            else{
                division = Double.NaN;
            }

            resultLabel.setText("Sum: " + sum + ", Product: " + product + ", Subtract: " + subtract + ", Division: " + division);

            // Save to DB
            ResultService.saveResult(num1, num2, sum, product, subtract, division);

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }


    public double add(double num1, double num2){
        return num1 + num2;
    }

    public double subtract(double num1, double num2){
        return num1 - num2;
    }

    public double product(double num1, double num2){
        return num1 * num2;
    }

    public double division(double num1, double num2){
        return num1 / num2;
    }
}