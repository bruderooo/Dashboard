package dataLayer;

import logicLayer.Route.Route;

import java.util.ArrayList;

public class RouteRepository implements Repository<Route>{

    private ArrayList<Route> routes;

    public RouteRepository() {
        routes = new ArrayList<>();
    }

    @Override
    public void add(Route item) {
        routes.add(item);
    }

    @Override
    public void remove(Route item) {
        routes.remove(item);
    }

    @Override
    public Route get(int index) {
        return routes.get(index);
    }
}
