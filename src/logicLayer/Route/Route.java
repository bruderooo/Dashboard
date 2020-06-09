package logicLayer.Route;

import dataLayer.ConnectToSQL;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Route {

    private Duration duration;
    private final LocalDateTime startDate;
    private LocalDateTime endDate;
    private double routeLength;

    public Route() {
        this.startDate = LocalDateTime.now();
    }

    public Route(double routeLength, LocalDateTime startDate, LocalDateTime endDate) {
        this.routeLength = routeLength;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = Duration.between(endDate, startDate);
    }

    public void endRoute(float routeLength, ConnectToSQL connectToSQL) throws SQLException {
        if (this.endDate == null) {
            this.routeLength = routeLength;
            this.endDate = LocalDateTime.now();
            this.duration = Duration.between(endDate, this.startDate);
            connectToSQL.addRoute(routeLength, this.startDate, this.endDate);
        }
    }

    public void endRoute() {
        if (this.endDate == null) {
            this.endDate = LocalDateTime.now();
            this.duration = Duration.between(endDate, this.startDate);
        }
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
