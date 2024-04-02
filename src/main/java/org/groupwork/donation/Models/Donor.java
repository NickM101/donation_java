package org.groupwork.donation.Models;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Donor {
    public static void addDonationTDB(String donation)
    {
        String sql = "UPDATE Donor_UD SET donationType = ?, status = 'Active' WHERE email = ?";

        String email = Model.getInstance().getUser().getEmail();

        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, donation);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setContentText("Donation type updated successfully.");
                errorAlert.showAndWait();
                System. out.println("Donation type updated successfully.");
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("No donation type updated. Make suze the email exists.");
                errorAlert.showAndWait();
                System.out.println("No donation type updated. Make suze the email exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Gets donors who have made donations from DB
    public static List<Map<String, String>> donationsMadeByDonor() {
        String query = "SELECT * FROM Donor_UD WHERE donationType != ? AND status = 'Active'";
        List<Map<String, String>> donors = new ArrayList<>();

        try (Connection connection = Model.getInstance().getDatabaseDriver().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "-");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()){
                    return donors;
                }
                donorsArray(donors, resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving donations made: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(donors);
        return donors;
    }

    //Creates a hashmap and adds the donor details from the result set
    public static void donorsArray(List<Map<String, String>> donors, ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            Map<String, String> donor = new HashMap<>();
            donor.put("Email", resultSet.getString("Email"));
            donor.put("Username", resultSet.getString("Username"));
            donor.put("Location", resultSet.getString("Location"));
            donor.put("PhoneNo", resultSet.getString("PhoneNo"));
            donors.add(donor);
        }
    }



}
