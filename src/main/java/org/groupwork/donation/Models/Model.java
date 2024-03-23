package org.groupwork.donation.Models;

import java.sql.*;

public class Model {
    private static final String JDBC_URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11693731";
    private static final String USERNAME = "sql11693731";
    private static final String PASSWORD = "fzBx8RdtCU";

    public static void initializeDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connection");
            //loadUsersDB(connection);
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //The following method adds user details to the database
    public static void addUserToDB(String email, String username, String password, String location, String usertype, String phoneno){

        String insertSQL = "INSERT INTO Donation_App_UD(Eamil, Username, Password, Location, UserType, PhoneNo) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
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


    //The following method verifies user details at login and opens the appropriate interface
    public void loginUser(String username, String password) {
        // Check if the user exists in the database
        boolean userExists = checkUserExists(username, password);

        if (userExists) {
            // Retrieve user type from the database
            String userType = getUserType(username);

            // Call the relevant class based on user type
            switch (userType) {
                case "admin":
                    //AdminInterface adminInterface = new AdminInterface();
                    // Implement admin interface methods
                    break;
                case "donor":
                    //DonorInterface donorInterface = new DonorInterface();
                    // Implement donor interface methods
                    break;
                case "recipient":
                    //RecipientInterface recipientInterface = new RecipientInterface();
                    // Implement recipient interface methods
                    break;
                default:
                    System.out.println("Unknown user type");
            }
        } else {
            System.out.println("User not found. Please register.");
            // Prompt the user to register
            // Implement registerUser method or display a registration form
        }
    }

    //The following method checks if a user is present on the database
    private boolean checkUserExists(String username, String password) {
        String query = "SELECT COUNT(*) FROM Donation_App_UD WHERE Username = ? AND Password = ?";
        boolean userExists = false;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
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
    private String getUserType(String username) {
        String query = "SELECT UserType FROM Donation_App_UD WHERE Username = ?";
        String userType = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = resultSet.getString("userType");
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
        String query = "SELECT Username, UserType, Eamil, PhoneNo FROM Donation_App_UD WHERE Username = ?";

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


}
