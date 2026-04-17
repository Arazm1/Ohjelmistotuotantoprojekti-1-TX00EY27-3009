package fuelcon.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AppLoggerTest {
    private TestLogHandler testLogHandler;
    private Logger targetLogger;

    /**
     * Initializes the custom handler
     * Grabs the exact logger instance AppLogger is using
     * Attaches the interceptor handler
     */
    @BeforeEach
    void setUp(){
        testLogHandler = new TestLogHandler();

        targetLogger = Logger.getLogger("FuelConApp");

        targetLogger.addHandler(testLogHandler);
    }

    /**
     * Clean up the handler after each test so they dont leak into others.
     */
    @AfterEach
    void tearDown(){
        targetLogger.removeHandler(testLogHandler);
    }


    @Test
    void testInfoLogsCorrectly(){
        String expectedMsg = "Database connection successful.";

        AppLogger.info(expectedMsg);

        assertEquals(1, testLogHandler.getRecords().size());
        LogRecord record = testLogHandler.getRecords().get(0);

        assertEquals(Level.INFO, record.getLevel());
        assertEquals(expectedMsg, record.getMessage());
    }

    @Test
    void testWarnLogsCorrectly() {
        String expectedMessage = "Performance is degrading.";

        AppLogger.warn(expectedMessage);

        assertEquals(1, testLogHandler.getRecords().size(), "Should have logged exactly one message");
        LogRecord record = testLogHandler.getRecords().get(0);

        assertEquals(Level.WARNING, record.getLevel());
        assertEquals(expectedMessage, record.getMessage());
    }

    @Test
    void testErrorLogsCorrectly() {
        String expectedMessage = "Failed to save to DB!";
        Throwable expectedException = new RuntimeException("Simulated DB failure");

        AppLogger.error(expectedMessage, expectedException);

        assertEquals(1, testLogHandler.getRecords().size(), "Should have logged exactly one message");
        LogRecord record = testLogHandler.getRecords().get(0);

        assertEquals(Level.SEVERE, record.getLevel()); // java.util.logging uses SEVERE for errors
        assertEquals(expectedMessage, record.getMessage());
        assertSame(expectedException, record.getThrown(), "The thrown exception should match exactly");
    }
}