package org.groupwork.donation.Models;

import java.sql.*;
import java.util.ArrayList;

public class DonorDB {

    //donorArray : Contains objects of all the donors in the DB
    public static ArrayList<Donors> donorArray = new ArrayList<>();

    //The method below adds a donor to the DB on registration. (TDB) To DataBase
    public static void addDonorTDB(String email, String username, String password, String location, String usertype, String phoneno){

        String inAuth = "INSERT INTO Donation_App_UD (Email, Username, Password, Location, UserType, PhoneNo, Org_Website) VALUES (?,?,?,?,?,?,?)";
        String inDonor = "INSERT INTO Donor_UD (email, username, location, userType, phoneno, status, donationType) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(inAuth)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, usertype);
            preparedStatement.setString(6, phoneno);
            preparedStatement.setString(7, "NA");

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
            prepStmtDUD.setString(6, "NA");
            prepStmtDUD.setString(7, "None made");

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
        String sql = "UPDATE Donor_UD SET DonationType = ? WHERE Email = ?";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, donation);
            preparedStatement.setString(2, Model.userEmail);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Lists the donors and their donations on the admin page
    public static ArrayList listDonors() throws SQLException{
        String selectSQL = "SELECT * FROM Donor_UD WHERE donationType != '-'";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String location = resultSet.getString("location");
                String status = resultSet.getString("status");
                String donationType = resultSet.getString("donationType");

                donorArray.add(new Donors(username, email, phone, location, status, donationType));
            }
        }
        return donorArray;
    }
}
