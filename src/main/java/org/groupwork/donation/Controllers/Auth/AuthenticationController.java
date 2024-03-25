package org.groupwork.donation.Controllers.Auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.groupwork.donation.Models.Model;

import java.io.IOException;

public class AuthenticationController {
    @FXML
    public TextField email_field = new TextField();
    @FXML
    public PasswordField password_field = new PasswordField();
    @FXML
    public Label error_msg = new Label();

    public String LOGIN_FXML = "/fxml/Auth/Login.fxml";
    public String REGISTER_CHOICE_FXML = "/fxml/Auth/RegisterChoice.fxml";
    public String REGISTER_DONOR_FXML = "/fxml/Auth/RegisterDonor.fxml";
    public String REGISTER_RECIPIENT_FXML = "/fxml/Auth/RegisterRecipient.fxml";
    public String REGISTER_DONOR_FORM_FXML = "/fxml/Auth/RegisterChoice.fxml";
    public String ADMIN_DASHBOARD_FXML = "/fxml/Admin/Admin.fxml";

    @FXML
    public Pane content_area;

    public void handleLogin(ActionEvent actionEvent) {
//
        System.out.println(email_field.getText());
        if(!email_field.getText().isBlank() && !password_field.getText().isBlank()){
            navigationToDashboard("Admin", ADMIN_DASHBOARD_FXML);
            error_msg.setText("Login");
            Model.loginUser(email_field.getText(), password_field.getText());
        } else {
            error_msg.setText("Enter missing fields to login.");
        }
    }

    public void handleSignUp(ActionEvent actionEvent) { navigateTo(REGISTER_CHOICE_FXML); }
    public void handleBackToLogin(MouseEvent mouseEvent) { navigateTo(LOGIN_FXML); }
    public void handleDonorRegistration(MouseEvent mouseEvent) {
        navigateTo(REGISTER_DONOR_FXML);
    }
    public void handleRecipientRegistration(MouseEvent mouseEvent) {
        navigateTo(REGISTER_RECIPIENT_FXML);
    }
    public void handleBackToChoice(MouseEvent mouseEvent) {
        navigateTo(REGISTER_CHOICE_FXML);
    }

    private void navigateTo(String fxmlFile) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newContentPane = loader.load();

            // Set the loaded content onto the content Pane
            content_area.getChildren().setAll(newContentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigationToDashboard(String name, String fxmlFile) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane root = loader.load();

            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Additional logic based on the user's role (name)
            switch (name) {
                case "Admin":
                    stage.setTitle("Admin Dashboard");
                    break;
                case "Donor":
                    stage.setTitle("User Dashboard");
                    break;
                case "Recipient":
                    stage.setTitle("Recipient Dashboard");
                    break;
            }

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
