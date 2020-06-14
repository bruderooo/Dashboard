package dataLayer;

import logicLayer.Route.Route;

import java.util.ArrayList;

/**
 * Klasa przechowujaca trasy uzytkownika.
 * Do tego celu została wykozytstana kolekcja <b>ArrayList</b>.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html">ArrayList</a>
 */
public class RouteRepository {

    private ArrayList<Route> routes;

    /**
     * Konstruktor, tworzy kolekcje ArrayList routes.
     */
    public RouteRepository() {
        routes = new ArrayList<>();
    }

    /**
     * Metoda dodajaca element typu <b>Route</b> do kolekcji.
     *
     * @param item dodawany element
     */
    public void add(Route item) {
        routes.add(item);
    }

    /**
     * Metoda usuwajaca element typu <b>Route</b> z kolekcji.
     *
     * @param item usuwany element
     */
    public void remove(Route item) {
        routes.remove(item);
    }

    /**
     * Metoda zwracajaca ilosc elementow w kolekcji.
     *
     * @return zwraca ilosc elementow w ArrayList routes.
     */
    public int size() {
        return routes.size();
    }

    /**
     * Usuwa wszystkie elementy z ArrayList routes.
     */
    public void clear() {
        routes.clear();
    }

    public Route get(int index) {
        return routes.get(index);
    }
}