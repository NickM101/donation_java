package org.groupwork.donation.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.groupwork.donation.Models.Model.ResponseArray;
import static org.groupwork.donation.Models.Model.databaseDriver;

public class RecipientDB {

    public static ArrayList<Recipients> recipientArray = new ArrayList<>();

    //The method below adds a recipient to the DB on registration. (TDB) To DataBase
    public static void addRecipientTDB(String email, String username, String password, String location, String usertype, String phoneno, String orgWeb){

        String inAuth = "INSERT INTO Donation_App_UD (Email, Username, Password, Location, UserType, PhoneNo, Org_Website) VALUES (?,?,?,?,?,?,?)";
        String inRecipient = "INSERT INTO Recipient_UD (email, username, location, usertype, phone, status, requestType) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(inAuth)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, usertype);
            preparedStatement.setString(6, phoneno);
            preparedStatement.setString(7, orgWeb);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success");
            }
        } catch (SQLException e){
            System.out.println("Exception to Auth DB caught");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement prepStmtRUD = connection.prepareStatement(inRecipient)){
            prepStmtRUD.setString(1, email);
            prepStmtRUD.setString(2, username);
            prepStmtRUD.setString(3, location);
            prepStmtRUD.setString(4, usertype);
            prepStmtRUD.setString(5, phoneno);
            prepStmtRUD.setString(6, "NA");
            prepStmtRUD.setString(7, "None made");

            int rowsAffected2 = prepStmtRUD.executeUpdate();
            if (rowsAffected2 > 0){
                System.out.println("Added to the recipient DB");
            }
        }
        catch (SQLException e){
            System.out.println("Exception to recipient DB Caught");
            e.printStackTrace();
        }

    }

    //The method below adds a request to the DB
    public static void addRequestTDB(String request)
    {
        String sql = "UPDATE Recipient_UD SET RequestType = ? WHERE Email = ?";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request);
            preparedStatement.setString(2, Model.userEmail);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Lists the Recipients and their requests on the admin page
    public static ArrayList listRecipints() throws SQLException{
        String selectSQL = "SELECT * FROM Recipient_UD";

        try (Connection connection = DriverManager.getConnection(Model.JDBC_URL, Model.USERNAME, Model.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String location = resultSet.getString("location");
                String status = resultSet.getString("status");
                String requestType = resultSet.getString("requestType");
                String userType = resultSet.getString("usertype");
                String orgWeb = resultSet.getString("orgWeb");

                recipientArray.add(new Recipients(username, email, phone, location, status, requestType, orgWeb));
            }
        }

        return recipientArray;
    }

    public static Map<String, String> recipientsRqsts(String userType) {
        String query = "SELECT * FROM Recipient_UD WHERE requestType != ?";
        Map<String, String> recipients = new LinkedHashMap<>();

        try (Connection connection = databaseDriver.connect();
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
}