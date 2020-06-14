package logicLayer.onboardComputer;

import dataLayer.RouteRepository;
import logicLayer.Route.Kilometrage;
import logicLayer.Route.Route;
import logicLayer.lights.*;
import logicLayer.mirrors.RearViewMirror;
import logicLayer.mirrors.WingMirror;
import logicLayer.sensors.AccumulatorLoadSensor;
import logicLayer.sensors.FuelLevelSensor;
import logicLayer.sensors.OilLevelSensor;
import logicLayer.velocity.Velocity;

/**
 * Glowna klasa projektu, odpowiada za funkcjonalnosc deski rozdzielczej.
 * Obiekt tej klasy przechowuje wszystkie wartosci opisujace stan pojazdu.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class OnboardComputer {
    // Ligts
    private LowBeam lowBeam;
    private FullBeam highBeam;
    private TurnSignal turnSignals;
    private FogLightsBack fogLightsBack;
    private FogLightsFront fogLightsFront;
    private PositionLights positionLights;

    // Mirrors
    private WingMirror wingMirrorLeft;
    private WingMirror wingMirrorRight;
    private RearViewMirror rearViewMirror;

    // Sensors
    private AccumulatorLoadSensor accumulator;
    private OilLevelSensor oilLevel;
    private FuelLevelSensor fuel;

    // Kilometrage
    private Kilometrage totalKilometrage;
    private Kilometrage dailyKilometrage;
    private Kilometrage userKilometrage;
    private Route tmpRoute;

    // Velocity
    private Velocity velocity;

    // Route repository
    private RouteRepository routes;

    /**
     * Konstruktor inicjalizuje obiekty opisujace stan pojazdu.
     */
    public OnboardComputer() {

        // Initializing Ligts
        lowBeam = new LowBeam();
        highBeam = new FullBeam();
        turnSignals = new TurnSignal();
        fogLightsBack = new FogLightsBack();
        fogLightsFront = new FogLightsFront();
        positionLights = new PositionLights();

        // Initializing mirrors
        wingMirrorLeft = new WingMirror();
        wingMirrorRight = new WingMirror();
        rearViewMirror = new RearViewMirror();

        // Initializing sensors
        accumulator = new AccumulatorLoadSensor();
        oilLevel = new OilLevelSensor();
        fuel = new FuelLevelSensor();

        // Mileage
        totalKilometrage = new Kilometrage();
        dailyKilometrage = new Kilometrage(true);
        userKilometrage = new Kilometrage();

        // Initializing velocity
        velocity = new Velocity();

        routes = new RouteRepository();
    }

    public LowBeam getLowBeam() {
        return lowBeam;
    }

    public FullBeam getHighBeam() {
        return highBeam;
    }

    public TurnSignal getTurnSignals() {
        return turnSignals;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setTmpRoute(Route tmpRoute) {
        this.tmpRoute = tmpRoute;
    }

    public Route getTmpRoute() {
        return tmpRoute;
    }

    public WingMirror getWingMirrorLeft() {
        return wingMirrorLeft;
    }

    public WingMirror getWingMirrorRight() {
        return wingMirrorRight;
    }

    public RearViewMirror getRearViewMirror() {
        return rearViewMirror;
    }

    public FuelLevelSensor getFuel() {
        return fuel;
    }

    public AccumulatorLoadSensor getAccumulator() {
        return accumulator;
    }

    public OilLevelSensor getOilLevel() {
        return oilLevel;
    }

    public FogLightsBack getFogLightsBack() {
        return fogLightsBack;
    }

    public FogLightsFront getFogLightsFront() {
        return fogLightsFront;
    }

    public Kilometrage getTotalKilometrage() {
        return totalKilometrage;
    }

    public Kilometrage getDailyKilometrage() {
        return dailyKilometrage;
    }

    public Kilometrage getUserKilometrage() {
        return userKilometrage;
    }

    public PositionLights getPositionLights() {
        return positionLights;
    }

    public RouteRepository getRoutes() {
        return routes;
    }

    /**
     * Metoda odpowiedzialna za obliczanie spalania na podstawie predkosci oraz
     * tego czy pojazd obecnie przyspiesza.
     *
     * @param revs informacja o tym czy pojazd przyspiesza
     * @return średnie spalanie na ostatnim odcinku trasy w <b>l/100Km</b>
     */
    public double fuelConsumption(boolean revs, boolean cruiseControll) {
        double consumed;
        if (revs) {
            consumed = 0.7*FuelLevelSensor.fuelPerOneKm/Velocity.maxVelocity * getVelocity().getCurrentVelocity() + FuelLevelSensor.fuelPerOneKm*3/10;
        } else if (velocity.getCurrentVelocity() > 0.0) {
            consumed = (0.9 + Math.random()*0.4) /100;
            if (cruiseControll) consumed *= 7;
        } else consumed = 0;

        fuel.setFuelAmount(fuel.getValue()-consumed);
        return consumed*100;
    }

    /**
     * Metoda odpowiedzialna za kontrole poziomu naladowania akumulatora.
     *
     * @param isOn czy samochod jest uruchomiony
     */
    public void updateAccumulatorStatus(boolean isOn) {
        if (isOn && (getAccumulator().getValue() < AccumulatorLoadSensor.maxLoad) ) getAccumulator().setCurrentLoad(getAccumulator().getValue()+0.5);
        else if (!isOn && (getTurnSignals().getRightLight().isOn() || getTurnSignals().getLeftLight().isOn() || getLowBeam().isOn() || getHighBeam().isOn() ||
                getPositionLights().isOn() || getFogLightsFront().isOn() || getFogLightsBack().isOn() ) && accumulator.getValue() > 0) {
            getAccumulator().setCurrentLoad(getAccumulator().getValue()-0.5);
        }
    }

    /**
     * Metoda odpowiedzialna za poziom oleju silnikowego.
     *
     * @param isOn czy samochod jest uruchomiony
     */
    public void updateOil(boolean isOn) {
        if (isOn && (getVelocity().getCurrentVelocity() > 0) && oilLevel.getValue() > 0) getOilLevel().setCurrentAmount(getOilLevel().getValue()-0.02);
    }

    /**
     * Metoda odpowiedzialna za swiatla pozycyjne, mijania i drogowe.
     * Jezeli ktores ze swiatel sa wlaczone wszystkie inne sa wylaczane.
     *
     * @param position czy swiatla pozycyjne sa wlaczane
     * @param low czy swiatla mijania sa wlaczane
     * @param full czy swiatla drogowe sa wlaczane
     */
    public void setGroupLights(boolean position, boolean low, boolean full) {
        if ( position) {
            getHighBeam().switchOff();
            getLowBeam().switchOff();
            getPositionLights().switchLight();
        } else if (low) {
            getPositionLights().switchOff();
            getHighBeam().switchOff();
            getLowBeam().switchLight();
        } else if (full) {
            getPositionLights().switchOff();
            getLowBeam().switchOff();
            getHighBeam().switchLight();
        }
    }

    /**
     * Metoda odpowiedzialna za obliczanie przebiegu i jego zmiane.
     */
    public void updateKilometrage() {
        double distance = getVelocity().getCurrentVelocity() * 0.5/3600;
        getTotalKilometrage().setRouteLength( getTotalKilometrage().getRouteLength() + distance);
        getDailyKilometrage().setRouteLength(getDailyKilometrage().getRouteLength() + distance);
        getUserKilometrage().setRouteLength(getUserKilometrage().getRouteLength() + distance);
    }

    /**
     * Metoda odpowiedzialna za resetowanie przebiegu.
     */
    public void resetUserKilometrage() {
        userKilometrage.setRouteLength(0);
    }


}
