package logicLayer.sensors;

public class OilLevelSensor implements Sensor {

    // 4.0 is full
    private static double maximum = 4.0;
    private double currentAmount;

    public OilLevelSensor(double amount) {
        this.currentAmount = amount;
    }

    @Override
    public double getValue() {
        return currentAmount;
    }

    @Override
    public int status() {
        if (currentAmount <= maximum && currentAmount >= maximum * 0.9) return GOOD;
        else if (currentAmount < 0.9 * maximum && currentAmount >= 0.75 * maximum) return CHECK;
        else return BAD;
    }
}
