package logicLayer.Route;

import java.time.LocalDateTime;

public class Kilometrage {
    private double routeLength;
    private LocalDateTime date = null;

    public Kilometrage() {}

    public Kilometrage(boolean daily) {
        if (daily) date = LocalDateTime.now();
        routeLength = 0;
    }

    public Kilometrage(double routeLength) {
        this.routeLength = routeLength;
    }

    public void setDate(LocalDateTime startDate) {
        this.date = startDate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return ", routeLength=" + routeLength;
    }

    public void endRoute(float routeLength) {
        this.routeLength = routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public double getRouteLength() {
        return routeLength;
    }
}
