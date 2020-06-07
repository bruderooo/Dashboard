package dataLayer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectToSQL {
    private String url = "jdbc:mysql://localhost:3306/dashboard";

    public ConnectToSQL() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS dashboard");
        statement.execute("USE dashboard");
        statement.execute("" +
                "CREATE TABLE IF NOT EXISTS routes (" +
                "id smallint unsigned not null auto_increment PRIMARY KEY, " +
                "start_date DATETIME, " +
                "end_date DATETIME, " +
                "route_length float, " +
                "duration float DEFAULT TIMEDIFF(end_date, start_date))"
        );

        statement.execute( "CREATE TABLE IF NOT EXISTS refuel (route_length float)");
        statement.execute( "UPDATE refuel SET route_length = 0");
        statement.execute( "CREATE TABLE IF NOT EXISTS kilometrage (route_length float)");
        statement.execute( "UPDATE kilometrage SET route_length = 0");

        connection.close();
    }

    public void addRoute(float routeLength, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);

        statement.execute(
                "INSERT INTO routes (start_date, end_date, route_length) " +
                    "VALUES (\"" + startDateString + "\", \"" + endDateString + "\", \"" + routeLength + "\")");

        updateTable(statement, routeLength, "refuel");
        updateTable(statement, routeLength, "kilometrage");

        connection.close();
    }

    public void resetRefuel() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        statement.execute("UPDATE refuel SET route_length = 0");

        connection.close();
    }

    private void updateTable(Statement statement, float routeLength, String tableName) throws SQLException {
        statement.execute("" +
                "UPDATE " + tableName +
                "SET route_length = route_length + " + routeLength + ";");
    }

    public void getAllRoutes() throws SQLException {
        Connection connection = DriverManager.getConnection(url, "root", "");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM routes;");

        while (resultSet.next()) {
            System.out.println("Route nr:       " + resultSet.getInt(1));
            System.out.println("Start date:     " + resultSet.getString(2));
            System.out.println("End date:       " + resultSet.getString(3));
            System.out.println("Route length:   " + resultSet.getFloat(4));
            System.out.println("Duration:       " + resultSet.getInt(5));
            System.out.println();
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
            System.out.println("Route length:   " + resultSet.getFloat(4));
            System.out.println("Duration:       " + resultSet.getInt(5));
            System.out.println();
        }

        connection.close();
    }
}