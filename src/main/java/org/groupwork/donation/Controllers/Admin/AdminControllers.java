package org.groupwork.donation.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.groupwork.donation.Controllers.Client.parent;
import org.groupwork.donation.Controllers.Donar.Action;

public class AdminControllers<Stage> {

     public Button logout, verify_Accounts,overView,Total_Donars,Total_Clients,Total_Donations;
    public Label label;

  @FXML
    public void logoutClicked(ActionEvent event) {
        
                  }
        
        
        public void verify_AccountsClicked(Action event) throws Exception{
            parent root = FXMLLoader.load (getClass().getResource("verify_Accounts.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
                }
                  public void overViewClicked(Action event) throws Exception{
            parent root =FXMLLoader.load (getClass().getResource("overView.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
                }  public void total_Donarslicked(Action event) throws Exception{
            parent root =FXMLLoader.load (getClass().getResource("Total_Donars.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
                }  public void total_ClientsClicked(Action event) throws Exception{
            parent root =FXMLLoader.load (getClass().getResource("Total_Clients.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
                }
                public void total_DonationsClicked(Action event) throws Exception{
            parent root =FXMLLoader.load (getClass().getResource("Total_Donations.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
                }

                
   
}

