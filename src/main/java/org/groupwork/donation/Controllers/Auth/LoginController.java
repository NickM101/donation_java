package org.groupwork.donation.Controllers.Auth;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {
    public String REGISTER_CHOICE_FXML = "/fxml/Auth/RegisterChoice.fxml";
    public TextField email_field;
    public PasswordField password_field;
    public Button login_btn;
    public Label error_msg;

    @FXML
    public Pane content_area;

    @FXML
    public void initialize() {
        // Perform initialization tasks
        if (content_area != null) {
            // content_area is initialized, you can safely access its methods
            ObservableList<Node> children = content_area.getChildren();
            // Proceed with your logic using children...
        } else {
            System.out.println("content_area is null");
        }
    }

    public void loginButtonOnAction(ActionEvent event) {
        if(!email_field.getText().isBlank() && !password_field.getText().isBlank()){
            error_msg.setText("Login");
        } else {
            error_msg.setText("Enter missing fields to login.");
        }
    }

    @FXML
    public void open_registration(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/Auth/RegisterChoice.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }


}
