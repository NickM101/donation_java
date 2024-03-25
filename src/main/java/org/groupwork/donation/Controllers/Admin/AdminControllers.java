package org.groupwork.donation.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminControllers {
    public AnchorPane content_area;

    public String OVERVIEW_DASH_FXML = "/fxml/Admin/OverviewDash.fxml";
    public String TotalDonors_FXML = "/fxml/Admin/TotalDonars.fxml";
    public String TotalDonations_FXML = "/fxml/Admin/TotalDonations.fxml";
    public String TotalRecipients_FXML = "/fxml/Admin/TotalRecipients.fxml";
    public String VerifyAccounts_FXML = "/fxml/Admin/VerifyAccounts.fxml";

    public void handleOverview(ActionEvent actionEvent) {
        navigateTo(OVERVIEW_DASH_FXML);
    }

    public void handleVerifyAccounts(ActionEvent actionEvent) {
        navigateTo(VerifyAccounts_FXML);
    }

    public void handleTotalDonars(ActionEvent actionEvent) {
        navigateTo(TotalDonors_FXML);
    }

    public void handleTotalRecipients(ActionEvent actionEvent) {
        navigateTo(TotalRecipients_FXML);
    }

    public void handleTotalDonations(ActionEvent actionEvent) {
        navigateTo(TotalDonations_FXML);
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


}
