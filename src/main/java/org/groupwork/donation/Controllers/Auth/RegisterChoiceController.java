package org.groupwork.donation.Controllers.Auth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.groupwork.donation.App;

import java.io.IOException;

public class RegisterChoiceController {


    public AnchorPane contentPane;

    public void handleBackButton(MouseEvent mouseEvent) {
//        navigateTo('/fxml/Auth/');
    }



    public void handleDonorRegistration(MouseEvent mouseEvent) {
        navigateTo("/fxml/Auth/RegisterDonor.fxml");
    }

    public void handleRecipientRegistration(MouseEvent mouseEvent) {
        navigateTo("/fxml/Auth/RegisterRecipient.fxml");
    }

    public void handleBackToChoice(MouseEvent mouseEvent) {
        navigateTo("/fxml/Auth/RegisterChoice.fxml");
    }


    // Method to navigate to different FXML files

    private void navigateTo(String fxmlFile) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newContentPane = loader.load();

            // Set the loaded content onto the content Pane
            contentPane.getChildren().setAll(newContentPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
