package org.groupwork.donation.Controllers.Donar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class DonarController<Stage, parent> {

    @FXML
    public Button donateBtn;
     
     //
     @FXML
    public void donateOnClicked(Action event) throws Exception {
         parent root = FXMLLoader.load(getClass().getResource("DonationSuccessful.fxml"));
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         Scene scene = new Scene((Parent) root);
         stage.setScene(scene);
         stage.show();

     }

    


}
