package logicLayer.sensors;

/**
 * Klasa odpowiedzialna za poziom paliwa.
 * Implementuje interfejs Sensor.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.sensors.Sensor
 */
public class FuelLevelSensor implements Sensor {
    private double fuelAmount;
    public static final double maxFuelAmount = 80.0;
    public static final double fuelPerOneKm = 0.146;

    /**
     * Konstruktor, ustawia pole odpowiedzialne za poziom paliwa na maksymalna wartosc,
     * oznaczajaca pelny zbiornik paliwa.
     */
    public FuelLevelSensor() {
        this.fuelAmount = maxFuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String toString() {
        return "Poziom zbiornika paliwa: " + fuelAmount + "/" + maxFuelAmount;
    }

    /**
     * Metoda zwracajaca obecna ilosc paliwa w zbiorniku.
     *
     * @return obecna ilosc paliwa w zbiorniku w litrach
     */
    @Override
    public double getValue() {
        return fuelAmount;
    }

    /**
     * Metoda zwraca informacje o statusie zbiornika paliwa.
     *
     * @return informacja czy stan jest dobry, zly, czy nalezy poddac kontroli
     */
    @Override
    public int status() {
        if (fuelAmount >= (maxFuelAmount/8)*3) return GOOD;
        else if (fuelAmount < (fuelAmount/8)*2 && fuelAmount >= (maxFuelAmount/8)*2) return CHECK;
        else return BAD;
    }
}
