package interfacesLayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logicLayer.lights.HighBeam;
import logicLayer.lights.LowBeam;

import java.io.IOException;

public class Dashboard extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Scenic Dashboard");

        Scene scene = new Scene(root);

        // stage settings
        primaryStage.setScene(scene);
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    public void initialize() {
        HighBeam highBeam = new HighBeam();
        LowBeam lowBeam = new LowBeam();

    }
}
