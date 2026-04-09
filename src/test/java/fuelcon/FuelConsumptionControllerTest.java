package fuelcon;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import fuelcon.service.LocalizationService;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FuelConsumptionControllerTest {

    private FuelConsumptionController controller;

    @BeforeAll
    static void initJavaFx() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
            latch.await(5, TimeUnit.SECONDS);
        } catch (IllegalStateException alreadyStarted) {
            // JavaFX already started
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        // Clear LocalizationService cache
        Field cacheField = LocalizationService.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        ((Map<?, ?>) cacheField.get(null)).clear();

        controller = new FuelConsumptionController();
        runOnFxThreadAndWait(() -> {
            try {
                setField("rootVBox", new VBox());
                setField("lblTitle", new Label());
                setField("lblDistance", new Label());
                setField("lblConsumption", new Label());
                setField("lblPrice", new Label());
                setField("lblLocalTime", new Label());
                setField("txtDistance", new TextField());
                setField("txtConsumption", new TextField());
                setField("txtPrice", new TextField());
                setField("btnCalculate", new Button());
                setField("lblResultOne", new Label());
                setField("lblResultTwo", new Label());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void initialize_setsTitle() throws Exception {
        runOnFxThreadAndWait(() -> controller.initialize());

        Label title = (Label) getField("lblTitle");
        assertFalse(title.getText().isEmpty());
    }

    @Test
    void initialize_setsCalculateButton() throws Exception {
        runOnFxThreadAndWait(() -> controller.initialize());

        Button btn = (Button) getField("btnCalculate");
        assertFalse(btn.getText().isEmpty());
    }

    @Test
    void initialize_setsDistanceLabel() throws Exception {
        runOnFxThreadAndWait(() -> controller.initialize());

        Label lbl = (Label) getField("lblDistance");
        assertFalse(lbl.getText().isEmpty());
    }

    @Test
    void onCalculateClick_validInput_setsResult() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            ((TextField) mustGetField("txtDistance")).setText("500");
            ((TextField) mustGetField("txtConsumption")).setText("8");
            ((TextField) mustGetField("txtPrice")).setText("1.5");
            controller.onCalculateClick(null);
        });

        Label resultOne = (Label) getField("lblResultOne");
        Label resultTwo = (Label) getField("lblResultTwo");
        assertTrue(resultOne.getText().contains("40.00"));
        assertTrue(resultTwo.getText().contains("60.00"));
    }

    @Test
    void onCalculateClick_invalidInput_showsError() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            ((TextField) mustGetField("txtDistance")).setText("abc");
            ((TextField) mustGetField("txtConsumption")).setText("8");
            ((TextField) mustGetField("txtPrice")).setText("1.5");
            controller.onCalculateClick(null);
        });

        Label resultOne = (Label) getField("lblResultOne");
        assertFalse(resultOne.getText().isEmpty());
    }

    @Test
    void onCalculateClick_negativeValues_showsError() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            ((TextField) mustGetField("txtDistance")).setText("-100");
            ((TextField) mustGetField("txtConsumption")).setText("8");
            ((TextField) mustGetField("txtPrice")).setText("1.5");
            controller.onCalculateClick(null);
        });

        Label resultOne = (Label) getField("lblResultOne");
        assertFalse(resultOne.getText().isEmpty());
    }

    @Test
    void onCalculateClick_zeroValues_showsError() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            ((TextField) mustGetField("txtDistance")).setText("0");
            ((TextField) mustGetField("txtConsumption")).setText("0");
            ((TextField) mustGetField("txtPrice")).setText("0");
            controller.onCalculateClick(null);
        });

        Label resultOne = (Label) getField("lblResultOne");
        assertFalse(resultOne.getText().isEmpty());
    }

    @Test
    void onENClick_setsLanguage() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            controller.onENClick(null);
        });

        Label title = (Label) getField("lblTitle");
        assertFalse(title.getText().isEmpty());
    }

    @Test
    void onFRClick_setsLanguage() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            controller.onFRClick(null);
        });

        Label title = (Label) getField("lblTitle");
        assertFalse(title.getText().isEmpty());
    }

    @Test
    void onJPClick_setsLanguage() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            controller.onJPClick(null);
        });

        Label title = (Label) getField("lblTitle");
        assertFalse(title.getText().isEmpty());
    }

    @Test
    void onIRClick_setsRTLDirection() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            controller.onIRClick(null);
        });
        waitForFxEvents();

        VBox root = (VBox) getField("rootVBox");
        assertEquals(NodeOrientation.RIGHT_TO_LEFT, root.getNodeOrientation());
    }

    @Test
    void onENClick_setsLTRDirection() throws Exception {
        runOnFxThreadAndWait(() -> {
            controller.initialize();
            controller.onIRClick(null);
            controller.onENClick(null);
        });
        waitForFxEvents();

        VBox root = (VBox) getField("rootVBox");
        assertEquals(NodeOrientation.LEFT_TO_RIGHT, root.getNodeOrientation());
    }

    @Test
    void initialize_localTimeNotEmpty() throws Exception {
        runOnFxThreadAndWait(() -> controller.initialize());

        Label localTime = (Label) getField("lblLocalTime");
        assertFalse(localTime.getText().isEmpty());
    }

    private Object getField(String fieldName) throws Exception {
        Field field = FuelConsumptionController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(controller);
    }

    private Object mustGetField(String fieldName) {
        try {
            return getField(fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(String fieldName, Object value) throws Exception {
        Field field = FuelConsumptionController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(controller, value);
    }

    private static void runOnFxThreadAndWait(Runnable runnable) throws InterruptedException {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                runnable.run();
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Timed out waiting for JavaFX task");
        }
    }

    private static void waitForFxEvents() throws InterruptedException {
        runOnFxThreadAndWait(() -> {});
    }
}