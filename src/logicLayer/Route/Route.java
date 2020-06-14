package logicLayer.Route;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasa odpowiedzialna za trasy uzytkownika, rozszeza Klase Kilometrage.
 * Trasa sklada sie z: lokacji poczatkowej, lokacji koncowej, czasu trwania, daty i czasu poczatku, daty i czasu konca.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.Route.Kilometrage
 */
public class Route extends Kilometrage {
    private String startingLocation;
    private String endLocation = null;
    private double duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Konstruktor klasy Route, wywoluje konstruktor superklasy Kilometrage.
     */
    public Route() {
        super();
    }

    /**
     * Konstruktor przyjmujacy lokalizacje poczatkowa i obecny przebieg calkowity pojazdu,
     * do obliczenia pozniej dlugosci trasy.
     *
     * @param routeLength obecny przebieg calkowity pojazdu
     * @param startingLocation lokalizacja poczatkowa
     */
    public Route(double routeLength, String startingLocation) {
        super(routeLength);
        this.startDate = LocalDateTime.now();
        this.startingLocation = startingLocation;
    }

    /**
     * Metoda odpowiedzialna za zakonczenie trasy przyjmuje lokalizacje koncowa i
     * roznice calkowietego przebiegu pojazdu oraz przebiegu zapisanego wczesniej
     * w polu route lenght, która jest dlugoscia trasy.
     *
     * @param endLocation lokalizacja koncowa
     * @param routeLength roznica obecnego calkowietego przebiegu i przebiegu zapisanego w trakcie tworzenia trasy
     */
    public void endRoute(String endLocation, double routeLength) {
        this.endLocation = endLocation;
        this.setRouteLength(routeLength);
        this.endDate = LocalDateTime.now();
        this.duration = Duration.between(getStartDate(), getEndDate()).getSeconds();
    }

    /**
     * Metoda przeksztalcajaca obiekt do postaci lancucha znakow uzywany do wyswietlania tras
     * w przeznaczonym do tego oknie.
     *
     * @return lancuch znakow bedacy reprezentacja obiektu
     */
    @Override
    public String toString() {
        DecimalFormat dec = new DecimalFormat("#0.0");
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Lokalizacja początkowa: " + startingLocation +
                ", lokalizacja końcowa: " + endLocation +
                ", długość: " + dec.format(getRouteLength()) + "km" +
                ", czas trwania: " + dec.format(duration) + "s" +
                ", początek: " + startDate.format(dt) +
                ", koniec: " + endDate.format(dt);
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public double getDuration() {
        return duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
}
