package interfacesLayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logicLayer.StackPaneController.MainController;
import java.io.IOException;

public class Dashboard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Scenic Dashboard");
        Scene scene = new Scene(root);

        // stage settings
        primaryStage.setScene(scene);
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);
        primaryStage.setResizable(false);

        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            MainController mainController = (MainController) loader.getController();
            try {
                mainController.writeComputerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainController.stopThread();
        });
    }
}
