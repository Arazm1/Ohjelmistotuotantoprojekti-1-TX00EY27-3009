package fuelcon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fuelcon.datasource.MariaDbConnection;

public class CalculationService {


    private static final String INSERT_SQL = 
        "INSERT INTO calculation_records " +
        "(distance, consumption, price, total_fuel, total_cost, language) " +
        "VALUES (?, ?, ?, ?, ?, ?)";


    public boolean saveCalculation(double distance, double consumption, double price,
                                double totalFuel, double totalCost, String language){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){

                statement.setDouble(1, distance);
                statement.setDouble(2, consumption);
                statement.setDouble(3, price);
                statement.setDouble(4, totalFuel);
                statement.setDouble(5, totalCost);
                statement.setString(6, language);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
        }
        catch(SQLException e){
            System.err.println("Error saving calculation record: " + e.getMessage());
            return false;
        }
    
    }

    public Connection getConnection() throws SQLException {
        return MariaDbConnection.getConnection();
    }
    
}
