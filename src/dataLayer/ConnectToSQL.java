package dataLayer;

import logicLayer.Route.Route;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectToSQL {
    private String url = "jdbc:mysql://localhost:3306/dashboard";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ConnectToSQL() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS dashboard;");
        statement.execute("USE dashboard;");

        statement.execute(
                "CREATE TABLE IF NOT EXISTS routes (" +
                        "id smallint unsigned not null auto_increment PRIMARY KEY, " +
                        "start_date DATETIME, " +
                        "end_date DATETIME, " +
                        "start_location VARCHAR(128), " +
                        "end_location VARCHAR(128), " +
                        "route_length float, " +
                        "duration float);"
        );

        connection.close();
    }

    public void clearDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE routes;");
        connection.close();
    }

    public void addRoute(Route route) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        String startDateString = route.getStartDate().format(formatter);
        String endDateString = route.getEndDate().format(formatter);
        String startLocation = route.getStartingLocation();
        String endLocation = route.getEndLocation();
        if (endLocation == null) endLocation = " ";

        statement.execute(
                "INSERT INTO routes (start_date, end_date, start_location, end_location, route_length, duration) " +
                    "VALUES (\"" + startDateString + "\", \"" + endDateString + "\", \"" + startLocation +
                        "\", \"" + endLocation + "\", \"" + route.getRouteLength() + "\", \"" + route.getDuration() + "\");");

        connection.close();
    }

    private void updateTable(Statement statement, double routeLength) throws SQLException {
        String tableName = "routes";
        statement.execute("" +
                "UPDATE " + tableName +
                " SET route_length = route_length + " + routeLength + ";");
    }

    public void getAllRoutes(RouteRepository routes) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM routes;");

        while (resultSet.next()) {
            Route route = new Route();
            route.setStartDate(LocalDateTime.parse(resultSet.getString(2), formatter));
            route.setEndDate(LocalDateTime.parse(resultSet.getString(3), formatter));
            route.setStartingLocation(resultSet.getString(4));
            route.setEndLocation(resultSet.getString(5));
            route.setRouteLength(resultSet.getFloat(6));
            route.setDuration(resultSet.getFloat(7));
            routes.add(route);
        }

        connection.close();
    }

    public void getRoute(int whichRoute) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM routes WHERE id = " + whichRoute + ";");

        while (resultSet.next()) {
            System.out.println("Route nr:       " + resultSet.getInt(1));
            System.out.println("Start date:     " + resultSet.getString(2));
            System.out.println("End date:       " + resultSet.getString(3));
            System.out.println("Route length:   " + resultSet.getFloat(6));
            System.out.println("Duration:       " + resultSet.getFloat(7));
            System.out.println();
        }

        connection.close();
    }
}