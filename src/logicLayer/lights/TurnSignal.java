package logicLayer.lights;

public class TurnSignal {
    private Light leftLight;
    private Light rightLight;

    public TurnSignal() {
        this.leftLight = new Light();
        this.rightLight = new Light();
    }

    public void turnLeft() {
        if( this.rightLight.isOn() ) this.rightLight.switchLight();

        this.leftLight.switchLight();
    }

    public void turnRight() {
        if( this.leftLight.isOn() ) this.leftLight.switchLight();

        this.rightLight.switchLight();
    }

    public Light getLeftLight() {
        return leftLight;
    }

    public Light getRightLight() {
        return rightLight;
    }
}
