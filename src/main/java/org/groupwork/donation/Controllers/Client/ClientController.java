package org.groupwork.donation.Controllers.Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.groupwork.donation.Controllers.Donar.Action;

public class ClientController<Stage> {

    @FXML
    public Button request;

    public void requestClickeOn(Action event) throws Exception {
            parent root = FXMLLoader.load (getClass().getResource("RequestSuccessful.fxml"));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    


}
