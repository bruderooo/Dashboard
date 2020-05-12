package dataLayer;

import java.time.Duration;
import java.time.LocalTime;

public class Route {

    private Duration duration;
    private float routeLength;
    private final LocalTime startDate;
    private LocalTime endDate;

    public Route(LocalTime start_date) {
        this.startDate = start_date;
    }

    public void setRouteLength(float route_length) {
        this.routeLength = route_length;
    }

    public void setEndDate(LocalTime end_date) {
        this.endDate = end_date;
        this.duration = Duration.between(end_date, this.startDate);
    }

    public Duration getDuration() {
        return duration;
    }

    public float getRouteLength() {
        return routeLength;
    }

    public LocalTime getStartDate() {
        return startDate;
    }

    public LocalTime getEndDate() {
        return endDate;
    }
}
