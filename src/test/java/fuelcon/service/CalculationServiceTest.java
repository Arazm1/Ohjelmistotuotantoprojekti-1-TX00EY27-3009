package fuelcon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    //A Spy allows to use the real object but override specific methods (like getConnection)
    @Spy
    private CalculationService calculationService;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;


    @Test
    void testSaveCalculation_Success() throws SQLException {
        doReturn(mockConnection).when(calculationService).getConnection();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1); // Simulate 1 row inserted

        boolean result = calculationService.saveCalculation(150.5, 6.5, 1.80, 9.78, 17.61, "en");

        assertTrue(result, "Should return true when rows are affected");

        verify(mockStatement).setDouble(1, 150.5);
        verify(mockStatement).setDouble(2, 6.5);
        verify(mockStatement).setDouble(3, 1.80);
        verify(mockStatement).setDouble(4, 9.78);
        verify(mockStatement).setDouble(5, 17.61);
        verify(mockStatement).setString(6, "en");
        verify(mockStatement).executeUpdate();
    }

    @Test
    void testSaveCalculation_NoRowsAffected() throws SQLException {
        doReturn(mockConnection).when(calculationService).getConnection();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(0); // Simulate insert failing silently

        boolean result = calculationService.saveCalculation(100.0, 5.0, 2.0, 5.0, 10.0, "nl");

        assertFalse(result, "Should return false when no rows are affected");
    }

    @Test
    void testSaveCalculation_ThrowsSQLException() throws SQLException {
        doReturn(mockConnection).when(calculationService).getConnection();

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(new SQLException("Database connection lost"));

        boolean result = calculationService.saveCalculation(200.0, 7.0, 1.5, 14.0, 21.0, "fr");

        assertFalse(result, "Should return false when a SQLException is thrown");
    }

    @Test
    void testSaveCalculation_ConnectionThrowsException() throws SQLException {

        doThrow(new SQLException("Could not connect")).when(calculationService).getConnection();

        boolean result = calculationService.saveCalculation(200.0, 7.0, 1.5, 14.0, 21.0, "de");

        assertFalse(result, "Should gracefully catch exception and return false");

        verify(mockConnection, never()).prepareStatement(anyString());
    }
}