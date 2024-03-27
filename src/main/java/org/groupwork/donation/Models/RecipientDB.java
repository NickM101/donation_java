package org.groupwork.donation.Models;

import java.sql.*;
import java.util.ArrayList;
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

                recipientArray.add(new Recipients(username, email, phone, location, status, requestType));
            }
        }

        return recipientArray;
    }
}

//The class below holds the recipients details from the DB
class Recipients {
    private String username, email, phone, location, status, requestType;

    public Recipients(String username, String email, String phone, String location, String status, String requestType){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.requestType = requestType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDonationType() {
        return requestType;
    }
}