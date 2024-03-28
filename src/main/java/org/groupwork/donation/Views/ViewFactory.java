package org.groupwork.donation.Views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.groupwork.donation.Controllers.Donor.DonorController;

public class ViewFactory {

    private final StringProperty adminSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane verifyAccountView;
    private AnchorPane totalDonorsView;
    private AnchorPane totalRecipientsView;
    private AnchorPane totalDonationsView;

    public ViewFactory(){
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    };

    public StringProperty getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null){
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/fxml/Admin/OverviewDash.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getVerifyAccountsView() {
        if(verifyAccountView == null){
            try {
                verifyAccountView = new FXMLLoader(getClass().getResource("/fxml/Admin/VerifyAccount.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return verifyAccountView;
    }

    public AnchorPane getTotalDonorsView() {
        if(totalDonorsView == null){
            try {
                totalDonorsView = new FXMLLoader(getClass().getResource("/fxml/Admin/TotalDonors.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalDonorsView;
    }
    public AnchorPane getTotalRecipientsView() {
        if(totalRecipientsView == null){
            try {
                totalRecipientsView = new FXMLLoader(getClass().getResource("/fxml/Admin/TotalRecipients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalRecipientsView;
    }
    public AnchorPane getTotalDonationsView() {
        if(totalDonationsView == null){
            try {
                totalDonationsView = new FXMLLoader(getClass().getResource("/fxml/Admin/TotalDonations.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalDonationsView;
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Auth/Login.fxml"));
        createStage(loader);
    }

    public void showDonorWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Donor/Donor.fxml"));
        DonorController donorController = new DonorController();
        loader.setController(donorController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Donation");
        stage.show();
    }


}
