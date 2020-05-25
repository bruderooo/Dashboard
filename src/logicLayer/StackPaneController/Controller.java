package logicLayer.StackPaneController;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Controller {

    @FXML
    public ToggleButton lowBeam, fullBeam;
    public ToggleGroup lowFullBeamsGroup = new ToggleGroup();
    public Button startButton;
    public Label clock, currentVelocity;

    private boolean isOn;

    @FXML
    public void lowBeamAction(){
        //TODO integracja z logika
    }
    public void fullBeamAction() {
        //TODO integracja z logika
    }
    public void startEngine() {

        initClock();
    }

    @FXML
    public void initialize() {
        clock.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        currentVelocity.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        clock.setAlignment(Pos.CENTER_RIGHT);
        currentVelocity.setAlignment(Pos.CENTER_RIGHT);

    }

    private void initClock() {
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            clock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.millis(100)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    }
}
