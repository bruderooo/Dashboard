package logicLayer.sensors;

public class OilTemperatureSensor implements Sensor {

    private static double maximumTemperature = 260.0;
    private double currentTemperature;

    public OilTemperatureSensor(double temperature) {
        this.currentTemperature = temperature;
    }

    @Override
    public double getValue() {
        return currentTemperature;
    }

    @Override
    public int status() {
        if (currentTemperature <= maximumTemperature * 0.9 && currentTemperature >= maximumTemperature * 0.7) return CHECK;
        else if (currentTemperature < 0.7 * maximumTemperature && currentTemperature >= -5.0) return GOOD;
        else return BAD;
    }
}
