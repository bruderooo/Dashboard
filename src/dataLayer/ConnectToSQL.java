package dataLayer;

import logicLayer.Route.Route;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Klasa odpowiedzialna za za zapisywanie tras do bazy danych.
 * Do realizacji tego zadania wykorzystany zostal MySQL.
 * Do polaczenia z baza danych potrzebny jest kontroler JDBC.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 * @see <a href="https://dev.mysql.com/downloads/connector/j/">JDBC</a>
 */
public class ConnectToSQL {
    private static final String url = "jdbc:mysql://localhost:3306/dashboard";
    private static final String user = "root";
    private static final String password = "";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Konstruktor, tworzy baze danych <b>dashboard</b> jezeli ta nie istnieje.
     * Tworzy w niej tabele <b>route</b> jeżeli ta nie istnieje.
     *
     * @throws SQLException the sql exception
     */
    public ConnectToSQL() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
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

    /**
     * Metoda usuwa wszystkie rejestr tabeli <b>route</b> (tras).
     *
     * @throws SQLException
     */
    public void clearDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE routes;");
        connection.close();
    }

    /**
     * Metoda dodaje rejestr trasy do tabeli <b>routes</b> w bazie danych <b>dashboard</b>.
     *
     * @param route
     * @throws SQLException
     */
    public void addRoute(Route route) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
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

    /**
     * Metoda odczytujaca rekordy z bazy danych.
     *
     * @param routes
     * @throws SQLException
     */
    public void getAllRoutes(RouteRepository routes) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
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
}