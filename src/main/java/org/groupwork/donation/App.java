package org.groupwork.donation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.groupwork.donation.Models.Model;

import java.io.InputStream;

public class App extends Application {

    private final String ICON_IMAGE = "/Images/donation_app.png";
    InputStream ImageClass = getClass().getResourceAsStream(ICON_IMAGE);

    @Override
    public void start(Stage stage) throws Exception {
        Model.initializeDB();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Auth/Authentication.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(ImageClass));
        stage.setTitle("Welcome Screen");
        stage.setScene(scene);
        stage.show();
    }
}
