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
import logicLayer.onboardComputer.OnboardComputer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    public ToggleButton lowBeam, fullBeam;
    public ToggleGroup lowFullBeamsGroup = new ToggleGroup();
    public Button startButton;
    public Label clock, currentVelocity;

    private boolean isOn;
    private OnboardComputer computer;
    private Timeline clockTimeline;

    @FXML
    public void lowBeamAction(){
        updateLowFullBeams();
    }
    public void fullBeamAction() {
        updateLowFullBeams();
    }
    public void startEngine() {
        if (!isOn) {
            isOn = true;

            clockON();


        } else {
            isOn = false;
            clockOFF();
            //TODO wszystkie swiatla off ???
            // wylaczanie samochodu
        }
    }

    @FXML
    public void initialize() {
        computer = new OnboardComputer();
        isOn = false;

        clock.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        clock.setAlignment(Pos.CENTER_RIGHT);
        currentVelocity.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        currentVelocity.setAlignment(Pos.CENTER_RIGHT);

    }

    private void clockON() {
        clockTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            clock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.millis(100)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    }

    private void clockOFF() {
        clockTimeline.stop();
        clock.setText("00:00:00");
    }

    private void updateLowFullBeams() {
        if (computer.getHighBeam().isOn() != fullBeam.isSelected()) {
            computer.getHighBeam().switchLight();
        }
        if (computer.getLowBeam().isOn() != lowBeam.isSelected()) {
            computer.getLowBeam().switchLight();
        }
    }
}
