package org.groupwork.donation.Controllers.Auth;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.groupwork.donation.Models.Model;
import org.groupwork.donation.Models.UserModel;

import java.io.IOException;
import java.io.InputStream;

public class AuthenticationController {
    private Stage primaryStage = new Stage();
    @FXML
    private TextField email_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Label error_msg;
    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Pane content_area;

    private BooleanProperty loading = new SimpleBooleanProperty(false);

    public boolean isLoading() {
        return loading.get();
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }

    public void setLoading(boolean isLoading) {
        loading.set(isLoading);
    }

    public void setError(String error) {
        System.out.println(error_msg);
        error_msg.setText(error);
    }


    public String LOGIN_FXML = "/fxml/Auth/Login.fxml";
    public String REGISTER_CHOICE_FXML = "/fxml/Auth/RegisterChoice.fxml";
    public String REGISTER_DONOR_FXML = "/fxml/Auth/RegisterDonor.fxml";
    public String REGISTER_RECIPIENT_FXML = "/fxml/Auth/RegisterRecipient.fxml";
    public String ADMIN_DASHBOARD_FXML = "/fxml/Admin/Admin.fxml";
    public String DONOR_DASHBOARD_FXML = "/fxml/Donor/Donor.fxml";
    public String RECIPIENT_DASHBOARD_FXML = "/fxml/Recipient/Recipient.fxml";

    public void handleLogin(ActionEvent actionEvent) {
        if(!email_field.getText().isBlank() && !password_field.getText().isBlank()){
            setLoading(true);
            String userType = UserModel.loginUser(email_field.getText(), password_field.getText());
            setLoading(false);
            switch (userType) {
                case "Admin":
                    navigationToDashboard("Admin", ADMIN_DASHBOARD_FXML, actionEvent);
                    break;
                case "Donor":
                    navigationToDashboard("Donor", DONOR_DASHBOARD_FXML, actionEvent);
                    break;
                case "Recipient":
                    navigationToDashboard("Recipient", RECIPIENT_DASHBOARD_FXML, actionEvent);
                    break;
                default:
                    error_msg.setText("User not found. Please register");
            }
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

    public void registerDonor(ActionEvent event){
        UserModel.registerUser("bruce@mail.com", "bruce","12345", "Chuka", "Donor", "0707712344", "website.co.ke" );
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

    public void navigationToDashboard(String name, String fxmlFile, ActionEvent event) {
        final String ICON_IMAGE = "/Images/donation_app.png";
        InputStream ImageClass = getClass().getResourceAsStream(ICON_IMAGE);
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            Pane root = loader.load();
            System.out.println("Navigation Auth");
            Node  source = (Node)  event.getSource();
            Stage prev_stage  = (Stage) source.getScene().getWindow();
            prev_stage.close();

            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.setTitle(name + " Dashboard");
            stage.getIcons().add(new Image(ImageClass));
            stage.setScene(new Scene(root));


            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
