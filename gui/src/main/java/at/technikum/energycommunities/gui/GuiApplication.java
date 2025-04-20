package at.technikum.energycommunities.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/fxml/main.fxml")
        );
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Energy Communities GUI");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
