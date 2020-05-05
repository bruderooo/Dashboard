package logicLayer.sensors;

public class OilLevelSensor implements Sensor {

    private static double maximum = 4.0;
    private double currentAmount;

    public OilLevelSensor(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public double getValue() {
        return currentAmount;
    }

    @Override
    public int status() {
        if (currentAmount <= maximum && currentAmount >= maximum * 0.9) return 0;
        else if (currentAmount < 0.9 * maximum && currentAmount >= 0.75 * maximum) return 1;
        else return 2;
    }
}
