package logicLayer.sensors;

/**
 * Metoda odpowiedzialna za poziom naladowania akumulatora.
 * Implementuje interfejs Sensor.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.sensors.Sensor
 */
public class AccumulatorLoadSensor implements Sensor {

    public static double maxLoad = 100.0;
    private double currentLoad;

    /**
     * Konstruktor, ustawia pole odpowiedzialne za obecny poziom naladowania na maksymalny.
     */
    public AccumulatorLoadSensor() {
        this.currentLoad = maxLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public String toString() {
        return "Poziom naładowania akumulatora: " + currentLoad + "/" + maxLoad;
    }

    /**
     * Metoda zwracajaca wartosc naladowania akumulatora.
     *
     * @return poziom naladowania akumulatora w procentach
     */
    @Override
    public double getValue() {
        return currentLoad;
    }

    /**
     * Metoda zwracajaca informacje o statusie akumulatora.
     *
     * @return stan akumulatora czy wszystko jest dobrze, czy nalezy sprawdzic, czy jest zle
     */
    @Override
    public int status() {
        if (currentLoad <= maxLoad && currentLoad >= 0.6 * maxLoad) return GOOD;
        if (currentLoad < 0.6 * maxLoad && currentLoad >= 0.1 * maxLoad) return CHECK;
        else return BAD;
    }
}
