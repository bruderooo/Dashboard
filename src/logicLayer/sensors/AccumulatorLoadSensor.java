package logicLayer.sensors;

public class AccumulatorLoadSensor implements Sensor {

    private static double maxLoad = 1.0;
    private double currentLoad;

    public AccumulatorLoadSensor(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public double getValue() {
        return currentLoad;
    }

    @Override
    public int status() {
        if (currentLoad <= maxLoad && currentLoad >= 0.95 * maxLoad) return 0;
        if (currentLoad < 0.9 * maxLoad && currentLoad >= 0.75 * maxLoad) return 1;
        else return 2;
    }
}
