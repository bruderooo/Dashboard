package logicLayer.onboardComputer;

import logicLayer.lights.FullBeam;
import logicLayer.lights.LowBeam;
import logicLayer.lights.TurnSignal;
import logicLayer.mirrors.RearViewMirror;
import logicLayer.mirrors.WingMirror;
import logicLayer.sensors.AccumulatorLoadSensor;
import logicLayer.sensors.FuelLevelSensor;
import logicLayer.sensors.OilLevelSensor;
import logicLayer.sensors.OilTemperatureSensor;
import logicLayer.velocity.Velocity;

import javax.swing.*;

public class OnboardComputer {
    // Initializing Ligts
    private LowBeam lowBeam;
    private FullBeam highBeam;
    private TurnSignal turnSignals;

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

    public OilTemperatureSensor getOilTemperature() {
        return oilTemperature;
    }

    public double fuelConsumption(boolean revs) {
        double consumed;
        if ( (getVelocity().getCurrentVelocity() <= 60) && revs) {
            consumed = FuelLevelSensor.fuelPerOneKm;
        } else if ( (getVelocity().getCurrentVelocity() > 60 && getVelocity().getCurrentVelocity() <= 90) && revs ) {
            consumed = FuelLevelSensor.fuelPerOneKm*7/10;
        } else if ( (getVelocity().getCurrentVelocity() > 90) && revs) {
            consumed= FuelLevelSensor.fuelPerOneKm;
        } else if (getVelocity().getCurrentVelocity() == 0 && !revs ) {
            consumed = 0;
        }
        else consumed = FuelLevelSensor.fuelPerOneKm/6;

        fuel.setFuelAmount(fuel.getValue()-consumed);
        return consumed*100;
    }

    public void updateAccumulatorStatus(boolean isOn) {
        if (isOn && (getAccumulator().getValue() < AccumulatorLoadSensor.maxLoad) ) getAccumulator().setCurrentLoad(getAccumulator().getValue()+0.1);
        else if (!isOn && (getTurnSignals().getRightLight().isOn() || getTurnSignals().getLeftLight().isOn() || getLowBeam().isOn() || getHighBeam().isOn())) {
            getAccumulator().setCurrentLoad(getAccumulator().getValue()-0.1);
        }
    }

    public void updateOil(boolean isOn) {
        if (isOn && (getVelocity().getCurrentVelocity() > 0) ) getOilLevel().setCurrentAmount(getOilLevel().getValue()-0.005);
    }
}
