package logicLayer.lights;

public class TurnSignal {
    private Light leftLight;
    private Light rightLight;

    public TurnSignal() {
        this.leftLight = new Light();
        this.rightLight = new Light();
    }

    public void turnLeft() {
        this.leftLight.switchLight();
    }

    public void turnRight() {
        this.rightLight.switchLight();
    }

}
