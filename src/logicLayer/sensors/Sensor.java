package logicLayer.sensors;

/**
 * Interfejs do obslugi czujnikow pojazdu.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public interface Sensor {

    int GOOD = 0;
    int CHECK = 1;
    int BAD = 2;

    /**
     * Metoda zwracajaca obecna wartosc obslugiwana przez dany czujnik.
     *
     * @return wartosc obslugiwana przez czujnik
     */
    double getValue();

    /**
     * Metoda zwracajaca informacje o stanie tego co kontroluje dany czyjnik.
     *
     * @return w jakim stanie jest obiekt kontrolowany przez czujnik
     */
    int status();
}
