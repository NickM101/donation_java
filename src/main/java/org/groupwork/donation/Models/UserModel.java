package org.groupwork.donation.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {
    private static final String JDBC_URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693731";
    private static final String USERNAME = "sql11693731";
    private static final String PASSWORD = "fzBx8RdtCU";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void registerUser(String email, String username, String password, String location, String userType, String phoneNo, String Org_Website) {
        String insertSQL = "INSERT INTO Donation_App_UD(Email, Username, Password, Location, UserType, PhoneNO, Org_Website) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, userType);
            preparedStatement.setString(6, phoneNo);
            preparedStatement.setString(7, Org_Website);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error registering user");
            e.printStackTrace();
        }
    }

    public static String loginUser(String email, String password) {
        String userType = "";
        String query = "SELECT UserType FROM Donation_App_UD WHERE Username = ? AND Password = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = resultSet.getString("UserType");
                    System.out.println(userType);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error logging in user: " + e.getMessage());
            e.printStackTrace();
        }

        return userType;
    }

    public static Map<String, String> getUserDetails(String email) {
        String query = "SELECT * FROM Donation_App_UD WHERE Email = ?";
        Map<String, String> userDetails = new HashMap<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userDetails.put("Username", resultSet.getString("Username"));
                    userDetails.put("Location", resultSet.getString("Location"));
                    userDetails.put("UserType", resultSet.getString("UserType"));
                    userDetails.put("PhoneNo", resultSet.getString("PhoneNo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user details");
            e.printStackTrace();
        }

        return userDetails;
    }

    public static List<Map<String, String>> getAllUsers() {
        String query = "SELECT * FROM Donation_App_UD";
        List<Map<String, String>> allUsers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("Email", resultSet.getString("Email"));
                user.put("Username", resultSet.getString("Username"));
                user.put("Location", resultSet.getString("Location"));
                user.put("UserType", resultSet.getString("UserType"));
                user.put("PhoneNo", resultSet.getString("PhoneNo"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all users");
            e.printStackTrace();
        }

        return allUsers;
    }
}
