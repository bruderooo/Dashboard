package logicLayer.sensors;

public class OilLevelSensor implements Sensor {

    public static double maximum = 100.0;
    private double currentAmount;

    public OilLevelSensor() {
        this.currentAmount = maximum;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public double getValue() {
        return currentAmount;
    }

    @Override
    public int status() {
        if (currentAmount <= maximum && currentAmount >= maximum * 0.5) return GOOD;
        else if (currentAmount < 0.5 * maximum && currentAmount >= 0.2 * maximum) return CHECK;
        else return BAD;
    }
}
