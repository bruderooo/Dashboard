package logicLayer.onboardComputer;

import logicLayer.lights.*;
import logicLayer.mirrors.RearViewMirror;
import logicLayer.mirrors.WingMirror;
import logicLayer.sensors.AccumulatorLoadSensor;
import logicLayer.sensors.FuelLevelSensor;
import logicLayer.sensors.OilLevelSensor;
import logicLayer.sensors.OilTemperatureSensor;
import logicLayer.velocity.Velocity;

public class OnboardComputer {
    // Initializing Ligts
    private LowBeam lowBeam;
    private FullBeam highBeam;
    private TurnSignal turnSignals;
    private FogLightsBack fogLightsBack;
    private FogLightsFront fogLightsFront;
    private PositionLights positionLights;

    // Initializing mirrors
    private WingMirror wingMirrorLeft;
    private WingMirror wingMirrorRight;
    private RearViewMirror rearViewMirror;

    // Initializing sensors
    private AccumulatorLoadSensor accumulator;
    private OilLevelSensor oilLevel;
    private OilTemperatureSensor oilTemperature;
    private FuelLevelSensor fuel;

    // Initializing velocity
    private Velocity velocity;


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
        oilTemperature = new OilTemperatureSensor(20.0);
        fuel = new FuelLevelSensor();

        // Initializing velocity
        velocity = new Velocity();
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

    public PositionLights getPositionLights() {
        return positionLights;
    }

    public double fuelConsumption(boolean revs) {
        double consumed;
        if (revs) {
            consumed = 0.7*FuelLevelSensor.fuelPerOneKm/Velocity.maxVelocity * getVelocity().getCurrentVelocity() + FuelLevelSensor.fuelPerOneKm*3/10;
        } else if (velocity.getCurrentVelocity() > 0.0) {
            consumed = (0.9 + Math.random()*0.4) /100;
        } else consumed = 0;



        fuel.setFuelAmount(fuel.getValue()-consumed);
        return consumed*100;
    }

    public void updateAccumulatorStatus(boolean isOn) {
        if (isOn && (getAccumulator().getValue() < AccumulatorLoadSensor.maxLoad) ) getAccumulator().setCurrentLoad(getAccumulator().getValue()+0.5);
        else if (!isOn && (getTurnSignals().getRightLight().isOn() || getTurnSignals().getLeftLight().isOn() || getLowBeam().isOn() || getHighBeam().isOn() ||
                getPositionLights().isOn() || getFogLightsFront().isOn() || getFogLightsBack().isOn() ) && accumulator.getValue() > 0) {
            getAccumulator().setCurrentLoad(getAccumulator().getValue()-0.5);
        }
    }

    public void updateOil(boolean isOn) {
        if (isOn && (getVelocity().getCurrentVelocity() > 0) && oilLevel.getValue() > 0) getOilLevel().setCurrentAmount(getOilLevel().getValue()-0.02);
    }

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
}
