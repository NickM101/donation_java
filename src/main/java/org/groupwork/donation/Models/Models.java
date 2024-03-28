package org.groupwork.donation.Models;

import java.sql.*;
import java.util.Map;

public class Models {
    public static final String JDBC_URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693731";
    public static final String USERNAME = "sql11693731";
    public static final String PASSWORD = "fzBx8RdtCU";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void initializeDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = getConnection();
            System.out.println("DB Connection established");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addUserToDB(String email, String username, String password, String location, String usertype, String phoneno) {
        String insertSQL = "INSERT INTO Donation_App_UD(Email, Username, Password, Location, UserType, PhoneNo) VALUES (?,?,?,?,?,?)";

        try (Connection connection = getConnection();
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
        } catch (SQLException e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    public static void loginUser(String email, String password) {
        boolean userExists = checkUserExists(email, password);
        System.out.println(userExists);

        if (userExists) {
            getUserType(email);
            System.out.println(userExists);
        }
    }

    private static boolean checkUserExists(String email, String password) {
        String query = "SELECT COUNT(*) FROM Donation_App_UD WHERE Email = ? AND Password = ?";
        boolean userExists = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    userExists = count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking user existence.");
            e.printStackTrace();
        }

        return userExists;
    }

    private static void getUserType(String email) {
        String query = "SELECT UserType FROM Donation_App_UD WHERE Email = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String userType = resultSet.getString("UserType");
                    System.out.println(userType);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user type.");
            e.printStackTrace();
        }
    }

    public static void displayUserDetails(String username) {
        String query = "SELECT Username, UserType, Email, PhoneNo FROM Donation_App_UD WHERE Username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String retrievedUsername = resultSet.getString("username");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("email");
                String phoneNo = resultSet.getString("phoneNo");

                System.out.println(email); // Print or use retrieved data
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user details.");
            e.printStackTrace();
        }
    }

    public static void addDonation(String donation) {
        String sql = "UPDATE Donation_App_UD SET Donation = ? WHERE Username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, donation);
            preparedStatement.setString(2, getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getUsername() {
        return null;
    }

    public static void addRequest(String request) {
        String query = "UPDATE Donation_App_UD SET Request = ? WHERE Username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, request);
            preparedStatement.setString(2, getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void donationsAndRequests(Map<String, String> donations, Map<String, String> requests) {
        String donorQuery = "SELECT Username, Donation FROM Donation_App_UD WHERE UserType = 'Donor' AND Donation != '-'";
        String recipientQuery = "SELECT Username, Donation FROM Donation_App_UD WHERE UserType = 'Recipient' AND Donation != '-'";

        try (Connection connection = getConnection();
             PreparedStatement stmtDonor = connection.prepareStatement(donorQuery);
             PreparedStatement stmtRecipient = connection.prepareStatement(recipientQuery)) {

            ResultSet rsDonor = stmtDonor.executeQuery();
            while (rsDonor.next()) {
                String username = rsDonor.getString("Username");
                String donation = rsDonor.getString("Donation");
                donations.put(username, donation);
            }

            ResultSet rsRecipient = stmtRecipient.executeQuery();
            while (rsRecipient.next()) {
                String username = rsRecipient.getString("Username");
                String request = rsRecipient.getString("Request");
                requests.put(username, request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
