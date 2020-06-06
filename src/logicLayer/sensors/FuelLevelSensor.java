package logicLayer.sensors;

public class FuelLevelSensor implements Sensor {
    private double fuelAmount;
    public static final double maxFuelAmount = 80.0;
    public static final double fuelPerOneKm = 0.097;


    public FuelLevelSensor() {
        this.fuelAmount = maxFuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    @Override
    public double getValue() {
        return fuelAmount;
    }

    @Override
    public int status() {
        if (fuelAmount >= (maxFuelAmount/8)*3) return GOOD;
        else if (fuelAmount < (fuelAmount/8)*2 && fuelAmount >= (maxFuelAmount/8)*2) return CHECK;
        else return BAD;
    }
}
