package interfacesLayer;

import logicLayer.lights.TurnSignal;

public class Controller {
    private TurnSignal turnSignal;

    public void metoda() {
        System.out.println("o cie panie");
    }

    public void roundLeft() {

    }

    public void roundRight() {
        this.turnSignal.turnRight();
    }
}
