package dataLayer;

import logicLayer.Route.Route;

import java.util.ArrayList;

public class RouteRepository {

    private ArrayList<Route> routes;

    public RouteRepository() {
        routes = new ArrayList<>();
    }

    public void add(Route item) {
        routes.add(item);
    }

    public void remove(Route item) {
        routes.remove(item);
    }

    public int size() {
        return routes.size();
    }

    public void clear() {
        routes.clear();
    }

    public Route get(int index) {
        return routes.get(index);
    }
}