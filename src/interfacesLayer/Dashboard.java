package interfacesLayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Scenic Dashboard");

        Scene scene = new Scene(root, 1280, 400);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {

            }
        });

        primaryStage.setScene(scene);

        // Lock main stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
