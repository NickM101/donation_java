package org.groupwork.donation.Models;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Recipient {

    public static void addRequestTDB(String request)
    {
        String sql = "UPDATE Recipient_UD SET requestType = ?, status = 'Active' WHERE email = ?";

        String email = Model.getInstance().getUser().getEmail();

        System.out.println(email);

        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setContentText("Request was sent successfully.");
                errorAlert.showAndWait();
                System. out.println("Request was sent successfully.");
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("No request updated. Make sure the email exists");
                errorAlert.showAndWait();
                System.out.println("No request updated. Make suze the email exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> recipientsRequests() {
        String query = "SELECT * FROM Recipient_UD WHERE requestType != ? AND  status = 'Active'";
        Map<String, String> recipients = new LinkedHashMap<>();

        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "-");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    recipients.put(resultSet.getString("username"), resultSet.getString("requestType"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving recipients" + e.getMessage());
            e.printStackTrace();
        }

        return recipients;
    }


    public static Map<String, String> requestsMade(String recipientEmail) {
        String query = "SELECT * FROM `Assigned_Donors&Recipients` WHERE RecipientEmail = ?";
        Map<String, String> requests = new LinkedHashMap<>();

        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipientEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    requests.put(resultSet.getString("status"), resultSet.getString("requestType"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving requests" + e.getMessage());
            e.printStackTrace();
        }

        return requests;
    }

    // Method to update status in combined table when recipient clicks complete
    private void updateCombinedTableStatus(String recipientEmail) throws SQLException {
        String updateStatusQuery = "UPDATE `Assigned_Donors&Recipients` SET status = ? WHERE RecipientUsername = ?";
        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement updateStatusStatement = connection.prepareStatement(updateStatusQuery)) {
            updateStatusStatement.setString(1, "Complete");
            updateStatusStatement.setString(2, recipientEmail);
            updateStatusStatement.executeUpdate();
        }
    }
}
