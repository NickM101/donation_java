package org.groupwork.donation.Models;

import java.sql.*;

public class DonorDB {
    //The method below adds a donor to the DB on registration (TDB) To DataBase

    static String username;
    public static void addDonorTDB(String email, String username, String password, String location, String usertype, String phoneno){

        String inAuth = "INSERT INTO Donation_App_UD (Email, Username, Password, Location, UserType, PhoneNo) VALUES (?,?,?,?,?,?)";
        String inDonor = "INSERT INTO Donor_UD (Email, Username, Location, UserType, PhoneNo) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(inAuth)) {
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
            System.out.println("Exception to Auth DB caught");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
            PreparedStatement prepStmtDUD = connection.prepareStatement(inDonor)){
            prepStmtDUD.setString(1, email);
            prepStmtDUD.setString(2, username);
            prepStmtDUD.setString(3, location);
            prepStmtDUD.setString(4, usertype);
            prepStmtDUD.setString(5, phoneno);

            int rowsAffected2 = prepStmtDUD.executeUpdate();
            if (rowsAffected2 > 0){
                System.out.println("Added to the donor DB");
            }
        }
        catch (SQLException e){
            System.out.println("Exception to donor DB Caught");
            e.printStackTrace();
        }
    }

    //The method below adds a donation to the DB
    public static void addDonationTDB(String donation)
    {
        String sql = "UPDATE Donor_UD SET DonationType = ? WHERE Username = ?";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, donation);
            preparedStatement.setString(2, username); //TODO: Create a link to the username

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listDonors(){}//TODO Review for moving into the admin class
}
