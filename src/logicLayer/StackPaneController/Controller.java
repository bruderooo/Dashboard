package logicLayer.StackPaneController;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.fxml.FXML;
import logicLayer.onboardComputer.OnboardComputer;
import logicLayer.sensors.FuelLevelSensor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    public ToggleButton lowBeam, fullBeam, mirrorsButton;
    public ToggleGroup lowFullBeamsGroup = new ToggleGroup();
    public Button startButton, leftBlinker, rightBlinker;
    public Label clock, velocity;
    public Pane pane;
    public ProgressBar accumulatorBar, oilBar;
    public Rectangle fuelRect0, fuelRect1, fuelRect2, fuelRect3, fuelRect4, fuelRect5, fuelRect6, fuelRect7;
    public VBox fuelBar;

    private boolean isOn, speedingUp, slowingDown;
    private OnboardComputer computer;
    private Timeline clockTimeline, leftBlinkerTimeline, rightBlinkerTimeline, velocityTimeline;


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

            computer.getFuel().setFuelAmount(FuelLevelSensor.maxFuelAmount);
            updateFuelBar();
            clockON();
            velocityMeterON();


        } else {
            isOn = false;
            computer.getFuel().setFuelAmount(0.0);
            updateFuelBar();
            clockOFF();
            velocityMeterOFF();
            // wylaczanie samochodu
        }
    }

    @FXML
    public void initialize() {
        computer = new OnboardComputer();

        isOn = false;
        speedingUp = false;
        slowingDown = false;


        accumulatorBar.setProgress(0.70);
        oilBar.setProgress(0.80);


        mirrorsButton.setText("Rozłóż\nLusterka");
        mirrorsButton.setAlignment(Pos.CENTER);

        // fill fuel level
        computer.getFuel().setFuelAmount(FuelLevelSensor.maxFuelAmount);

        // color and position in of values in clock and velocity meter
        clock.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        clock.setAlignment(Pos.CENTER_RIGHT);
        velocity.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        velocity.setAlignment(Pos.CENTER_RIGHT);

        // making it so "focus" can not traverse between buttons
        lowBeam.setFocusTraversable(false);
        fullBeam.setFocusTraversable(false);
        startButton.setFocusTraversable(false);
        velocity.setFocusTraversable(false);
        leftBlinker.setFocusTraversable(false);
        rightBlinker.setFocusTraversable(false);
        mirrorsButton.setFocusTraversable(false);

        // clock is left traversable so that there is focus on the main pane so that we can handle KeyPress and KeyRelease but it doesnt affect clock label
        clock.setFocusTraversable(true);

        // setting up timelines for left / right blinker animations
        leftBlinkerTimeline = new Timeline(new KeyFrame(Duration.millis(600), e -> leftBlinker.setStyle("-fx-background-color: #00db08")),
                new KeyFrame(Duration.millis(1200), e -> leftBlinker.setStyle("-fx-background-color: #000000")));
        leftBlinkerTimeline.setCycleCount(Animation.INDEFINITE);
        rightBlinkerTimeline = new Timeline(new KeyFrame(Duration.millis(600), e -> rightBlinker.setStyle("-fx-background-color: #00db08")),
                new KeyFrame(Duration.millis(1200), e -> rightBlinker.setStyle("-fx-background-color: #000000")));
        rightBlinkerTimeline.setCycleCount(Animation.INDEFINITE);

    }

    private void velocityMeterON() {
        velocityTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            // calculate current velocity
            computer.getVelocity().calculate(speedingUp, slowingDown);

            // calculate fuel consumption
            computer.fuelConsumption();

            updateFuelBar();

            DecimalFormat dec = new DecimalFormat("#0.0");
            velocity.setText( dec.format(computer.getVelocity().getCurrentVelocity()) + " km/h");
        }), new KeyFrame(Duration.millis(500)));
        velocityTimeline.setCycleCount(Animation.INDEFINITE);
        velocityTimeline.play();
    }

    private void velocityMeterOFF() {
        velocityTimeline.stop();
        velocity.setText("0.0 km/h");
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

    public void rightBlinkerPress() {
        if( !computer.getTurnSignals().getRightLight().isOn() ) {
            if( computer.getTurnSignals().getLeftLight().isOn() ) {
                leftBlinkerTimeline.stop();
                leftBlinker.setStyle("-fx-background-color: #000000");
            }
            rightBlinkerTimeline.play();

        } else {
            rightBlinkerTimeline.stop();
            rightBlinker.setStyle("-fx-background-color: #000000");
        }
        computer.getTurnSignals().turnRight();
    }

    public void leftBlinkerPress() {
        if( !computer.getTurnSignals().getLeftLight().isOn() ) {
            if( computer.getTurnSignals().getRightLight().isOn() ) {
                rightBlinkerTimeline.stop();
                rightBlinker.setStyle("-fx-background-color: #000000");
            }
            leftBlinkerTimeline.play();
        }
        else {
            leftBlinkerTimeline.stop();
            leftBlinker.setStyle("-fx-background-color: #000000");
        }
        computer.getTurnSignals().turnLeft();
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
            speedingUp = true;
        }
        if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
            slowingDown = true;
        }
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
            speedingUp = false;
        }
        if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
            slowingDown = false;
        }
    }

    public void mirrorsButtonAction(ActionEvent actionEvent) {
        if (mirrorsButton.isSelected()){
            computer.getRearViewMirror().openMirror();
            computer.getWingMirrorLeft().openMirror();
            computer.getWingMirrorRight().openMirror();
            mirrorsButton.setText("Złóż\nLusterka");
        } else {
            computer.getRearViewMirror().closeMirror();
            computer.getWingMirrorLeft().closeMirror();
            computer.getWingMirrorRight().closeMirror();
            mirrorsButton.setText("Rozłóż\nLusterka");
        }
    }

    public void updateFuelBar() {

        if (computer.getFuel().getValue() <= 0.0 ) {
            fuelRect0.setFill(Color.web("#616160"));
        } else {
            fuelRect0.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 10.0 ) {
            fuelRect1.setFill(Color.web("#616160"));
        } else {
            fuelRect1.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 20.0 ) {
            fuelRect2.setFill(Color.web("#616160"));
        } else {
            fuelRect2.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 30.0 ) {
            fuelRect3.setFill(Color.web("#616160"));
        } else {
            fuelRect3.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 40.0 ) {
            fuelRect4.setFill(Color.web("#616160"));
        } else {
            fuelRect4.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 50.0 ) {
            fuelRect5.setFill(Color.web("#616160"));
        } else {
            fuelRect5.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= 60.0 ) {
            fuelRect6.setFill(Color.web("#616160"));
        } else {
            fuelRect6.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <=70.0 ) {
            fuelRect7.setFill(Color.web("#616160"));
        } else {
            fuelRect7.setFill(Color.web("#7B91F2"));
        }
    }

}
