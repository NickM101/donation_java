package org.groupwork.donation.Models;

import java.sql.*;

public class DonorDB {
    //The method below adds a donor to the DB on registration
    public static void addUserToDB(String email, String username, String password, String location, String usertype, String phoneno){

        String insertSQL = "INSERT INTO Donor_UD (Email, Username, Password, Location, UserType, PhoneNo) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, usertype);
            preparedStatement.setString(6, phoneno);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success");
            }
        } catch (SQLException e){
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    public static void addDonationsTDB(){}

    public static void listDonors(){}//TODO Review for moving into the admin class
}
