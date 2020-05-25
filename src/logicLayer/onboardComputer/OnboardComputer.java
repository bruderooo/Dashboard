package logicLayer.onboardComputer;

import logicLayer.lights.FullBeam;
import logicLayer.lights.LowBeam;
import logicLayer.lights.TurnSignal;
import logicLayer.mirrors.RearViewMirror;
import logicLayer.mirrors.WingMirror;
import logicLayer.sensors.AccumulatorLoadSensor;
import logicLayer.sensors.OilLevelSensor;
import logicLayer.sensors.OilTemperatureSensor;

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
        oilLevel = new OilLevelSensor(3.8);
        oilTemperature = new OilTemperatureSensor(20.0);
    }

    public LowBeam getLowBeam() {
        return lowBeam;
    }

    public FullBeam getHighBeam() {
        return highBeam;
    }
}
