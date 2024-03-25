package org.groupwork.donation.Controllers.Register;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    public TextField email_field;
    public PasswordField password_field;
    public Button login_btn;
    public Label error_msg;


    public void RegisterButtonOnAction(ActionEvent event) {
        if(!email_field.getText().isBlank() && !password_field.getText().isBlank()){
            error_msg.setText("Login");
        } else {
            error_msg.setText("Enter missing fields to login.");
        }
    }
}
