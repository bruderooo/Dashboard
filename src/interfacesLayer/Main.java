package interfacesLayer;

import dataLayer.ConnectToSQL;
import logicLayer.Route.Route;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {

        ConnectToSQL connectToSQL = new ConnectToSQL();

        Route route = new Route();

        TimeUnit.SECONDS.sleep(10);

        route.endRoute(69, connectToSQL);

//        connectToSQL.getRoute(3);

//        connectToSQL.getAllRoutes();
    }
}
