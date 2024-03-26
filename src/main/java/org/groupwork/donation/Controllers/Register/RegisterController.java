package org.groupwork.donation.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    
    public TextField email_field;
    public TextField phone_number;
    public TextField location_field;
    public  TextField username_field;
    public PasswordField password_field;
    public Button login_btn;
    public Label error_msg;


    public void loginButtonOnAction(ActionEvent event) {
        if(!email_field.getText().isBlank() && !password_field.getText().isBlank() 
        && !phone_number.getText().isBlank() && !location_field.getText().isBlank()
         && !username_field.getText().isBlank()){
            error_msg.setText("Login");
        } else {
            error_msg.setText("Enter missing fields to login.");
        }
    }
}