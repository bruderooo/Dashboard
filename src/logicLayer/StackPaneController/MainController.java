package logicLayer.StackPaneController;

import dataLayer.ConnectToSQL;
import dataLayer.SerializationXML;
import interfacesLayer.MenuWindows;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import logicLayer.onboardComputer.OnboardComputer;
import logicLayer.sensors.AccumulatorLoadSensor;
import logicLayer.sensors.FuelLevelSensor;
import logicLayer.sensors.OilLevelSensor;
import logicLayer.sensors.Sensor;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

/**
 * Klasa kontrolera dla glownego okna aplikacji.
 * Przechowuje obiekty reprezentujace elementy interfejsu graficznego,
 * oraz metody, ktore sa przez nie wywolywane.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class MainController {

    @FXML
    public ToggleButton lowBeam, fullBeam, mirrorsButton, positionLights, fogLightsBack, fogLightsFront, cruiseControllButton;
    public ToggleGroup lightsGroup = new ToggleGroup();
    public Button startButton, leftBlinker, rightBlinker, userKilometrageResetButton;
    public Label clock, velocity, fuelConsumptionLabel, totalKilometrage, dailyKilometrage, usersKilometrage;
    public Pane pane;
    public ProgressBar accumulatorBar, oilBar;
    public Rectangle fuelRect0, fuelRect1, fuelRect2, fuelRect3, fuelRect4, fuelRect5, fuelRect6, fuelRect7;
    public VBox fuelBar;
    public ImageView accImg0,accImg1,accImg2,oilImg0, oilImg1,oilImg2;


    private boolean carOn, appOn = true, speedingUp = false, slowingDown = false, cruiseControll = false;
    private OnboardComputer computer;
    private Timeline clockTimeline, leftBlinkerTimeline, rightBlinkerTimeline, velocityTimeline, kilometrageTimeline;
    private DecimalFormat dec = new DecimalFormat("#0.0");
    private ConnectToSQL sql;

    /**
     * Metoda odpowiedzialna za inicjalizacje kontrolera, wywolywana po tym jak jego glowny element
     * zostal odpowiedznio przetworzony.
     * Tworzy nowy watek odpowiedzialny za regularne wykonywanie obliczen oraz zapisywanie danych
     * komputera pokladowego co minute do pliku.
     *
     * @throws SQLException wyjatek sql
     *
     * @see <a href="https://openjfx.io/javadoc/11/javafx.fxml/javafx/fxml/Initializable.html#initialize(java.net.URL,java.util.ResourceBundle)">initialize</a>
     */
    @FXML
    public void initialize() throws SQLException, IOException {
        computer = new OnboardComputer();
        sql = new ConnectToSQL();
        sql.getAllRoutes(computer.getRoutes());

        SerializationXML.readComputerData(computer);

        Thread updatingThread = new Thread( () -> {
            int counter = 0;
            while (appOn) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
                // Once evety minute it saves computer data to xml
                if (counter%120 == 0) {
                    counter = 0;
                    try {
                        writeComputerData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                updateSensors();
                computer.updateKilometrage();
            }

        });

        updatingThread.start();

        fadeOilControls();
        fadeAccControls();
        accImg0.setOpacity(100);
        oilImg0.setOpacity(100);

        fadeOilControls();
        fadeAccControls();
        accImg0.setOpacity(100);
        oilImg0.setOpacity(100);

        carOn = false;
        speedingUp = false;
        slowingDown = false;


        mirrorsButton.setText("Rozłóż\nLusterka");
        mirrorsButton.setAlignment(Pos.CENTER);

        // color and position in of values in clock and velocity meter
        clock.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        clock.setAlignment(Pos.CENTER_RIGHT);
        velocity.setBackground(new Background(new BackgroundFill(Color.rgb(150,150,150), CornerRadii.EMPTY, Insets.EMPTY)));
        velocity.setAlignment(Pos.CENTER_RIGHT);
        fuelConsumptionLabel.setAlignment(Pos.CENTER_RIGHT);

        // making it so "focus" can not traverse between buttons
        lowBeam.setFocusTraversable(false);
        fogLightsBack.setFocusTraversable(false);
        fogLightsBack.setFocusTraversable(false);
        positionLights.setFocusTraversable(false);
        fullBeam.setFocusTraversable(false);
        startButton.setFocusTraversable(false);
        velocity.setFocusTraversable(false);
        leftBlinker.setFocusTraversable(false);
        rightBlinker.setFocusTraversable(false);
        mirrorsButton.setFocusTraversable(false);
        cruiseControllButton.setFocusTraversable(false);
        userKilometrageResetButton.setFocusTraversable(false);


        // clock is left traversable so that there is focus on the main pane so that we can handle KeyPress and KeyRelease but it doesnt affect clock label
        clock.setFocusTraversable(true);

        // setting up timelines for left / right blinker animations
        leftBlinkerTimeline = new Timeline(new KeyFrame(Duration.millis(600), e -> leftBlinker.setStyle("-fx-background-color: #00db08")),
                new KeyFrame(Duration.millis(1200), e -> leftBlinker.setStyle("-fx-background-color: #000000")));
        leftBlinkerTimeline.setCycleCount(Animation.INDEFINITE);
        rightBlinkerTimeline = new Timeline(new KeyFrame(Duration.millis(600), e -> rightBlinker.setStyle("-fx-background-color: #00db08")),
                new KeyFrame(Duration.millis(1200), e -> rightBlinker.setStyle("-fx-background-color: #000000")));
        rightBlinkerTimeline.setCycleCount(Animation.INDEFINITE);

        kilometrageTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            totalKilometrage.setText(dec.format(computer.getTotalKilometrage().getRouteLength()) + " KM" );
            dailyKilometrage.setText(dec.format(computer.getDailyKilometrage().getRouteLength()) + " KM");
            usersKilometrage.setText(dec.format(computer.getUserKilometrage().getRouteLength()) + " KM");
        }), new KeyFrame(Duration.millis(500)));
        kilometrageTimeline.setCycleCount(Animation.INDEFINITE);

        // Timeline for showing velocity animation
        velocityTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            velocity.setText( dec.format(computer.getVelocity().getCurrentVelocity()) + " km/h");
            fuelConsumptionLabel.setText(dec.format(computer.fuelConsumption(speedingUp, cruiseControll)) + " L/100KM");
        }), new KeyFrame(Duration.millis(500)));
        velocityTimeline.setCycleCount(Animation.INDEFINITE);

        // Timeline for clock animation
        clockTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            clock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.millis(100)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za swiatla mijania.
     */
    public void lowBeamAction(){
        computer.setGroupLights(false, true, false);
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za swiatla drogowe.
     */
    public void fullBeamAction() {
        computer.setGroupLights(false, false, true);
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za swiatla pozycyjne.
     */
    public void positionLightsAction() {
        computer.setGroupLights(true, false, false);
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za swiatla przeciwmglowe tylne.
     */
    public void fogLightsBackAction() {
        computer.getFogLightsBack().switchLight();
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za swiatla przeciwmglowe przednie.
     */
    public void fogLightsFrontAction() {
        computer.getFogLightsFront().switchLight();
    }

    /**
     * Metoda wywolywana przy wcisnieciu przycisku odpowiedzialnego za uruchamianie / gaszenie samochodu.
     */
    public void startEngine() {
        if (!carOn && computer.getAccumulator().getValue() > 0 && computer.getOilLevel().getValue() > 0) {
            carOn = true;

            updateFuelBar();
            clockON();
            velocityMeterON();
            kilometrageTimeline.play();

        } else {
            carOn = false;
            cruiseControll = false;
            cruiseControllButton.setSelected(false);
            updateFuelBar();
            clockOFF();
            velocityMeterOFF();
            kilometrageTimeline.stop();
            totalKilometrage.setText("0.0 KM");
            dailyKilometrage.setText("0.0 KM");
            usersKilometrage.setText("0.0 KM");

            fadeOilControls();
            fadeAccControls();
            accImg0.setOpacity(100);
            oilImg0.setOpacity(100);
        }
    }

    /**
     * Metoda odpowiedzialna za rozpoczecie animacji pokazujacej obecna predkosc.
     */
    private void velocityMeterON() {
        velocityTimeline.play();
    }

    /**
     * Metoda odpowiedzialna za przerwanie animacji pokazujacej obecna predkosc i ustawienie wartosci predkosciomieza na 0.0 km/h.
     */
    private void velocityMeterOFF() {
        velocityTimeline.stop();
        velocity.setText("0.0 km/h");
    }

    /**
     * Metoda odpowiedzialna za rozpoczecie animacji pokazujacej obecny czas.
     */
    private void clockON() {
        clockTimeline.play();
    }

    /**
     * Metoda odpowiedzialna za przerwanie animacji pokazujacej obecny czas i ustawienie wartosci na wyswietlaczu na 00:00:00.
     */
    private void clockOFF() {
        clockTimeline.stop();
        clock.setText("00:00:00");
    }

    public boolean isAppOn() {
        return appOn;
    }

    /**
     * Metoda wywolywana przez przycisniecie prawego kierunkowskazu, strzalki w prawo lub D.
     * Wlacza prawy kierunkowskaz i wylacza lewy jezeli ten jest wlaczony.
     */
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

    /**
     * Metoda wywolywana przez przycisniecie przycisku lewego kierunkowskazu, strzalki w lewo lub A.
     * Wlacza lewy kierunkowskaz i wylacza lewy jezeli ten byl wlaczony.
     */
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

    /**
     * Metoda wczytujaca wcisniety przycisk z klawiatury i zmieniajaca odpowiedznie pola informujace
     * o tym czy pojazd przyspiesza czy hamuje.
     *
     * @param keyEvent zdazenie wywolane przez wcisniecie przycisku
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
            speedingUp = true;
        }
        if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
            slowingDown = true;
            cruiseControll = false;
            cruiseControllButton.setSelected(false);
        }
    }

    public OnboardComputer getComputer() {
        return computer;
    }

    /**
     * Metoda wczytujaca zwolniony przycisk z klawiatury i zmieniajaca wartosci pol informujacych o tym
     * czy pojazd przyspiesza czy hamuje.
     * Moze rowniez wlaczyc kierunkowskaz.
     *
     * @param keyEvent zdazenie wywolane przez zwolnienie przycisku
     */
    public void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
            speedingUp = false;
        }
        if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
            slowingDown = false;
        }
        if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
            rightBlinkerPress();
        }
        if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
            leftBlinkerPress();
        }
    }

    /**
     * Metoda wykonywana przez przycisniecie przycisku obslugi lusterek.
     * Sklada / rozklada lusterka pojazdu.
     */
    public void mirrorsButtonAction() {
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

    /**
     * Metoda odpowiedzialna za przedstawianie ilosci paliwa w graficznym interfejsie uzytkownika.
     */
    public void updateFuelBar() {
        if (computer.getFuel().getValue() <= 0.0 ) {
            fuelRect0.setFill(Color.web("#616160"));
        } else {
            fuelRect0.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*1/8 ) {
            fuelRect1.setFill(Color.web("#616160"));
        } else {
            fuelRect1.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*2/8 ) {
            fuelRect2.setFill(Color.web("#616160"));
        } else {
            fuelRect2.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*3/8 ) {
            fuelRect3.setFill(Color.web("#616160"));
        } else {
            fuelRect3.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*4/8 ) {
            fuelRect4.setFill(Color.web("#616160"));
        } else {
            fuelRect4.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*5/8 ) {
            fuelRect5.setFill(Color.web("#616160"));
        } else {
            fuelRect5.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <= FuelLevelSensor.maxFuelAmount*6/8 ) {
            fuelRect6.setFill(Color.web("#616160"));
        } else {
            fuelRect6.setFill(Color.web("#7B91F2"));
        }
        if (computer.getFuel().getValue() <=FuelLevelSensor.maxFuelAmount*7/8 ) {
            fuelRect7.setFill(Color.web("#616160"));
        } else {
            fuelRect7.setFill(Color.web("#7B91F2"));
        }
    }

    /**
     * Metoda ustawiajaca nieprzezroczystosc kontrolek akumulatora na 0.
     */
    public void fadeAccControls() {
        accImg0.setOpacity(0);
        accImg1.setOpacity(0);
        accImg2.setOpacity(0);
    }

    /**
     * Metoda ustawiajaca nieprzezroczystosc kontrolek oleju na 0;
     */
    public void fadeOilControls() {
        oilImg0.setOpacity(0);
        oilImg1.setOpacity(0);
        oilImg2.setOpacity(0);
    }

    /**
     * Metoda ustawiajaca kontrolki oleju i akumulatora na odpowiednie w zaleznosci od stanu ich czujnikow.
     */
    public void updateControls() {
        fadeAccControls();
        fadeOilControls();

        if (computer.getAccumulator().status() == Sensor.CHECK) accImg1.setOpacity(100);
        else if (computer.getAccumulator().status() == Sensor.BAD)  accImg2.setOpacity(100);
        else accImg0.setOpacity(100);

        if (computer.getOilLevel().status() == Sensor.CHECK) oilImg1.setOpacity(100);
        else if (computer.getOilLevel().status() == Sensor.BAD) oilImg2.setOpacity(100);
        else oilImg0.setOpacity(100);
    }

    /**
     * Metoda odpowiedzialna za zmiany w paskach postepu i reprezentacji zbiornika paliwa.
     */
    public void updateBars() {
        accumulatorBar.setProgress(computer.getAccumulator().getValue()/100);
        oilBar.setProgress(computer.getOilLevel().getValue()/100);
        updateFuelBar();
    }

    /**
     * Metoda odpowiedzialna za wywolanie metod zmieniajacych stan pojazdu w zaleznosci interakcji z uzytkownikiem,
     * oraz metod odpowiedzialnych za reprezentacje stanu pojazdu przez interfejs graficzny.
     */
    public void  updateSensors() {
        computer.getVelocity().calculate(speedingUp, slowingDown, cruiseControll);
        computer.updateAccumulatorStatus(carOn);
        computer.updateOil(carOn);
        updateControls();
        updateBars();
        if (computer.getFuel().getValue() <= 0) {
            startEngine();
        }
    }

    /**
     * Metoda odpowiedzialna za zmiane pola od ktorego zalezy czy watek utworzony do obliczen dalej funkcjonuje.
     * Jest wywolywana przy zamykaniu aplikacji.
     */
    public void stopThread() {
        appOn = false;
    }

    /**
     * Metoda uzupelniajaca poziom paliwa.
     */
    public void refillFuel() {
        computer.getFuel().setFuelAmount(FuelLevelSensor.maxFuelAmount);
    }

    /**
     * Metoda uzupelniajaca poziom oleju.
     */
    public void refillOil() {
        computer.getOilLevel().setCurrentAmount(OilLevelSensor.maximum);
    }

    /**
     * Metoda ladujaca akumulator.
     */
    public void refillAccumulator() {
        computer.getAccumulator().setCurrentLoad(AccumulatorLoadSensor.maxLoad);
    }

    /**
     * Metoda wywolywana przy wybraniu z paska menu informacje opcji Instrukcja.
     */
    public void menuInformationsInstructions() {
        MenuWindows.displayProgramInfo("Instrukcja aplikacji");
    }

    /**
     * Metoda wywolywana przy wybraniu z paska menu informacje opcji O pojezdzie.
     */
    public void menuInformationsAboutVehicle() {
        MenuWindows.displayAutoInfo("Informacje o samochodzie");
    }

    /**
     * Metoda odpowiedzialna za wywolanie metody zapisujacej dane do pliku xml.
     *
     * @throws IOException wyjatek wejscia wyjscia
     */
    public void writeComputerData() throws IOException {
        SerializationXML.writeComputerData(computer);
    }

    /**
     * Metoda wykonywana przy wcisnieciu przycisku Wyzeruj przebieg.
     * Pyta uzytkownika o potwierdzenie i w zaleznosci od niego zeruje przebieg.
     */
    public void userKilometrageResetButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyzeruj przebieg");
        alert.setHeaderText("Czy jesteś pewny, że chcesz wyzerować przebieg?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            computer.resetUserKilometrage();
        }
    }

    /**
     * Metoda wykonywana przy wcisnieciu przycisku Tempomat.
     */
    public void cruiseControllButtonAction() {
        if (carOn) {
            cruiseControll = !cruiseControll;
        } else cruiseControllButton.setSelected(false);

    }

    /**
     * Metoda wykonywana przy wybraniu z menu tras opcji zarzadzaj.
     * Otwiera nowe okno w ktorym mozna dodawac trasy lub usunac wszystkie trasy.
     *
     * @throws IOException wyjatek wejscia wyjscia
     */
    public void routesMenuAction() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../interfacesLayer/routesWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Trasy");
        stage.setHeight(500);
        stage.setWidth(1000);
        stage.setResizable(false);

        RoutesWindowController routesController = fxmlLoader.getController();
        routesController.setComputer(computer);
        routesController.setRouteLabels();
        stage.setScene(scene);
        stage.show();
    }
}
