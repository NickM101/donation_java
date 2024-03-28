package org.groupwork.donation.Models;

import org.groupwork.donation.Views.ViewFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    // user
    private User user;
    private String isUserLoggedIn;


    public Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();

        this.isUserLoggedIn = null;

        this.user = new User("","","","","","");
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(String userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }

    public void validateUserCredentials(String email, String password) throws SQLException {
        String query = "SELECT * FROM Donation_App_UD WHERE Email = ? AND Password = ?";

        try (Connection connection = databaseDriver.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String email_address = resultSet.getString("Email");
                    String username = resultSet.getString("Username");
                    String location = resultSet.getString("Location");
                    String userType = resultSet.getString("UserType");
                    String phoneNo = resultSet.getString("PhoneNo");
                    String website = resultSet.getString("Org_Website");
                    User user = new User(email_address, username, location, userType, phoneNo, website);
                    setUser(user);
                    setUserLoggedIn(userType);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in validateUserCredentials: " + e);
            throw e; // Rethrow the exception to propagate it to the caller
        }
    }


    public void registerNewUser(String email, String username, String password, String location, String userType, String phoneNo, String website) throws SQLException {
        String insertSQL = "INSERT INTO Donation_App_UD(Email, Username, Password, Location, UserType, PhoneNo, Org_Website) VALUES (?,?,?,?,?,?)";

        try (Connection connection = databaseDriver.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, userType);
            preparedStatement.setString(6, phoneNo);
            preparedStatement.setString(7, website);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error registering user" +e);
            throw e; // Rethrow the exception to propagate it to the caller
        }


    }
}
