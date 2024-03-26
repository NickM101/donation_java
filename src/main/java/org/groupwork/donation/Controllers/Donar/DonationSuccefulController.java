package org.groupwork.donation.Controllers.Donar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.Node;

public class DonationSuccessfulController {


     public Button exitBtn, back_Home;

      //If Doner does not have anything  else to donate should exit
  @FXML
    public void exitOnClicked(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
          // stage.close;


                  }

        //Returning to Doner HomePage

        @FXML
        public <parent, Action> void back_HomeOnClicked(Action event) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("Doner.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }

}

