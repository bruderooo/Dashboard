package logicLayer.StackPaneController;

import dataLayer.ConnectToSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logicLayer.Route.Route;
import logicLayer.onboardComputer.OnboardComputer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class RoutesWindowController {

    @FXML
    public TextField startCityTextField, endCityTextField;
    public Label routeLabel0, routeLabel1, routeLabel2, routeLabel3, routeLabel4, routeLabel5, routeLabel6, routeLabel7;
    public Button deleteAllRoutesButton;

    private OnboardComputer computer;
    private ArrayList<Label> labels = new ArrayList<>();


    public void initialize() {

        labels.add(routeLabel0);
        labels.add(routeLabel1);
        labels.add(routeLabel2);
        labels.add(routeLabel3);
        labels.add(routeLabel4);
        labels.add(routeLabel5);
        labels.add(routeLabel6);
        labels.add(routeLabel7);
    }

    public void setComputer(OnboardComputer computer) {
        this.computer = computer;
    }

    public void startRouteButton() {
        computer.setTmpRoute(new Route(computer.getTotalKilometrage().getRouteLength(), startCityTextField.getText()));
        startCityTextField.clear();
    }

    public void endRouteButton() throws SQLException {
        if (computer.getTmpRoute() != null) {
            computer.getTmpRoute().endRoute(endCityTextField.getText());
            computer.getTmpRoute().setRouteLength(computer.getTotalKilometrage().getRouteLength() - computer.getTmpRoute().getRouteLength());
            computer.getRoutes().add(computer.getTmpRoute());
            setRouteLabels();
            ConnectToSQL sql = new ConnectToSQL();
            sql.addRoute(computer.getTmpRoute());
            computer.setTmpRoute(null);
        }
        endCityTextField.clear();
    }

    public void setRouteLabels() {
        for (int i = 0; i < 8; i++) {
            labels.get(i).setText("");
        }
        for (int i = 0; i < computer.getRoutes().size(); i++) {
            labels.get(i).setText(computer.getRoutes().get(i).toString());
        }
    }

    public void deleteAllRoutesButtonAction() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuń wszystkie trasy");
        alert.setHeaderText("Czy jesteś pewien, że chcesz usunąć wszystkie trasy?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            computer.getRoutes().clear();
            setRouteLabels();
            ConnectToSQL sql = new ConnectToSQL();
            sql.clearDatabase();
        }
    }
}
