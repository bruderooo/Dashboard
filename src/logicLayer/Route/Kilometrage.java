package logicLayer.Route;

import java.time.LocalDateTime;

/**
 * Klasa odpowiedzialna za przebieg pojazdu.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class Kilometrage {
    private double routeLength;
    private LocalDateTime date = null;

    /**
     * Konstruktor klasy Kilometrage.
     */
    public Kilometrage() {}

    /**
     * Konstruktor, inicjalizuje pole date za pomoca metody now Klasy LocalDateTime,
     * jezeli przekazany parametr jest wartoscia true.
     *
     * @param daily czy jest to obiekt odpowiedzialna za przebieg dzienny
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDateTime.html#now()">LocalDateTime.now()</a>
     */
    public Kilometrage(boolean daily) {
        if (daily) date = LocalDateTime.now();
        routeLength = 0;
    }

    /**
     * Konstruktor, ustawia pole routeLenght na podane jako argument.
     *
     * @param routeLength przebieg
     */
    public Kilometrage(double routeLength) {
        this.routeLength = routeLength;
    }

    public String toString() {
        return Double.toString(routeLength);
    }

    public void setDate(LocalDateTime startDate) {
        this.date = startDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public double getRouteLength() {
        return routeLength;
    }
}
