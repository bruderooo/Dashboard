public class OilTemperatureSensor implements Sensor {

    private static double maximumTemperature = 260.0;
    private double currentTemperature;

    public OilTemperatureSensor(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    @Override
    public double getValue() {
        return currentTemperature;
    }

    @Override
    public int status() {
        if (currentTemperature <= maximumTemperature * 0.9 && currentTemperature >= maximumTemperature * 0.7) return 1;
        else if (currentTemperature < 0.7 * maximumTemperature && currentTemperature >= 0.05 * maximumTemperature) return 0;
        else return 2;
    }
}
