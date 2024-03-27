package org.groupwork.donation.Models;

import org.groupwork.donation.Controllers.Auth.AuthenticationController;

import java.sql.*;
import java.util.Map;

import org.groupwork.donation.Controllers.Admin.AdminControllers;
import org.groupwork.donation.Controllers.Auth.AuthenticationController;


public class Model {
    public static final String JDBC_URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693731";
    public static final String USERNAME = "sql11693731";
    public static final String PASSWORD = "fzBx8RdtCU";
    public static String userEmail;
    public static String userType;

    public static void initializeDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("DB Connection established");
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //The following method verifies user details at login and opens the appropriate interface
    public static void loginUser(String email, String password) {
        // Check if the user exists in the database
        boolean userExists = checkUserExists(email, password);

        if (userExists) {
            // Retrieve user type from the database
            userType = getUserType(email);
            userEmail = email;

        } else {
            AuthenticationController controller = new AuthenticationController();
//            controller.setError("User not found. Please register.");
//            System.out.println("User not found. Please register.");
            // Prompt the user to register
            // Implement registerUser method or display a registration form
        }
    }

    //The following method checks if a user is present on the database
    private static boolean checkUserExists(String email, String password) {
        String query = "SELECT COUNT(*) FROM Donation_App_UD WHERE Email = ? AND Password = ?";
        boolean userExists = false;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
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

    //The following method returns the usertype of the username provided
    private static String getUserType(String email) {
        String query = "SELECT UserType FROM Donation_App_UD WHERE Email = ?";
        String userType = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = resultSet.getString("UserType");
                    System.out.println(userType);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user type.");
            e.printStackTrace();
        }

        return userType;
    }


    //The following method displays some user details in the dashboard
    public static void displayUserDetails(String username) {
        String query = "SELECT Username, UserType, Email, PhoneNo FROM Donation_App_UD WHERE Username = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String retrievedUsername = resultSet.getString("username");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("email");
                String phoneNo = resultSet.getString("phoneNo");

                //Implementation of labels which utilise the above variables in the GUI

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user details.");
            e.printStackTrace();
        }
    }


    /**
     * The method below adds the donors and the recipients who
     * have made donations and requests to two different maps
     * to be shown on the admin page
     * */
    public static void donationsAndRequests(Map<String, String> donations, Map<String, String> requests) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        String donorQuery = "SELECT Username, Donation FROM Donation_App_UD WHERE UserType = 'Donor' AND Donation != '-'";
        String recipientQuery = "SELECT Username, Donation FROM Donation_App_UD WHERE UserType =  'Recipient' AND Donation != '-'";

        try (PreparedStatement stmtDonor = connection.prepareStatement(donorQuery)){
            ResultSet rsDonor = stmtDonor.executeQuery();
            while (rsDonor.next()){
                String username = rsDonor.getString("Username");
                String donation = rsDonor.getString("Donation");
                donations.put(username, donation);
            }
        }

        try (PreparedStatement stmtRecipient = connection.prepareStatement(recipientQuery)){
            ResultSet rsRecipient = stmtRecipient.executeQuery();
            while (rsRecipient.next()){
                String username = rsRecipient.getString("Username");
                String request = rsRecipient.getString("Request");
                requests.put(username, request);
            }
        }
    }
}
