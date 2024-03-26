package org.groupwork.donation.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.groupwork.donation.Controllers.Donar.Action;


public class RequestsuccefulController<Stage> {

     public Button exitBtn, back_Home;
  
      //If Client does not have anything  else to request should exit 
  @FXML
    public void exitOnClicked(ActionEvent event) {
      Stage stage = (Stage) exitBtn.getScene().getWindow();
      stage.close();
           
        
                  }
        //Returning to Client HomePage

        @FXML
        public void back_HomeOnClicked(Action event) throws Exception{
            parent root = FXMLLoader.load (getClass().getResource("Client.fxml "));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               
                }
   
}

