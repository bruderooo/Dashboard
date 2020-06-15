package logicLayer.sensors;

/**
 * Klasa odpowiedzialna za poziom oleju silnikowego.
 * Implementuje interfejs Sensor.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.sensors.Sensor
 */
public class OilLevelSensor implements Sensor {

    public static double maximum = 100.0;
    private double currentAmount;

    /**
     * Konstruktor, ustawia poziom oleju silnikowego na maksymalny mozliwy.
     */
    public OilLevelSensor() {
        this.currentAmount = maximum;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String toString() {
        return "Poziom oleju: " + currentAmount + "/" + maximum;
    }

    /**
     * Metoda zwraca wartosc obecnego poziomu oleju silnikowego jako wartosc procentowa
     *
     * @return obecny poziom oleju silnikowego w procetach
     */
    @Override
    public double getValue() {
        return currentAmount;
    }

    /**
     * Metoda zwraca informacje o stanie poziomu oleju silnikowego.
     *
     * @return stan oleju, czy jest dobry, nieodpowiedni, czy wymaga kontroli
     */
    @Override
    public int status() {
        if (currentAmount <= maximum && currentAmount >= maximum * 0.5) return GOOD;
        else if (currentAmount < 0.5 * maximum && currentAmount >= 0.2 * maximum) return CHECK;
        else return BAD;
    }
}
