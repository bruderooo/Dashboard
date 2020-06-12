package interfacesLayer;

import dataLayer.ConnectToSQL;
import dataLayer.RouteRepository;
import logicLayer.Route.Kilometrage;
import logicLayer.Route.Route;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws SQLException {

        ConnectToSQL sql = new ConnectToSQL();

        RouteRepository routes = new RouteRepository();
        sql.getAllRoutes(routes);
        System.out.println(routes.toString());

    }
}
