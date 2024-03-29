package org.groupwork.donation.Models;

import java.sql.*;

public class Assigned {

    //The method below takes the donor username and the recipient username and adds them to a db called assigned
    public static void combineData(String donorUsername, String recipientUsername) {
        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD)) {
            // Retrieve donor data
            String donorQuery = "SELECT username, email, status FROM Donor_UD WHERE username = ?";
            PreparedStatement donorStatement = connection.prepareStatement(donorQuery);
            donorStatement.setString(1, donorUsername);
            ResultSet donorResultSet = donorStatement.executeQuery();

            // Retrieve recipient data
            String recipientQuery = "SELECT username, email, status FROM Recipient_UD WHERE username = ?";
            PreparedStatement recipientStatement = connection.prepareStatement(recipientQuery);
            recipientStatement.setString(1, recipientUsername);
            ResultSet recipientResultSet = recipientStatement.executeQuery();

            // Insert combined data into the combined table
            String combinedInsertQuery = "INSERT INTO `Assigned_Donors&Recipients` (donor_username, donor_email, donor_status, recipient_username, recipient_email, recipient_status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement combinedStatement = connection.prepareStatement(combinedInsertQuery);

            if (donorResultSet.next() && recipientResultSet.next()) {
                // Extract donor data
                String donorEmail = donorResultSet.getString("email");
                String donorStatus = donorResultSet.getString("status");

                // Extract recipient data
                String recipientEmail = recipientResultSet.getString("email");
                String recipientStatus = recipientResultSet.getString("status");

                // Insert combined data into the combined table
                combinedStatement.setString(1, donorUsername);
                combinedStatement.setString(2, donorEmail);
                combinedStatement.setString(3, donorStatus);
                combinedStatement.setString(4, recipientUsername);
                combinedStatement.setString(5, recipientEmail);
                combinedStatement.setString(6, recipientStatus);

                // Execute the insert query
                combinedStatement.executeUpdate();
                System.out.println("Data combined and inserted into the 'combined' table successfully.");
            } else {
                System.out.println("Donor or recipient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error combining data: " + e.getMessage());
        }
    }

}
