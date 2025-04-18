package at.technikum.energycommunities.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GuiApplication extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("GUI 界面启动成功！");
        Scene scene = new Scene(label, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Energy Communities GUI");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
