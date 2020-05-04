package Lights;

public class TurnSignal {
    private Light leftLight;
    private Light rightLight;

    public TurnSignal() {
        this.leftLight = new Light(false);
        this.rightLight = new Light(false);
    }

    public void turnLeft() {
        this.leftLight.switchLight();
    }

    public void turnRight() {
        this.rightLight.switchLight();
    }

}
