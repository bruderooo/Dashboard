package logicLayer.sensors;

public class AccumulatorLoadSensor implements Sensor {

    public static double maxLoad = 100.0;
    private double currentLoad;

    public AccumulatorLoadSensor() {
        this.currentLoad = maxLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public double getValue() {
        return currentLoad;
    }

    @Override
    public int status() {
        if (currentLoad <= maxLoad && currentLoad >= 0.6 * maxLoad) return GOOD;
        if (currentLoad < 0.6 * maxLoad && currentLoad >= 0.1 * maxLoad) return CHECK;
        else return BAD;
    }
}
