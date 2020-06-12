package logicLayer.Route;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Route extends Kilometrage {
    private String startingLocation;
    private String endLocation = null;
    private double duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Route() {}

    public Route(double routeLength, String startingLocation) {
        super(routeLength);
        this.startDate = LocalDateTime.now();
        this.startingLocation = startingLocation;
    }

    public void endRoute(String endLocation) {
        this.endLocation = endLocation;
        this.endDate = LocalDateTime.now();
        this.duration = Duration.between(getStartDate(), getEndDate()).getSeconds();
    }

    public void endRoute(String endLocation, double routeLength) {
        this.endLocation = endLocation;
        this.setRouteLength(routeLength);
        this.endDate = LocalDateTime.now();
        this.duration = Duration.between(getStartDate(), getEndDate()).getSeconds();
    }

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
