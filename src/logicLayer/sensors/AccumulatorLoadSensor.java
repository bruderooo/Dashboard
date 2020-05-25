package logicLayer.sensors;

public class AccumulatorLoadSensor implements Sensor {

    private static double maxLoad = 1.0;
    private double currentLoad;

    public AccumulatorLoadSensor() {
        this.currentLoad = 1.0;
    }

    @Override
    public double getValue() {
        return currentLoad;
    }

    @Override
    public int status() {
        if (currentLoad <= maxLoad && currentLoad >= 0.95 * maxLoad) return GOOD;
        if (currentLoad < 0.9 * maxLoad && currentLoad >= 0.75 * maxLoad) return CHECK;
        else return BAD;
    }
}
